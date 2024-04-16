
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.Mark;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordShowService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


	@Override
	public void authorise() {
		boolean status;
		CodeAudit codeAudit;
		int id;
		id = super.getRequest().getData("id", int.class);
		codeAudit = this.repository.findOneCodeAuditByAuditRecordId(id);
		status = codeAudit != null && (!codeAudit.isDraftMode() || super.getRequest().getPrincipal().hasRole(codeAudit.getAuditor()));
		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		AuditRecord object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneAuditRecordById(id);
		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;
		Dataset dataset;
		SelectChoices markChoices;
		markChoices = SelectChoices.from(Mark.class, object.getMark());

		dataset = super.unbind(object, "code", "startPeriod", "endPeriod", "mark", "link");
		dataset.put("masterId", object.getCodeAudit().getId());
		dataset.put("mark", markChoices);
		dataset.put("draftMode", object.getCodeAudit().isDraftMode());
		super.getResponse().addData(dataset);
	}

}
