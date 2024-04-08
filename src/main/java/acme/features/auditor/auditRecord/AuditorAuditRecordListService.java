
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordListService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


	@Override
	public void authorise() {
		//		boolean status;
		//		int id;
		//		AuditRecord auditRecord;
		//
		//		id = super.getRequest().getData("id", int.class);
		//		auditRecord = this.repository.findOneAuditRecordById(id);
		//		status = auditRecord != null && (!auditRecord.isDraftMode() || super.getRequest().getPrincipal().hasRole(Auditor.class));

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<AuditRecord> objects;
		//Principal principal;
		int id;
		id = super.getRequest().getData("masterId", int.class);
		objects = this.repository.findManyAuditRecordsByCodeAuditId(id);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "startPeriod", "endPeriod", "mark", "link");

		super.getResponse().addData(dataset);
	}

	@Override
	public void unbind(final Collection<AuditRecord> objects) {
		assert objects != null;

		int masterId;
		CodeAudit codeAudit;
		final boolean showCreate;

		masterId = super.getRequest().getData("masterId", int.class);
		codeAudit = this.repository.findOneCodeAuditById(masterId);
		showCreate = codeAudit.isDraftMode() && super.getRequest().getPrincipal().hasRole(codeAudit.getAuditor());

		super.getResponse().addGlobal("masterId", masterId);
		super.getResponse().addGlobal("showCreate", showCreate);
	}

}
