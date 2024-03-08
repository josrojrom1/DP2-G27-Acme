
package acme.entities.risk;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Risk extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	//a reference (pattern “R-[0-9]{3}”), not blank, unique)
	@NotBlank
	@Pattern(regexp = "R-\\d{3}")
	@Column(unique = true)
	private String				reference;

	//an identification date (in the past)
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				identificationDate;

	//an impact (positive real number)
	@Digits(integer = 3, fraction = 2)
	@DecimalMin("0.00")
	@DecimalMax("100.00")
	private double				impact;

	//a probability
	@Digits(integer = 3, fraction = 2)
	@DecimalMin("0.00")
	@DecimalMax("100.00")
	private double				probability;


	@Transient
	private double value() {
		return this.impact * this.probability;
	}


	//a description (not blank, shorter than 101 characters)
	@NotBlank
	@Length(max = 100)
	private String	description;

	//an optional link with further information
	@URL
	private String	link;

}
