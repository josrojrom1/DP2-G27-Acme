
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SponsorDashboard extends AbstractForm {

	// Serialization identifier 

	private static final long	serialVersionUID	= 1L;

	// Attributes

	int							totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne;
	int							totalNumOfSponsorshipsWithLink;

	double						sponsorshipAmountAverageEUR;
	double						sponsorshipAmountAverageUSD;
	double						sponsorshipAmountAverageGBP;

	double						sponsorshipAmountDeviationEUR;
	double						sponsorshipAmountDeviationUSD;
	double						sponsorshipAmountDeviationGBP;

	double						minimumSponsorshipAmountEUR;
	double						minimumSponsorshipAmountUSD;
	double						minimumSponsorshipAmountGBP;

	double						maximumSponsorshipAmountEUR;
	double						maximumSponsorshipAmountUSD;
	double						maximumSponsorshipAmountGBP;

	double						invoiceQuantityAverageEUR;
	double						invoiceQuantityAverageUSD;
	double						invoiceQuantityAverageGBP;

	double						invoiceQuantityDeviationEUR;
	double						invoiceQuantityDeviationUSD;
	double						invoiceQuantityDeviationGBP;

	double						minimumInvoiceQuantityEUR;
	double						minimumInvoiceQuantityUSD;
	double						minimumInvoiceQuantityGBP;

	double						maximumInvoiceQuantityEUR;
	double						maximumInvoiceQuantityUSD;
	double						maximumInvoiceQuantityGBP;

}
