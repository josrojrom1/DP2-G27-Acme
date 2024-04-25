
package acme.features.client.progressLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
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

		id = super.getRequest().getData("id", int.class);
		pLog = this.repository.findProgressLogById(id);
		client = pLog == null ? null : pLog.getContract().getClient();
		status = super.getRequest().getPrincipal().hasRole(client) && pLog != null && client.getId() == super.getRequest().getPrincipal().getActiveRoleId();
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

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "contract.code", "published");

		super.getResponse().addData(dataset);
	}

}
