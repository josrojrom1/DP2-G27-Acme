
package acme.features.manager.projectUserStory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.ProjectUserStory;
import acme.roles.Manager;

@Service
public class ManagerProjectUserStoryShowService extends AbstractService<Manager, ProjectUserStory> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectUserStoryRepository repository;

	// AbstractService<Authenticated, Assignment> ---------------------------


	@Override
	public void authorise() {
		boolean status;
		ProjectUserStory projectUserStory;
		Manager manager;

		int id = super.getRequest().getData("id", int.class);
		projectUserStory = this.repository.findProjectUserStoryById(id);

		manager = projectUserStory == null ? null : projectUserStory.getProject().getManager();

		status = projectUserStory != null && super.getRequest().getPrincipal().hasRole(manager);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		ProjectUserStory projectUserStory = this.repository.findProjectUserStoryById(id);

		super.getBuffer().addData(projectUserStory);
	}

	@Override
	public void unbind(final ProjectUserStory object) {
		assert object != null;

		Dataset dataset = super.unbind(object, "userStory", "project");
		dataset.put("title", object.getUserStory().getTitle());
		dataset.put("code", object.getProject().getCode());
		dataset.put("draftMode", object.getProject().isDraftMode());

		super.getResponse().addData(dataset);
	}

}
