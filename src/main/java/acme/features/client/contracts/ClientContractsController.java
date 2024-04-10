
package acme.features.client.contracts;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.contracts.Contract;
import acme.roles.Client;

@Controller
public class ClientContractsController extends AbstractController<Client, Contract> {

	@Autowired
	private ClientContractsListMineService	listMineService;

	@Autowired
	private ClientContractsShowService		showService;

	@Autowired
	private ClientContractsCreateService	createService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addCustomCommand("list-mine", "list", this.listMineService);
	}

}
