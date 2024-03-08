
package acme.entities.banner;

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
public class Banner extends AbstractEntity {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				instantiationOrUpdateMoment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	// Must start at any moment after the instantiation/update moment
	private Date				displayStartDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	// Must be at least one week after displayStartDate
	private Date				displayEndDate;

	@NotBlank
	private String				picture;

	@NotBlank
	@Length(max = 75)
	private String				slogan;

	@NotBlank
	@URL
	private String				webDocument;

}
