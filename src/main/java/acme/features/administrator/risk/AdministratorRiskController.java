
package acme.features.administrator.risk;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Administrator;
import acme.entities.risk.Risk;

@Controller
public class AdministratorRiskController extends AbstractController<Administrator, Risk> {

	@Autowired
	private AdministratorRiskShowService	showService;

	@Autowired
	private AdministratorRiskListService	listService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}
}
