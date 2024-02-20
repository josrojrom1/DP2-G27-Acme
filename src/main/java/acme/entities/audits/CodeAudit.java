
package acme.entities.audits;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;

public class CodeAudit extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	//Code (pattern "[A-Z]{1,3}-[0,9]{3}", not blank, unique)
	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}-d{3}")
	@Column(unique = true)
	private String				code;

	//execution date (in the past)
	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent
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
	private Mark				mark;

	//optional link with further information.
	@URL
	private String				link;

}
