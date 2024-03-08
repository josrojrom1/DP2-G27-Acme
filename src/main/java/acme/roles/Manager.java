
package acme.roles;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class Manager extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(max = 76)
	private String				degree;

	@NotBlank
	@Length(max = 101)
	private String				overview;

	@NotBlank
	@Length(max = 101)
	private String				certifications;

	@URL
	private String				link;

}
