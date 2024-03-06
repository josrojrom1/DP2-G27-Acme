
package acme.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Client extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "CLI-\\d{4}")
	@Column(unique = true)
	private String				identification;

	@NotBlank
	@Length(max = 76)
	private String				companyName;

	@NotNull
	private ClientType			type;

	@Email
	@NotBlank
	private String				email;

	@URL
	private String				link;

}
