
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsor extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@NotBlank
	@Length(max = 75)
	private String				name;

	@NotNull
	@NotBlank
	@Length(max = 100)
	private String				benefits;

	@URL
	@Length(max = 255)
	private String				webPage;

	@Email
	private String				contact;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
