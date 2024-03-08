
package acme.entities.audits;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.entities.projects.Project;
import acme.roles.Auditor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CodeAudit extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	//Code (pattern "[A-Z]{1,3}-[0,9]{3}", not blank, unique)
	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}-\\d{3}")
	@Column(unique = true)
	private String				code;

	//execution date (in the past)
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				execution;

	//type (“Static”, “Dynamic”) -> Enumerate
	@NotNull
	private Type				type;

	//list of proposed corrective actions (not blank, shorter than 101 characters)
	@NotBlank
	@Length(max = 100)
	private String				correctiveActions;

	//mark (computed as the mode of the marks in the corresponding auditing records; ties must be broken arbitrarily if necessary)
	@Transient
	private Mark				mark; //Se ha dejado como String al ser depentiente del método que se construya en el servicio correspondiente en el futuro

	//optional link with further information.
	@URL
	private String				link;

	//Relationships
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Auditor				auditor;

	//Falta NotNull aquí, esperando a que se cree el csv de Project para meterlo como key
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Project				project;

}
