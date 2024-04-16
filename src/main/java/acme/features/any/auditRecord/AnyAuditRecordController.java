
package acme.features.any.auditRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Any;
import acme.entities.audits.AuditRecord;

@Controller
public class AnyAuditRecordController extends AbstractController<Any, AuditRecord> {

	@Autowired
	private AnyAuditRecordListService	listService;

	@Autowired
	private AnyAuditRecordShowService	showService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}
}
