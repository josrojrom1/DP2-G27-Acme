
package acme.features.sponsor.invoice;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.invoice.Invoice;
import acme.entities.sponsorship.Sponsorship;
import acme.roles.Sponsor;

@Service
public class SponsorInvoiceCreateService extends AbstractService<Sponsor, Invoice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorInvoiceRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Sponsorship sponsorship;

		masterId = super.getRequest().getData("masterId", int.class);
		sponsorship = this.repository.findOneSponsorshipById(masterId);
		status = sponsorship != null && sponsorship.isDraftMode() && super.getRequest().getPrincipal().hasRole(sponsorship.getSponsor()) && super.getRequest().getPrincipal().getActiveRoleId() == sponsorship.getSponsor().getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Invoice object;
		int masterId;
		Sponsorship sponsorship;

		masterId = super.getRequest().getData("masterId", int.class);
		sponsorship = this.repository.findOneSponsorshipById(masterId);

		Date registration = MomentHelper.getCurrentMoment();

		object = new Invoice();
		object.setSponsorship(sponsorship);
		object.setRegistration(registration);
		object.setDraftMode(true);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Invoice object) {
		assert object != null;

		super.bind(object, "code", "registration", "dueDate", "quantity", "tax", "link");
	}

	@Override
	public void validate(final Invoice object) {
		assert object != null;

		final Date topDate = MomentHelper.parse("2200/12/31 23:59", "yyyy/MM/dd HH:mm");

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Invoice existing;

			existing = this.repository.findOneInvoiceByCode(object.getCode());
			super.state(existing == null, "code", "sponsor.invoice.form.error.code.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("registration"))
			super.state(MomentHelper.isAfter(object.getRegistration(), object.getSponsorship().getMoment()), "registration", "sponsor.invoice.form.error.registration");

		if (!super.getBuffer().getErrors().hasErrors("dueDate"))
			super.state(object.getRegistration() != null, "dueDate", "sponsor.invoice.form.error.invalid-registration");

		if (!super.getBuffer().getErrors().hasErrors("dueDate")) {
			Date minimumExpirationDate;

			minimumExpirationDate = MomentHelper.deltaFromMoment(object.getRegistration(), 30, ChronoUnit.DAYS);
			super.state(MomentHelper.isAfter(object.getDueDate(), minimumExpirationDate), "dueDate", "sponsor.invoice.form.error.too-close");
		}

		if (!super.getBuffer().getErrors().hasErrors("dueDate"))
			super.state(MomentHelper.isBeforeOrEqual(object.getDueDate(), topDate), "dueDate", "sponsor.invoice.form.error.too-late");

		if (!super.getBuffer().getErrors().hasErrors("quantity"))
			super.state(object.getQuantity().getAmount() > 0, "quantity", "sponsor.invoice.form.error.negative-amount");
		if (!super.getBuffer().getErrors().hasErrors("quantity"))
			super.state(object.getQuantity().getAmount() <= 1000000, "quantity", "sponsor.invoice.form.error.too-big");
		if (!super.getBuffer().getErrors().hasErrors("quantity")) {
			Sponsorship sponsorship = object.getSponsorship();
			String currency = sponsorship.getAmount().getCurrency();
			super.state(object.getQuantity().getCurrency().equals(currency), "quantity", "sponsor.invoice.form.error.invalid-currency");
		}

		if (!super.getBuffer().getErrors().hasErrors("quantity")) {
			Sponsorship sponsorship = object.getSponsorship();
			Double amount = sponsorship.getAmount().getAmount();
			Collection<Invoice> invoices = this.repository.findManyInvoicesByMasterId(sponsorship.getId());
			double invoicesTotal = 0.0;
			for (Invoice i : invoices)
				invoicesTotal += i.totalAmount();
			super.state(amount >= invoicesTotal + object.totalAmount(), "quantity", "sponsor.invoice.form.error.quantity-invalid");
		}
	}

	@Override
	public void perform(final Invoice object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Invoice object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "registration", "dueDate", "quantity", "tax", "link", "draftMode");
		dataset.put("totalAmount", object.totalAmount()); // ojo a esta
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));

		super.getResponse().addData(dataset);
	}

}
