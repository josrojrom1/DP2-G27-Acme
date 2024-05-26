
package acme.features.client.contracts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.roles.Client;

@Service
public class ClientContractsListMineService extends AbstractService<Client, Contract> {

	@Autowired
	ClientContractsRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Principal principal;
		List<Contract> contracts;

		principal = super.getRequest().getPrincipal();
		contracts = this.repository.findContractsByClientId(principal.getActiveRoleId());

		super.getBuffer().addData(contracts);
	}

	@Override
	public void unbind(final Contract object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "instantiationMoment", "budget", "project.code");

		if (this.repository.findContractById(object.getId()).isPublished())
			dataset.put("published", "✔");
		else
			dataset.put("published", "✖");

		super.getResponse().addData(dataset);
	}

}
