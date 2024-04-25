
package acme.features.client.contracts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.roles.Client;

@Service
public class ClientContractsShowService extends AbstractService<Client, Contract> {

	@Autowired
	private ClientContractsRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
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
