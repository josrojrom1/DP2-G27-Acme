
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
	private Date				instalationOrUpdateMoment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	// Debe comenzar en cualquier momento después del momento de 
	// instanciación/ actualización y debe durar al menos una semana
	private Date				displayPeriod;

	@NotNull
	@URL
	private String				picture;

	@NotNull
	@NotBlank
	@Length(max = 75)
	private String				slogan;

	@NotNull
	@URL
	private String				webDocument;

	// Derived attributes

}
