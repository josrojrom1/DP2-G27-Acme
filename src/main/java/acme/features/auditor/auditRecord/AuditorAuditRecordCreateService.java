
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
public class AuditorAuditRecordCreateService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		CodeAudit codeAudit;
		masterId = super.getRequest().getData("masterId", int.class);
		codeAudit = this.repository.findOneCodeAuditById(masterId);
		status = codeAudit != null && codeAudit.isDraftMode() && super.getRequest().getPrincipal().hasRole(codeAudit.getAuditor());
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditRecord object;
		CodeAudit codeAudit;
		int masterId;

		masterId = super.getRequest().getData("masterId", int.class);
		codeAudit = this.repository.findOneCodeAuditById(masterId);
		object = new AuditRecord();
		object.setCodeAudit(codeAudit);
		object.setDraftMode(true);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final AuditRecord object) {
		assert object != null;
		super.bind(object, "code", "startPeriod", "endPeriod", "mark", "link");
	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			AuditRecord existing;

			existing = this.repository.findOneAuditRecordByCode(object.getCode());
			super.state(existing == null, "code", "auditor.audit-record.form.error.code.duplicated");
		}

	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;
		this.repository.save(object);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;
		SelectChoices markChoices;
		markChoices = SelectChoices.from(Mark.class, object.getMark());
		Dataset dataset;
		int masterId;
		masterId = super.getRequest().getData("masterId", int.class);
		dataset = super.unbind(object, "code", "startPeriod", "endPeriod", "mark", "link");
		dataset.put("marks", markChoices);
		dataset.put("mark", markChoices.getSelected().getKey());
		dataset.put("masterId", masterId);
		dataset.put("draftMode", object.getCodeAudit().isDraftMode());
		super.getResponse().addData(dataset);
	}

}
