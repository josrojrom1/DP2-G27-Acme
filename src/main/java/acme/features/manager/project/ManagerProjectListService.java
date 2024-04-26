
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectListService extends AbstractService<Manager, Project> {

	@Autowired
	protected ManagerProjectRepository repository;


	@Override
	public void authorise() {
		Boolean status;

		status = super.getRequest().getPrincipal().hasRole(Manager.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<Project> objects;

		int managerId = super.getRequest().getPrincipal().getActiveRoleId();
		objects = this.repository.getProjectsByManager(managerId);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final Project object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "title", "cost");

		super.getResponse().addData(dataset);
	}

}
