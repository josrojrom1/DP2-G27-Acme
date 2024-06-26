
package acme.features.any.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;

@Service
public class AnyContractShowService extends AbstractService<Any, Contract> {

	@Autowired
	private AnyContractRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		Contract contract;

		id = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(id);
		status = contract != null && contract.isPublished();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int contractId;
		Contract contract;

		contractId = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(contractId);

		super.getBuffer().addData(contract);
	}

	@Override
	public void unbind(final Contract object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "instantiationMoment", "provider", "customer", "goals", "budget", "project.code", "published");

		super.getResponse().addData(dataset);
	}

}
