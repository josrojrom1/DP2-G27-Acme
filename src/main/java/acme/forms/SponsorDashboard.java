
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

	Integer						totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne;
	Integer						totalNumOfSponsorshipsWithLink;
	Double						sponsorshipAmountAverage;
	Double						sponsorshipAmountDeviation;
	Integer						minimumSponsorshipAmount;
	Integer						maximumSponsorshipAmount;
	Double						invoiceQuantityAverage;
	Double						invoiceQuantityDeviation;
	Integer						minimumInvoiceQuantity;
	Integer						maximumInvoiceQuantity;

}
