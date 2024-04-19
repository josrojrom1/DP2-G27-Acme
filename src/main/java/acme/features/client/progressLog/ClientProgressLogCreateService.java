
package acme.features.client.progressLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogCreateService extends AbstractService<Client, ProgressLog> {

	@Autowired
	ClientProgressLogRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		ProgressLog object;
		int masterId;
		Contract contract;

		masterId = super.getRequest().getData("masterId", int.class);
		contract = this.repository.findContractById(masterId);
		object = new ProgressLog();
		object.setPublished(false);
		object.setContract(contract);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final ProgressLog object) {
		assert object != null;

		int contractId;
		Contract contract;

		contractId = super.getRequest().getData("masterId", int.class);
		contract = this.repository.findContractById(contractId);

		super.bind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "contract");
		object.setContract(contract);
	}

	@Override
	public void validate(final ProgressLog object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("recordId")) {
			ProgressLog existing;

			existing = this.repository.findProgressLogByRecordId(object.getRecordId());
			super.state(existing == null, "recordId", "client.progress-log.form.error.duplicated");
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
		dataset.put("contract.code", object.getContract().getCode());

		super.getResponse().addData(dataset);
	}

}
