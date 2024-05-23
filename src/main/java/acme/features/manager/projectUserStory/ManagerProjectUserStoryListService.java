
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectUserStory;
import acme.roles.Manager;

@Service
public class ManagerProjectUserStoryListService extends AbstractService<Manager, ProjectUserStory> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectUserStoryRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int projectId;
		Project project;
		Manager manager;

		projectId = super.getRequest().getData("id", int.class);
		project = this.repository.findProjectById(projectId);
		manager = project == null ? null : project.getManager();

		status = super.getRequest().getPrincipal().hasRole(manager) && project != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int projectId = super.getRequest().getData("id", int.class);
		Collection<ProjectUserStory> objects = this.repository.getRelationsByProject(projectId);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final ProjectUserStory object) {
		assert object != null;

		Dataset dataset = new Dataset();
		dataset.put("code", object.getProject().getCode());
		dataset.put("title", object.getUserStory().getTitle());

		super.getResponse().addData(dataset);
	}
}
