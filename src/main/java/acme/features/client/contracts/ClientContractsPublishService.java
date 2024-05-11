
package acme.features.client.contracts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.projects.Project;
import acme.roles.Client;

@Service
public class ClientContractsPublishService extends AbstractService<Client, Contract> {

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

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			String code;
			Contract existing;

			code = object.getCode();
			existing = this.repository.findContractByCode(code);
			super.state(existing == null || object.getId() == existing.getId(), "code", "client.contract.form.error.code");
		}

		if (!super.getBuffer().getErrors().hasErrors("budget"))
			super.state(object.getBudget() != null, "budget", "client.contract.form.error.null-budget");

		if (!super.getBuffer().getErrors().hasErrors("budget")) {
			double budget;

			budget = object.getBudget().getAmount();
			super.state(budget > 0, "budget", "client.contract.form.error.negative-budget");
		}

		if (object.getProject() != null) {
			if (!super.getBuffer().getErrors().hasErrors("budget")) {
				String projectCurrency;
				String budgetCurrency;

				budgetCurrency = object.getBudget().getCurrency();
				projectCurrency = object.getProject().getCost().getCurrency();
				super.state(budgetCurrency.equals(projectCurrency), "budget", "client.contract.form.error.incorrect-currency");
			}

			if (!super.getBuffer().getErrors().hasErrors("budget")) {
				double projectCost;
				double budget;

				budget = object.getBudget().getAmount();
				projectCost = object.getProject().getCost().getAmount();
				super.state(budget <= projectCost, "budget", "client.contract.form.error.incorrect-budget");
			}
		}

		double budgetsSum;
		double projectCost;

		projectCost = object.getProject().getCost().getAmount();
		budgetsSum = this.repository.findManyContractsByProjectId(object.getProject().getId()).stream().mapToDouble(c -> c.getBudget().getAmount()).sum();
		super.state(budgetsSum <= projectCost, "*", "client.contract.form.error.not-published");

	}

	@Override
	public void perform(final Contract object) {
		assert object != null;

		Contract contract;

		contract = this.repository.findContractById(object.getId());
		contract.setPublished(true);

		this.repository.save(contract);
	}

	@Override
	public void unbind(final Contract object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "instantiationMoment", "provider", "customer", "goals", "budget", "published");
		dataset.put("client", object.getClient().getIdentification());
		dataset.put("project.code", object.getProject().getCode());

		super.getResponse().addData(dataset);
	}

}
