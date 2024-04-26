
package acme.features.any.claim;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Any;
import acme.entities.claims.Claim;

@Controller
public class AnyClaimsController extends AbstractController<Any, Claim> {

	@Autowired
	private AnyClaimsListService	listService;

	@Autowired
	private AnyClaimsShowService	showService;

	@Autowired
	private AnyClaimsCreateService	createService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
	}
}
