
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.ProjectUserStory;
import acme.entities.projects.UserStory;
import acme.features.manager.projectUserStory.ManagerProjectUserStoryRepository;
import acme.roles.Manager;

@Service
public class ManagerUserStoryDeleteService extends AbstractService<Manager, UserStory> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerUserStoryRepository			repository;
	@Autowired
	private ManagerProjectUserStoryRepository	pUSRepository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		UserStory userStory;
		int id = super.getRequest().getData("id", int.class);
		userStory = this.repository.findUserStoryById(id);
		status = userStory != null && super.getRequest().getPrincipal().hasRole(Manager.class) && super.getRequest().getPrincipal().getActiveRoleId() == userStory.getManager().getId() && userStory.getDraftMode() == true;
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		UserStory object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findUserStoryById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final UserStory object) {
		assert object != null;

		super.bind(object, "manager", "title", "description", "estimatedCost", "acceptanceCriteria", "priority", "draftMode", "link");
	}

	@Override
	public void validate(final UserStory object) {
		assert object != null;
	}

	@Override
	public void perform(final UserStory object) {
		assert object != null;

		Collection<ProjectUserStory> projectUserStory;

		projectUserStory = this.pUSRepository.getRelationsByUserStory(object.getId());

		this.pUSRepository.deleteAll(projectUserStory);
		this.repository.delete(object);
	}

	@Override
	public void unbind(final UserStory object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "manager", "title", "description", "estimatedCost", "acceptanceCriteria", "priority", "draftMode", "link");
	}
}
