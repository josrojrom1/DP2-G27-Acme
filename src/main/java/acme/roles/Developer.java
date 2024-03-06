
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
public class Developer extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	//degree (not blank, shorter than 76 characters)
	@NotBlank
	@Length(max = 75)
	private String				degree;

	//specialisation (not blank, shorter than 101 characters)
	@NotBlank
	@Length(max = 100)
	private String				specialisation;

	//list of skills (not blank, shorter than 101 characters)
	//TODO: DEBE SER UNA LISTA O CON SER UN STRING SIRVE???
	@NotBlank
	@Length(max = 100)
	private String				listSkills;

	//email
	@NotNull
	@Email
	private String				email;

	//optional link with further information
	@URL
	private String				link;
}
