
package acme.features.client.progressLog;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogPublishService extends AbstractService<Client, ProgressLog> {

	@Autowired
	ClientProgressLogRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int objectId;
		ProgressLog object;
		Client client;
		Contract contract;

		objectId = super.getRequest().getData("id", int.class);
		object = this.repository.findProgressLogById(objectId);
		contract = object.getContract();
		client = object.getContract().getClient();
		status = !object.isPublished() && contract.isPublished() && super.getRequest().getPrincipal().hasRole(client) && super.getRequest().getPrincipal().getActiveRoleId() == client.getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ProgressLog object;
		int objectId;

		objectId = super.getRequest().getData("id", int.class);
		object = this.repository.findProgressLogById(objectId);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final ProgressLog object) {
		assert object != null;

		super.bind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson");
	}

	@Override
	public void validate(final ProgressLog object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			String code;
			ProgressLog existing;

			code = object.getRecordId();
			existing = this.repository.findProgressLogByRecordId(code);
			super.state(existing == null || object.getId() == existing.getId(), "recordId", "client.progress-log.form.error.recordId");
		}

		if (!super.getBuffer().getErrors().hasErrors("completeness")) {
			ProgressLog maxCompleteness;
			ProgressLog existing;

			existing = this.repository.findProgressLogById(object.getId());
			maxCompleteness = this.repository.findProgressLogWithMaxCompleteness(existing.getContract().getId());
			if (maxCompleteness != null)
				super.state(maxCompleteness.getCompleteness() < object.getCompleteness(), "completeness", "client.progress-log.form.error.completeness");
		}

		if (!super.getBuffer().getErrors().hasErrors("registrationMoment")) {
			ProgressLog maxCompleteness;
			Date objectDate;
			ProgressLog existing;

			existing = this.repository.findProgressLogById(object.getId());
			maxCompleteness = this.repository.findProgressLogWithMaxCompleteness(existing.getContract().getId());
			objectDate = this.repository.findProgressLogById(object.getId()).getRegistrationMoment();
			if (maxCompleteness != null && !maxCompleteness.getRegistrationMoment().equals(objectDate))
				super.state(maxCompleteness.getRegistrationMoment().before(objectDate), "registrationMoment", "client.progress-log.form.error.registration-moment");
		}

		if (!super.getBuffer().getErrors().hasErrors("published")) {
			ProgressLog existing;

			existing = this.repository.findProgressLogByRecordId(object.getRecordId());
			super.state(!existing.isPublished(), "published", "client.progress-log.form.error.published");
		}

	}

	@Override
	public void perform(final ProgressLog object) {
		assert object != null;

		ProgressLog pLog;

		pLog = this.repository.findProgressLogById(object.getId());
		pLog.setPublished(true);

		this.repository.save(pLog);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "published");

		super.getResponse().addData(dataset);
	}

}
