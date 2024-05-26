
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

		double sponsorshipAmountAverageEUR;
		double sponsorshipAmountAverageUSD;
		double sponsorshipAmountAverageGBP;

		double sponsorshipAmountDeviationEUR;
		double sponsorshipAmountDeviationUSD;
		double sponsorshipAmountDeviationGBP;

		double minimumSponsorshipAmountEUR;
		double minimumSponsorshipAmountUSD;
		double minimumSponsorshipAmountGBP;

		double maximumSponsorshipAmountEUR;
		double maximumSponsorshipAmountUSD;
		double maximumSponsorshipAmountGBP;

		double invoiceQuantityAverageEUR;
		double invoiceQuantityAverageUSD;
		double invoiceQuantityAverageGBP;

		double invoiceQuantityDeviationEUR;
		double invoiceQuantityDeviationUSD;
		double invoiceQuantityDeviationGBP;

		double minimumInvoiceQuantityEUR;
		double minimumInvoiceQuantityUSD;
		double minimumInvoiceQuantityGBP;

		double maximumInvoiceQuantityEUR;
		double maximumInvoiceQuantityUSD;
		double maximumInvoiceQuantityGBP;

		totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne = this.repository.totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne(id);
		totalNumOfSponsorshipsWithLink = this.repository.totalNumOfSponsorshipsWithLink(id);

		try {

			sponsorshipAmountAverageEUR = this.repository.sponsorshipAmountAverageEUR(id);
			sponsorshipAmountDeviationEUR = this.repository.sponsorshipAmountDeviationEUR(id);
			minimumSponsorshipAmountEUR = this.repository.minimumSponsorshipAmountEUR(id);
			maximumSponsorshipAmountEUR = this.repository.maximumSponsorshipAmountEUR(id);

			invoiceQuantityAverageEUR = this.repository.invoiceQuantityAverageEUR(id);
			invoiceQuantityDeviationEUR = this.repository.invoiceQuantityDeviationEUR(id);
			minimumInvoiceQuantityEUR = this.repository.minimumInvoiceQuantityEUR(id);
			maximumInvoiceQuantityEUR = this.repository.maximumInvoiceQuantityEUR(id);

		} catch (Exception e) {

			sponsorshipAmountAverageEUR = 0.0;
			sponsorshipAmountDeviationEUR = -10.0;
			minimumSponsorshipAmountEUR = 0.0;
			maximumSponsorshipAmountEUR = 0.0;

			invoiceQuantityAverageEUR = 0.0;
			invoiceQuantityDeviationEUR = -10.0;
			minimumInvoiceQuantityEUR = 0.0;
			maximumInvoiceQuantityEUR = 0.0;
		}

		try {

			sponsorshipAmountAverageUSD = this.repository.sponsorshipAmountAverageUSD(id);
			sponsorshipAmountDeviationUSD = this.repository.sponsorshipAmountDeviationUSD(id);
			minimumSponsorshipAmountUSD = this.repository.minimumSponsorshipAmountUSD(id);
			maximumSponsorshipAmountUSD = this.repository.maximumSponsorshipAmountUSD(id);

			invoiceQuantityAverageUSD = this.repository.invoiceQuantityAverageUSD(id);
			invoiceQuantityDeviationUSD = this.repository.invoiceQuantityDeviationUSD(id);
			minimumInvoiceQuantityUSD = this.repository.minimumInvoiceQuantityUSD(id);
			maximumInvoiceQuantityUSD = this.repository.maximumInvoiceQuantityUSD(id);

		} catch (Exception e) {

			sponsorshipAmountAverageUSD = 0.0;
			sponsorshipAmountDeviationUSD = -10.0;
			minimumSponsorshipAmountUSD = 0.0;
			maximumSponsorshipAmountUSD = 0.0;

			invoiceQuantityAverageUSD = 0.0;
			invoiceQuantityDeviationUSD = -10.0;
			minimumInvoiceQuantityUSD = 0.0;
			maximumInvoiceQuantityUSD = 0.0;
		}

		try {

			sponsorshipAmountAverageGBP = this.repository.sponsorshipAmountAverageGBP(id);
			sponsorshipAmountDeviationGBP = this.repository.sponsorshipAmountDeviationGBP(id);
			minimumSponsorshipAmountGBP = this.repository.minimumSponsorshipAmountGBP(id);
			maximumSponsorshipAmountGBP = this.repository.maximumSponsorshipAmountGBP(id);

			invoiceQuantityAverageGBP = this.repository.invoiceQuantityAverageGBP(id);
			invoiceQuantityDeviationGBP = this.repository.invoiceQuantityDeviationGBP(id);
			minimumInvoiceQuantityGBP = this.repository.minimumInvoiceQuantityGBP(id);
			maximumInvoiceQuantityGBP = this.repository.maximumInvoiceQuantityGBP(id);

		} catch (Exception e) {

			sponsorshipAmountAverageGBP = 0.0;
			sponsorshipAmountDeviationGBP = -10.0;
			minimumSponsorshipAmountGBP = 0.0;
			maximumSponsorshipAmountGBP = 0.0;

			invoiceQuantityAverageGBP = 0.0;
			invoiceQuantityDeviationGBP = -10.0;
			minimumInvoiceQuantityGBP = 0.0;
			maximumInvoiceQuantityGBP = 0.0;
		}

		dashboard = new SponsorDashboard();

		dashboard.setTotalNumOfInvoicesWithTaxLessOrEqualToTwentyOne(totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne);
		dashboard.setTotalNumOfSponsorshipsWithLink(totalNumOfSponsorshipsWithLink);

		dashboard.setSponsorshipAmountAverageEUR(sponsorshipAmountAverageEUR);
		dashboard.setSponsorshipAmountAverageUSD(sponsorshipAmountAverageUSD);
		dashboard.setSponsorshipAmountAverageGBP(sponsorshipAmountAverageGBP);

		dashboard.setSponsorshipAmountDeviationEUR(sponsorshipAmountDeviationEUR);
		dashboard.setSponsorshipAmountDeviationUSD(sponsorshipAmountDeviationUSD);
		dashboard.setSponsorshipAmountDeviationGBP(sponsorshipAmountDeviationGBP);

		dashboard.setMaximumSponsorshipAmountEUR(maximumSponsorshipAmountEUR);
		dashboard.setMaximumSponsorshipAmountUSD(maximumSponsorshipAmountUSD);
		dashboard.setMaximumSponsorshipAmountGBP(maximumSponsorshipAmountGBP);

		dashboard.setMinimumSponsorshipAmountEUR(minimumSponsorshipAmountEUR);
		dashboard.setMinimumSponsorshipAmountUSD(minimumSponsorshipAmountUSD);
		dashboard.setMinimumSponsorshipAmountGBP(minimumSponsorshipAmountGBP);

		dashboard.setInvoiceQuantityAverageEUR(invoiceQuantityAverageEUR);
		dashboard.setInvoiceQuantityAverageUSD(invoiceQuantityAverageUSD);
		dashboard.setInvoiceQuantityAverageGBP(invoiceQuantityAverageGBP);

		dashboard.setInvoiceQuantityDeviationEUR(invoiceQuantityDeviationEUR);
		dashboard.setInvoiceQuantityDeviationUSD(invoiceQuantityDeviationUSD);
		dashboard.setInvoiceQuantityDeviationGBP(invoiceQuantityDeviationGBP);

		dashboard.setMaximumInvoiceQuantityEUR(maximumInvoiceQuantityEUR);
		dashboard.setMaximumInvoiceQuantityUSD(maximumInvoiceQuantityUSD);
		dashboard.setMaximumInvoiceQuantityGBP(maximumInvoiceQuantityGBP);

		dashboard.setMinimumInvoiceQuantityEUR(minimumInvoiceQuantityEUR);
		dashboard.setMinimumInvoiceQuantityUSD(minimumInvoiceQuantityUSD);
		dashboard.setMinimumInvoiceQuantityGBP(minimumInvoiceQuantityGBP);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final SponsorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne", "totalNumOfSponsorshipsWithLink", "sponsorshipAmountAverageEUR", "sponsorshipAmountAverageUSD", "sponsorshipAmountAverageGBP", "sponsorshipAmountDeviationEUR",
			"sponsorshipAmountDeviationUSD", "sponsorshipAmountDeviationGBP", "minimumSponsorshipAmountEUR", "minimumSponsorshipAmountUSD", "minimumSponsorshipAmountGBP", "maximumSponsorshipAmountEUR", "maximumSponsorshipAmountUSD",
			"maximumSponsorshipAmountGBP", "invoiceQuantityAverageEUR", "invoiceQuantityAverageUSD", "invoiceQuantityAverageGBP", "invoiceQuantityDeviationEUR", "invoiceQuantityDeviationUSD", "invoiceQuantityDeviationGBP", "minimumInvoiceQuantityEUR",
			"minimumInvoiceQuantityUSD", "minimumInvoiceQuantityGBP", "maximumInvoiceQuantityEUR", "maximumInvoiceQuantityUSD", "maximumInvoiceQuantityGBP");

		super.getResponse().addData(dataset);
	}

}
