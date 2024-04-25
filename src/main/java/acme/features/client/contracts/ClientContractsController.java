
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

	@Autowired
	private ClientContractsDeleteService	deleteService;

	@Autowired
	private ClientContractsPublishService	publishService;

	@Autowired
	private ClientContractsUpdateService	updateService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("update", this.updateService);

		super.addCustomCommand("list-mine", "list", this.listMineService);
		super.addCustomCommand("publish", "update", this.publishService);
	}

}
