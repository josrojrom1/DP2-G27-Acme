
package acme.features.auditor.codeAudit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.audits.CodeAudit;
import acme.roles.Auditor;

@Controller
public class AuditorCodeAuditController extends AbstractController<Auditor, CodeAudit> {

	@Autowired
	private AuditorCodeAuditListMineService	listService;

	@Autowired
	private AuditorCodeAuditShowService		showService;

	@Autowired
	private AuditorCodeAuditCreateService	createService;

	@Autowired
	private AuditorCodeAuditDeleteService	deleteService;

	@Autowired
	private AuditorCodeAuditUpdateService	updateService;

	@Autowired
	private AuditorCodeAuditPublishService	publishService;


	@PostConstruct
	protected void initialise() {

		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("update", this.updateService);

		super.addCustomCommand("list-mine", "list", this.listService);
		super.addCustomCommand("publish", "update", this.publishService);
	}
}
