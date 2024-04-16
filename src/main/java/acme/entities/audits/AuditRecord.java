
package acme.entities.audits;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class AuditRecord extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	//code (pattern “AU-[0-9]{4}-[0-9]{3}”, not blank, unique)
	@NotBlank
	@Pattern(regexp = "AU-\\d{4}-\\d{3}")
	@Column(unique = true)
	private String				code;

	//period during which the subject was audited (in the past, at least one hour long)
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				startPeriod;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				endPeriod;

	// Posible método para implementar en la validación o servicio correspondiente cuando se de en teoría
	//public boolean atLeastOneHourLong() {
	//	double diff = this.endPeriod.getTime() - this.startPeriod.getTime();
	//	double hours = diff / (1000 * 60 * 60);
	//	return hours >= 1;
	//}

	//mark (“A+”, “A”, “B”, “C”, “F”, or “F-”)
	@NotNull
	private Mark				mark;

	private boolean				draftMode;

	//optional link with further information
	@URL
	private String				link;

	//Relationships
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private CodeAudit			codeAudit;

}
