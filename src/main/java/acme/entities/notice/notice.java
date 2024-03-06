
package acme.entities.notice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
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
public class notice extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	//an instantiation moment (in the past)
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				instantiationMoment;

	//a title (not blank, shorter than 76 characters)
	@NotBlank
	@Length(max = 75)
	private String				title;

	//an author (not blank, shorter than 76 characters)
	@NotBlank
	@Length(max = 75)
	private String				author;

	//a message (not blank, shorter than 101 characters)
	@NotBlank
	@Length(max = 100)
	private String				message;

	//an optional email address
	@Email
	private String				email;

	//an optional link
	@URL
	private String				link;

	//TODO:relationships
	/*
	 * @NotNull
	 * 
	 * @Valid
	 * 
	 * @ManyToOne(optional = false)
	 * private Principal principal;
	 */

}
