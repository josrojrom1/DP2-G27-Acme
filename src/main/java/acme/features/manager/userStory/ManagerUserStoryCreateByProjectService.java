
package acme.features.manager.userStory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.PriorityEnum;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectUserStory;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Service
public class ManagerUserStoryCreateByProjectService extends AbstractService<Manager, UserStory> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerUserStoryRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		Boolean status;
		int masterId;
		Project project;
		Manager manager;

		masterId = super.getRequest().getData("masterId", int.class);
		project = this.repository.findProjectById(masterId);
		manager = project == null ? null : project.getManager();
		status = project != null && super.getRequest().getPrincipal().hasRole(manager) && project.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		UserStory object;

		int id = super.getRequest().getPrincipal().getActiveRoleId();
		Manager manager = this.repository.findManagerById(id);

		object = new UserStory();
		object.setManager(manager);
		object.setDraftMode(true);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final UserStory object) {
		assert object != null;

		super.bind(object, "manager", "title", "description", "estimatedCost", "acceptanceCriteria", "priority", "link");
	}

	@Override
	public void validate(final UserStory object) {
		assert object != null;

	}

	@Override
	public void perform(final UserStory object) {
		assert object != null;

		int masterId;
		Project project;

		masterId = super.getRequest().getData("masterId", int.class);
		project = this.repository.findProjectById(masterId);

		this.repository.save(object);

		ProjectUserStory relation = new ProjectUserStory();

		relation.setProject(project);
		relation.setUserStory(object);

		this.repository.save(relation);
	}

	@Override
	public void unbind(final UserStory object) {
		assert object != null;

		int masterId = super.getRequest().getData("masterId", int.class);

		Dataset dataset;
		SelectChoices priorityChoices;
		priorityChoices = SelectChoices.from(PriorityEnum.class, object.getPriority());

		dataset = super.unbind(object, "manager", "title", "description", "estimatedCost", "acceptanceCriteria", "priority", "link");
		dataset.put("priorities", priorityChoices);
		dataset.put("masterId", masterId);

		super.getResponse().addData(dataset);
	}

}
