
package acme.entities.objectives;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Objective extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				instantiationMoment;

	@NotBlank
	@Length(max = 76)
	private String				title;

	@NotBlank
	@Length(max = 101)
	private String				description;

	@NotNull
	private Priority			priority;

	// para indicar si es crítico o no, true indica crítico
	private boolean				critical;

	@NotNull
	private Date				executionPeriodStart; // hay que verlo bien

	@NotNull
	private Date				executionPeriodFinish;

	@URL
	private String				link;

	/*
	 * @Valid
	 * 
	 * @ManyToOne(optional = false)
	 * 
	 * @NotNull
	 * private Project project;
	 */

}
