
package acme.features.client.progressLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogUpdateService extends AbstractService<Client, ProgressLog> {

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
		client = object == null ? null : contract.getClient();
		status = object != null && !object.isPublished() && !contract.isPublished() && super.getRequest().getPrincipal().hasRole(client) && super.getRequest().getPrincipal().getActiveRoleId() == client.getId();

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

		ProgressLog pLog;
		int objectId;

		objectId = super.getRequest().getData("id", int.class);
		pLog = this.repository.findProgressLogById(objectId);

		super.bind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "contract");
		object.setContract(pLog.getContract());
	}

	@Override
	public void validate(final ProgressLog object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("published")) {
			ProgressLog existing;

			existing = this.repository.findProgressLogByRecordId(object.getRecordId());
			super.state(!existing.isPublished(), "published", "client.progress-log.form.error.published");
		}

	}

	@Override
	public void perform(final ProgressLog object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "published");
		dataset.put("contract", object.getContract());

		super.getResponse().addData(dataset);
	}

}
