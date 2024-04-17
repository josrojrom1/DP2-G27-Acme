
package acme.features.client.contracts;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.entities.projects.Project;
import acme.roles.Client;

@Service
public class ClientContractsDeleteService extends AbstractService<Client, Contract> {

	@Autowired
	ClientContractsRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int contractId;
		Contract contract;
		Client client;

		contractId = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(contractId);
		client = contract == null ? null : contract.getClient();
		status = contract != null && !contract.isPublished() && super.getRequest().getPrincipal().hasRole(client) && super.getRequest().getPrincipal().getActiveRoleId() == client.getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Contract object;
		int contractId;

		contractId = super.getRequest().getData("id", int.class);
		object = this.repository.findContractById(contractId);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Contract object) {
		assert object != null;

		int projectId;
		Project project;

		projectId = object.getProject().getId();
		project = this.repository.findProjectById(projectId);

		super.bind(object, "code", "instantiationMoment", "provider", "customer", "goals", "budget", "project");
		object.setProject(project);
	}

	@Override
	public void validate(final Contract object) {
		assert object != null;
	}

	@Override
	public void perform(final Contract object) {
		assert object != null;

		Collection<ProgressLog> progLogs;

		progLogs = this.repository.findProgressLogsByContractId(object.getId());
		this.repository.deleteAll(progLogs);
		this.repository.delete(object);
	}

	@Override
	public void unbind(final Contract object) {
		assert object != null;

		Dataset dataset;
		Collection<Project> projects;
		SelectChoices choices;

		projects = this.repository.findAllProjects();
		choices = SelectChoices.from(projects, "code", object.getProject());

		dataset = super.unbind(object, "code", "instantiationMoment", "provider", "customer", "goals", "budget", "draftMode", "published");
		dataset.put("client", object.getClient().getIdentification());
		dataset.put("project", choices.getSelected());
		dataset.put("projects", choices);

		super.getResponse().addData(dataset);
	}

}
