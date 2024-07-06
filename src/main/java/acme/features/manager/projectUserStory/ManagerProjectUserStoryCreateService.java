
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectUserStory;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Service
public class ManagerProjectUserStoryCreateService extends AbstractService<Manager, ProjectUserStory> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectUserStoryRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Project project;
		Manager manager;

		int id = super.getRequest().getData("masterId", int.class);
		project = this.repository.findProjectById(id);
		manager = project == null ? null : project.getManager();
		status = project != null && super.getRequest().getPrincipal().hasRole(manager) && project.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ProjectUserStory object;

		int id = super.getRequest().getData("masterId", int.class);
		Project project = this.repository.findProjectById(id);

		object = new ProjectUserStory();
		object.setProject(project);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final ProjectUserStory object) {
		assert object != null;

		int id = super.getRequest().getData("userStory", int.class);
		UserStory userStory = this.repository.findUserStoryById(id);

		super.bind(object, "userStory");
		object.setUserStory(userStory);

	}

	@Override
	public void validate(final ProjectUserStory object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("userStory")) {
			Project project = object.getProject();
			UserStory userStory = object.getUserStory();
			super.state(userStory.getManager().getId() == project.getManager().getId(), "userStory", "manager.project.form.error.same-manager");
		}
	}

	@Override
	public void perform(final ProjectUserStory object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final ProjectUserStory object) {
		assert object != null;

		int id = super.getRequest().getData("masterId", int.class);
		Project project = this.repository.findProjectById(id);

		Dataset dataset;
		SelectChoices userStoryChoices;
		Collection<UserStory> userStories = this.repository.findUserStoryByManager(project.getManager().getId()).stream().filter(us -> this.repository.getRelationsByProject(id).stream().map(ProjectUserStory::getUserStory).noneMatch(us2 -> us2.equals(us))) //
			.toList();

		userStoryChoices = SelectChoices.from(userStories, "title", object.getUserStory());

		dataset = super.unbind(object, "userStory");
		dataset.put("userStory", userStoryChoices.getSelected().getKey());
		dataset.put("userStories", userStoryChoices);
		dataset.put("projectId", id);

		super.getResponse().addData(dataset);
	}

}
