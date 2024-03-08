
package acme.forms;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SponsorDashboard extends AbstractForm {

	// Serialization identifier 

	private static final long	serialVersionUID	= 1L;

	// Attributes

	@Min(0)
	int							totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne;
	@Min(0)
	int							totalNumOfSponsorshipsWithLink;

	@DecimalMin("0.0")
	double						sponsorshipAmountAverage;
	@DecimalMin("0.0")
	double						sponsorshipAmountDeviation;

	double						minimumSponsorshipAmount;
	double						maximumSponsorshipAmount;

	@DecimalMin("0.0")
	double						invoiceQuantityAverage;
	@DecimalMin("0.0")
	double						invoiceQuantityDeviation;

	double						minimumInvoiceQuantity;
	double						maximumInvoiceQuantity;

}
