
package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.invoice.Invoice;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.entities.sponsorship.SponsorshipType;
import acme.roles.Sponsor;

@Service
public class SponsorSponsorshipShowService extends AbstractService<Sponsor, Sponsorship> {

	// Internal state -----------------------------------------------

	@Autowired
	private SponsorSponsorshipRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Sponsorship sponsorship;
		Sponsor sponsor;

		masterId = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(masterId);
		sponsor = sponsorship == null ? null : sponsorship.getSponsor();
		status = super.getRequest().getPrincipal().hasRole(sponsor) && super.getRequest().getPrincipal().getActiveRoleId() == sponsor.getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Sponsorship sponsorship;
		int id;

		id = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);

		super.getBuffer().addData(sponsorship);

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
		super.getResponse().addData(dataset);

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
		dataset.put("readOnly", true);
		super.getResponse().addData(dataset);
	}

}
