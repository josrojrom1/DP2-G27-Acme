
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.Mark;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordShowService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


	@Override
	public void authorise() {
		boolean status;
		AuditRecord auditRecord;
		Auditor auditor;
		int id;
		id = super.getRequest().getData("id", int.class);
		auditRecord = this.repository.findOneAuditRecordById(id);
		auditor = auditRecord == null ? null : auditRecord.getCodeAudit().getAuditor();
		status = super.getRequest().getPrincipal().hasRole(auditor) || auditRecord != null && !auditRecord.isDraftMode();
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
		dataset.put("mark", markChoices);
		dataset.put("draftMode", object.getCodeAudit().isDraftMode());
		super.getResponse().addData(dataset);
	}

}
