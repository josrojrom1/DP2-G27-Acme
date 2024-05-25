
package acme.features.sponsor.sponsorship;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.invoice.Invoice;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.entities.sponsorship.SponsorshipType;
import acme.roles.Sponsor;

@Service
public class SponsorSponsorshipPublishService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	private SponsorSponsorshipRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int sponsorshipId;
		Sponsorship sponsorship;
		Sponsor sponsor;

		sponsorshipId = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(sponsorshipId);
		sponsor = sponsorship == null ? null : sponsorship.getSponsor();
		status = sponsorship != null && sponsorship.isDraftMode() && super.getRequest().getPrincipal().hasRole(sponsor) && super.getRequest().getPrincipal().getActiveRoleId() == sponsor.getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Sponsorship object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneSponsorshipById(id);
		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Sponsorship object) {
		assert object != null;

		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);

		super.bind(object, "code", "moment", "startDate", "expirationDate", "amount", "type", "contact", "link");
		object.setProject(project);
	}

	@Override
	public void validate(final Sponsorship object) {
		assert object != null;

		final Date baseDate = MomentHelper.parse("2000/01/01 00:00", "yyyy/MM/dd HH:mm");
		final Date topDate = MomentHelper.parse("2200/12/31 23:59", "yyyy/MM/dd HH:mm");

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Sponsorship existing;

			existing = this.repository.findOneSponsorshipByCode(object.getCode());
			super.state(existing == null || object.getId() == existing.getId(), "code", "sponsor.sponsorship.form.error.code.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("moment"))
			super.state(MomentHelper.isAfterOrEqual(object.getMoment(), baseDate), "moment", "sponsor.sponsorship.form.error.too-soon");

		if (!super.getBuffer().getErrors().hasErrors("startDate"))
			super.state(object.getMoment() != null, "startDate", "sponsor.sponsorship.form.error.inlavid-moment-date");

		if (!super.getBuffer().getErrors().hasErrors("startDate"))
			super.state(MomentHelper.isAfter(object.getStartDate(), object.getMoment()), "startDate", "sponsor.sponsorship.form.error.inlavid-start-date");

		if (!super.getBuffer().getErrors().hasErrors("startDate"))
			super.state(MomentHelper.isBeforeOrEqual(object.getStartDate(), topDate), "startDate", "sponsor.sponsorship.form.error.too-late");

		if (!super.getBuffer().getErrors().hasErrors("expirationDate")) {
			Date minimumExpirationDate;

			minimumExpirationDate = MomentHelper.deltaFromMoment(object.getStartDate(), 30, ChronoUnit.DAYS);
			super.state(object.getStartDate() != null, "expirationDate", "sponsor.sponsorship.form.error.start-date-not-null");
			super.state(MomentHelper.isAfter(object.getExpirationDate(), minimumExpirationDate), "expirationDate", "sponsor.sponsorship.form.error.too-close");
		}

		if (!super.getBuffer().getErrors().hasErrors("expirationDate"))
			super.state(MomentHelper.isBeforeOrEqual(object.getExpirationDate(), topDate), "expirationDate", "sponsor.sponsorship.form.error.too-late");

		if (!super.getBuffer().getErrors().hasErrors("amount")) {
			super.state(object.getAmount().getAmount() > 0, "amount", "sponsor.sponsorship.form.error.negative-amount");
			super.state(object.getAmount().getAmount() <= 1000000, "amount", "sponsor.sponsorship.form.error.too-big");
		}
		if (!super.getBuffer().getErrors().hasErrors("amount")) {
			Collection<Invoice> invoices = this.repository.findManyInvoicesByMasterId(object.getId());
			double total = 0.0;
			for (Invoice i : invoices)
				total += i.totalAmount();
			super.state(object.getAmount().getAmount() == total, "amount", "sponsor.sponsorship.form.error.invoices-inconsistency");
		}

		if (!super.getBuffer().getErrors().hasErrors("project")) {
			Project objProject = object.getProject();
			super.state(!objProject.isDraftMode(), "project", "sponsor.sponsorship.form.error.project.published");
		}
	}
	@Override
	public void perform(final Sponsorship object) {
		assert object != null;

		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Sponsorship object) {
		assert object != null;

		Collection<Project> projects;
		Collection<Invoice> invoices;
		SelectChoices projectChoices;
		SelectChoices typeChoices;
		typeChoices = SelectChoices.from(SponsorshipType.class, object.getType());
		projects = this.repository.findPublishedProjects();
		projectChoices = SelectChoices.from(projects, "title", object.getProject());
		Dataset dataset;
		dataset = super.unbind(object, "code", "moment", "startDate", "expirationDate", "amount", "contact", "link", "draftMode");
		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("type", typeChoices.getSelected().getKey());
		dataset.put("types", typeChoices);

		boolean invoicesDraftModeState = true;
		double total = 0.0;
		invoices = this.repository.findManyInvoicesByMasterId(object.getId());
		for (Invoice i : invoices)
			if (invoices.isEmpty() || i.isDraftMode())
				break;
			else
				total += i.totalAmount();
		if (total == object.getAmount().getAmount() && !invoices.isEmpty())
			invoicesDraftModeState = false;
		dataset.put("invoicesDraftModeState", invoicesDraftModeState);
		super.getResponse().addData(dataset);
	}

}
