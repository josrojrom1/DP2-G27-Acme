
package acme.features.auditor.auditRecord;

import java.time.Duration;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.Mark;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordUpdateService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int auditRecordId;
		CodeAudit codeAudit;
		auditRecordId = super.getRequest().getData("id", int.class);
		codeAudit = this.repository.findOneCodeAuditByAuditRecordId(auditRecordId);
		status = codeAudit != null && codeAudit.isDraftMode() && super.getRequest().getPrincipal().hasRole(codeAudit.getAuditor()) && codeAudit.getAuditor().getId() == super.getRequest().getPrincipal().getActiveRoleId();
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
			super.state(existing == null || existing.getId() == object.getId(), "code", "auditor.audit-record.form.error.code.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("startPeriod"))
			if (object.getEndPeriod() != null)

				super.state(object.getStartPeriod().before(object.getEndPeriod()), "startPeriod", "auditor.audit-record.form.error.startPeriod_before_endPeriod");

		if (!super.getBuffer().getErrors().hasErrors("startPeriod"))
			if (object.getEndPeriod() != null)

				super.state(Duration.between(object.getStartPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), object.getEndPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).toHours() >= 1, "startPeriod",
					"auditor.audit-record.form.error.one_hour_period");
		if (!super.getBuffer().getErrors().hasErrors("startPeriod"))
			if (object.getStartPeriod() != null) {

				Date fechaMin = MomentHelper.parse("2000/01/01 00:00", "yyyy/MM/dd HH:mm");
				Date fechaMax = MomentHelper.parse("2200/12/31 23:59", "yyyy/MM/dd HH:mm");

				super.state(object.getStartPeriod().before(fechaMax), "startPeriod", "auditor-audit-record.form.error.execution-max-date");
				super.state(object.getStartPeriod().after(fechaMin), "startPeriod", "auditor-audit-record.form.error.execution-min-date");

			}

		if (!super.getBuffer().getErrors().hasErrors("endPeriod"))
			if (object.getEndPeriod() != null) {

				Date fechaMin = MomentHelper.parse("2000/01/01 00:00", "yyyy/MM/dd HH:mm");
				Date fechaMax = MomentHelper.parse("2200/12/31 23:59", "yyyy/MM/dd HH:mm");

				super.state(object.getEndPeriod().before(fechaMax), "endPeriod", "auditor-audit-record.form.error.execution-max-date");
				super.state(object.getEndPeriod().after(fechaMin), "endPeriod", "auditor-audit-record.form.error.execution-min-date");

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
		dataset = super.unbind(object, "code", "startPeriod", "endPeriod", "mark", "link", "draftMode");
		dataset.put("marks", markChoices);
		dataset.put("mark", markChoices.getSelected().getKey());
		dataset.put("masterId", object.getCodeAudit().getId());
		dataset.put("draftMode", object.isDraftMode());
		super.getResponse().addData(dataset);
	}
}
