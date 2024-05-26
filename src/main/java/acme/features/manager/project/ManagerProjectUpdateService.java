
package acme.features.manager.project;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectUpdateService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Project project;

		int id = super.getRequest().getData("id", int.class);
		project = this.repository.findProjectById(id);

		Manager manager = project == null ? null : project.getManager();
		status = project != null && super.getRequest().getPrincipal().hasRole(manager) && project.isDraftMode();
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Project object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findProjectById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Project object) {
		assert object != null;

		super.bind(object, "code", "title", "description", "indication", "cost", "link");
	}

	@Override
	public void validate(final Project object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Project existing = this.repository.findProjectByCode(object.getCode());
			super.state(existing == null || existing.equals(object), "code", "manager.project.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("cost")) {
			final Money cost = object.getCost();

			super.state(cost.getAmount() >= 0, "cost", "manager.project.form.error.negative-amount");
			super.state(cost.getAmount() <= 1000000, "cost", "manager.project.form.error.too-big");

			final List<String> acceptedCurrencies = Arrays.asList(this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",")).stream().map(String::trim).collect(Collectors.toList());
			super.state(acceptedCurrencies.contains(cost.getCurrency()), "cost", "manager.project.form.error.currency");
		}
	}

	@Override
	public void perform(final Project object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Project object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "title", "description", "indication", "draftMode", "cost", "link");

		super.getResponse().addData(dataset);
	}
}
