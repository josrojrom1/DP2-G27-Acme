
package acme.features.auditor.auditRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.audits.AuditRecord;
import acme.roles.Auditor;

@Controller
public class AuditorAuditRecordController extends AbstractController<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordListService	listService;

	@Autowired
	private AuditorAuditRecordShowService	showService;

	@Autowired
	private AuditorAuditRecordCreateService	createService;

	@Autowired
	private AuditorAuditRecordDeleteService	deleteService;

	@Autowired
	private AuditorAuditRecordUpdateService	updateService;


	@PostConstruct
	protected void initialise() {

		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("update", this.updateService);
	}

}
