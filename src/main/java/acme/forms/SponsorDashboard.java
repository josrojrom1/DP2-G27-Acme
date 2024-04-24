
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

	double						sponsorshipAmountAverage;
	double						sponsorshipAmountDeviation;

	double						minimumSponsorshipAmount;
	double						maximumSponsorshipAmount;

	double						invoiceQuantityAverage;
	double						invoiceQuantityDeviation;

	double						minimumInvoiceQuantity;
	double						maximumInvoiceQuantity;

}
