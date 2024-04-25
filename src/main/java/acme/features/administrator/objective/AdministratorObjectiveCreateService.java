/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (C) 2012-2024 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.objective;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.helpers.PrincipalHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.objectives.Objective;
import acme.entities.objectives.Priority;

@Service
public class AdministratorObjectiveCreateService extends AbstractService<Administrator, Objective> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorObjectiveRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void authorise() {
		boolean status;
		status = super.getRequest().getPrincipal().hasRole(Administrator.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Objective object;

		object = new Objective();

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Objective object) {
		assert object != null;

		super.bind(object, "instantiationMoment", "title", "description", "priority", "critical", "executionPeriodStart", "executionPeriodFinish", "link");
	}

	@Override
	public void validate(final Objective object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("executionPeriodStart"))
			super.state(MomentHelper.isAfter(object.getExecutionPeriodStart(), object.getInstantiationMoment()), "executionPeriodStart", "administrator.objective.form.error.inlavid-period-start-date");
		if (!super.getBuffer().getErrors().hasErrors("executionPeriodFinish"))
			super.state(MomentHelper.isAfter(object.getExecutionPeriodFinish(), object.getExecutionPeriodStart()), "executionPeriodFinish", "administrator.objective.form.error.inlavid-period-finish-date");

		boolean isAccepted = this.getRequest().getData("accept", boolean.class);
		super.state(isAccepted, "accept", "administrator.objective.form.error.must-accept");
	}

	@Override
	public void perform(final Objective object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Objective object) {
		Dataset dataset;

		SelectChoices priorityChoices;
		priorityChoices = SelectChoices.from(Priority.class, object.getPriority());
		dataset = super.unbind(object, "instantiationMoment", "title", "description", "priority", "critical", "executionPeriodStart", "executionPeriodFinish", "link");
		dataset.put("priority", priorityChoices.getSelected().getKey());
		dataset.put("priorities", priorityChoices);

		if (super.getRequest().getMethod().equals("POST"))
			dataset.put("accept", super.getRequest().getData("accept", boolean.class));
		else
			dataset.put("accept", "false");

		super.getResponse().addData(dataset);
	}

	@Override
	public void onSuccess() {
		if (super.getRequest().getMethod().equals("POST"))
			PrincipalHelper.handleUpdate();
	}

}
