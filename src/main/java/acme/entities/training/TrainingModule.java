
package acme.entities.training;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.entities.projects.Project;
import acme.roles.Developer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "code")
})
public class TrainingModule extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	//code (pattern “[A-Z]{1,3}-[0-9]{3}”, not blank, unique)
	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}-\\d{3}")
	@Column(unique = true)
	private String				code;

	//creation moment (in the past)
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				creationMoment;

	//details describing the training module (not blank, shorter than 101 characters)
	@NotBlank
	@Length(max = 100)
	private String				details;

	//difficulty level (“Basic”, “Intermediate”, or “Advanced”)
	@NotNull
	private DifficultyLevel		difficultyLevel;

	//update moment (in the past, after the creation moment)
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				updateMoment;

	//optional link with further information
	@URL
	@Length(max = 255)
	private String				link;

	//estimated total time
	@NotNull
	@Min(1)
	@Max(10000)
	private int					totalTime;

	private boolean				draftMode;

	//Relationships
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Developer			developer;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Project				project;
}
