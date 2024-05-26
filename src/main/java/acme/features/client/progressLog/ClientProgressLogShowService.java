
package acme.features.client.progressLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogShowService extends AbstractService<Client, ProgressLog> {

	@Autowired
	ClientProgressLogRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		ProgressLog pLog;
		Client client;
		Contract contract;

		id = super.getRequest().getData("id", int.class);
		pLog = this.repository.findProgressLogById(id);
		contract = pLog.getContract();
		client = pLog.getContract().getClient();
		status = super.getRequest().getPrincipal().hasRole(client) && contract.isPublished() && pLog != null && client.getId() == super.getRequest().getPrincipal().getActiveRoleId();
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ProgressLog object;
		int pLogId;

		pLogId = super.getRequest().getData("id", int.class);
		object = this.repository.findProgressLogById(pLogId);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "published");

		super.getResponse().addData(dataset);
	}

}
