
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;
import acme.features.manager.projectUserStory.ManagerProjectUserStoryRepository;
import acme.roles.Manager;

@Service
public class ManagerProjectPublishService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository			repository;
	@Autowired
	private ManagerProjectUserStoryRepository	pUSRepository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Project project;
		int id = super.getRequest().getData("id", int.class);
		project = this.repository.findProjectById(id);
		status = project != null && super.getRequest().getPrincipal().hasRole(Manager.class) && super.getRequest().getPrincipal().getActiveRoleId() == project.getManager().getId() && project.isDraftMode();
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Project object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findProjectById(id);

		object.setDraftMode(false);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Project object) {
		assert object != null;

		super.bind(object, "code", "title", "description", "indication", "draftMode", "cost", "link");
	}

	@Override
	public void validate(final Project object) {
		assert object != null;

		Collection<UserStory> userStories = this.pUSRepository.getUserStoryByProject(object.getId());

		if (!super.getBuffer().getErrors().hasErrors("draftMode"))
			super.state(userStories.stream().allMatch(x -> !x.getDraftMode()), "draftMode", "manager.user-story.publish.error.no-publisheds");

		if (!super.getBuffer().getErrors().hasErrors("draftMode"))
			super.state(userStories.size() >= 1, "draftMode", "manager.user-story.publish.error.no-userStories");

		if (!super.getBuffer().getErrors().hasErrors("indication"))
			super.state(!object.isIndication(), "indication", "manager.user-story.publish.error.no-errors");
	}

	@Override
	public void perform(final Project object) {
		assert object != null;

		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Project object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "title", "description", "indication", "draftMode", "cost", "link");
	}
}
