
package acme.entities.training;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TrainingSessions extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	//code (pattern “TS-[A-Z]{1,3}-[0-9]{3}”, not blank, unique)
	@NotBlank
	@Pattern(regexp = "TS-[A-Z]{1,3}-\\d{3}")
	@Column(unique = true)
	private String				code;

	//time period (at least one week ahead the training module creation moment, at least one week long)
	//TODO:Service
	@NotNull
	private Date				periodStart;

	@NotNull
	private Date				periodFinish;

	//location (not blank, shorter than 76 characters)
	@NotBlank
	@Length(max = 75)
	private String				location;

	//instructor (not blank, shorter than 76 characters)
	@NotBlank
	@Length(max = 75)
	private String				instructor;

	//mandatory contact email
	@Email
	@NotBlank
	@Length(max = 255)
	private String				contactEmail;

	//optional link with further information
	@URL
	@Length(max = 255)
	private String				link;

	private boolean				draftMode;

	//Relationships
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private TrainingModule		trainingModule;
}
