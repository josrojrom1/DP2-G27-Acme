
package acme.features.sponsor.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.SponsorDashboard;
import acme.roles.Sponsor;

@Service
public class SponsorDashboardShowService extends AbstractService<Sponsor, SponsorDashboard> {

	@Autowired
	private SponsorDashboardRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		SponsorDashboard dashboard;
		int id;
		id = super.getRequest().getPrincipal().getActiveRoleId();
		int totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne;
		int totalNumOfSponsorshipsWithLink;

		double sponsorshipAmountAverage;
		// double sponsorshipAmountDeviation;
		double minimumSponsorshipAmount;
		double maximumSponsorshipAmount;

		double invoiceQuantityAverage;
		// double invoiceQuantityDeviation;
		double minimumInvoiceQuantity;
		double maximumInvoiceQuantity;

		totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne = this.repository.totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne(id);
		totalNumOfSponsorshipsWithLink = this.repository.totalNumOfSponsorshipsWithLink(id);

		sponsorshipAmountAverage = this.repository.sponsorshipAmountAverage(id);
		minimumSponsorshipAmount = this.repository.minimumSponsorshipAmount(id);
		maximumSponsorshipAmount = this.repository.maximumSponsorshipAmount(id);

		invoiceQuantityAverage = this.repository.invoiceQuantityAverage(id);
		minimumInvoiceQuantity = this.repository.minimumInvoiceQuantity(id);
		maximumInvoiceQuantity = this.repository.maximumInvoiceQuantity(id);

		dashboard = new SponsorDashboard();

		dashboard.setTotalNumOfInvoicesWithTaxLessOrEqualToTwentyOne(totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne);
		dashboard.setTotalNumOfSponsorshipsWithLink(totalNumOfSponsorshipsWithLink);

		dashboard.setSponsorshipAmountAverage(sponsorshipAmountAverage);
		dashboard.setMinimumSponsorshipAmount(minimumSponsorshipAmount);
		dashboard.setMaximumSponsorshipAmount(maximumSponsorshipAmount);

		dashboard.setInvoiceQuantityAverage(invoiceQuantityAverage);
		dashboard.setMinimumInvoiceQuantity(minimumInvoiceQuantity);
		dashboard.setMaximumInvoiceQuantity(maximumInvoiceQuantity);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final SponsorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne", "totalNumOfSponsorshipsWithLink", "sponsorshipAmountAverage", "minimumSponsorshipAmount", "maximumSponsorshipAmount", "invoiceQuantityAverage",
			"minimumInvoiceQuantity", "maximumInvoiceQuantity");

		super.getResponse().addData(dataset);
	}

}
