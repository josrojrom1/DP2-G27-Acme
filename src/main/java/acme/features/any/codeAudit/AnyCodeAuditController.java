
package acme.features.any.codeAudit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Any;
import acme.entities.audits.CodeAudit;

@Controller
public class AnyCodeAuditController extends AbstractController<Any, CodeAudit> {

	@Autowired
	private AnyCodeAuditListService	listService;

	@Autowired
	private AnyCodeAuditShowService	showService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}

}
