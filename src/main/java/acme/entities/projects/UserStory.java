
package acme.entities.projects;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.client.data.AbstractEntity;
import acme.roles.Manager;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class UserStory extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@ManyToOne
	private Manager				manager;

	@NotBlank
	@Length(max = 76)
	private String				title;

	@NotBlank
	@Length(max = 101)
	private String				description;

	@Min(0)
	@NotNull
	private Double				estimatedCost;

	@NotNull
	private String				acceptanceCriteria;

	@NotNull
	private PriorityEnum		priority;

	private String				link;

	@NotNull
	@ManyToOne
	private Project				project;
}
