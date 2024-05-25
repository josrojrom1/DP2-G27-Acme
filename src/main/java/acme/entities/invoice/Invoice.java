
package acme.entities.invoice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import acme.entities.sponsorship.Sponsorship;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invoice extends AbstractEntity {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes

	@Pattern(regexp = "IN-[0-9]{4}-[0-9]{4}")
	@NotBlank
	@Column(unique = true)
	private String				code;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				registration;

	@NotNull
	// Must be one month ahead the registration time (must be implemented in the service)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dueDate;

	@NotNull
	// Must be positive
	private Money				quantity;

	@NotNull
	@DecimalMin("0.0")
	@DecimalMax("100.0")
	@Digits(integer = 3, fraction = 2)
	private Double				tax;

	@URL
	@Length(max = 255)
	private String				link;

	@NotNull
	private boolean				draftMode;

	// Derived attributes


	@Transient
	// The sum of the amount of invoices (including taxes) must add up 
	// and never exceed the the amount of money to be paid in the corresponding sponsorship
	public Double totalAmount() {
		return this.tax * this.quantity.getAmount() / 100 + this.quantity.getAmount();
	}

	// Relations


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsorship sponsorship;

}
