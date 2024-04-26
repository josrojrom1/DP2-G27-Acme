
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;
import acme.features.manager.project.ManagerProjectRepository;
import acme.features.manager.projectUserStory.ManagerProjectUserStoryRepository;
import acme.roles.Manager;

@Service
public class ManagerUserStoryListByProjectService extends AbstractService<Manager, UserStory> {

	@Autowired
	protected ManagerUserStoryRepository		repository;
	@Autowired
	private ManagerProjectUserStoryRepository	pUSRepository;
	@Autowired
	private ManagerProjectRepository			pRepository;


	@Override
	public void authorise() {
		Boolean status;
		int masterId;
		Project project;

		masterId = super.getRequest().getData("masterId", int.class);
		project = this.pRepository.findProjectById(masterId);
		status = project != null && super.getRequest().getPrincipal().hasRole(Manager.class) && super.getRequest().getPrincipal().getActiveRoleId() == project.getManager().getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<UserStory> objects;
		int projectId;

		projectId = super.getRequest().getData("masterId", int.class);
		objects = this.pUSRepository.getUserStoryByProject(projectId);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final UserStory object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "title", "estimatedCost", "priority");

		super.getResponse().addData(dataset);
	}

	@Override
	public void unbind(final Collection<UserStory> objects) {
		assert objects != null;

		int masterId;
		Project project;
		final boolean showCreate;

		masterId = super.getRequest().getData("masterId", int.class);
		project = this.pRepository.findProjectById(masterId);
		showCreate = project.isDraftMode() && super.getRequest().getPrincipal().getActiveRoleId() == project.getManager().getId();

		super.getResponse().addGlobal("masterId", masterId);
		super.getResponse().addGlobal("showCreate", showCreate);

	}

}
