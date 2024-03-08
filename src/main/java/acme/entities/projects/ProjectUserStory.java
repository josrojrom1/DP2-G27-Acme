
package acme.entities.projects;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class ProjectUserStory extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@ManyToOne(optional = false)
	private Project				project;

	@ManyToOne(optional = false)
	private UserStory			userStory;

}
