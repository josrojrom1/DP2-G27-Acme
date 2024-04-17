
package acme.features.client.contracts;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contracts.Contract;
import acme.entities.projects.Project;
import acme.roles.Client;

@Service
public class ClientContractsCreateService extends AbstractService<Client, Contract> {

	@Autowired
	ClientContractsRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Contract object;
		Client client;

		client = this.repository.findClientById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Contract();
		object.setPublished(false);
		object.setClient(client);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Contract object) {
		assert object != null;

		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findProjectById(projectId);

		super.bind(object, "code", "instantiationMoment", "provider", "customer", "goals", "budget", "project");
		object.setProject(project);
	}

	@Override
	public void validate(final Contract object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Contract existing;

			existing = this.repository.findContractByCode(object.getCode());
			super.state(existing == null, "code", "client.contract.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("budget")) {
			double projectCost;
			double budget;

			budget = object.getBudget().getAmount();
			projectCost = object.getProject().getCost().getAmount();
			super.state(budget <= projectCost, "budget", "client.contract.form.error.incorrect-budget");
		}

	}

	@Override
	public void perform(final Contract object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Contract object) {
		assert object != null;

		Dataset dataset;
		Collection<Project> projects;
		SelectChoices choices;

		projects = this.repository.findAllProjects();
		choices = SelectChoices.from(projects, "code", object.getProject());

		dataset = super.unbind(object, "code", "instantiationMoment", "provider", "customer", "goals", "budget", "published");
		dataset.put("client", object.getClient().getIdentification());
		dataset.put("project", choices.getSelected());
		dataset.put("projects", choices);

		super.getResponse().addData(dataset);
	}

}
