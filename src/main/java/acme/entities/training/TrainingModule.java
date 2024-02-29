
package acme.entities.training;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	//TODO: COMO HAGO PARA VERIFICAR QUE SE CREE DESPUÉS DEL CREATIONMOMENT???
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				updateMoment;

	//optional link with further information
	@URL
	private String				link;

	//estimated total time
	@NotNull
	private Date				totalTime;

	//Relationships
	//TODO: HACER UNA RELACION CON PROJECT
}
