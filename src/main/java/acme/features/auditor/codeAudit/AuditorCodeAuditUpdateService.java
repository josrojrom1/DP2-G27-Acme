
package acme.features.auditor.codeAudit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.Type;
import acme.entities.projects.Project;
import acme.roles.Auditor;

@Service
public class AuditorCodeAuditUpdateService extends AbstractService<Auditor, CodeAudit> {

	@Autowired
	private AuditorCodeAuditRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		CodeAudit codeAudit;
		Auditor auditor;

		masterId = super.getRequest().getData("id", int.class);
		codeAudit = this.repository.findOneCodeAuditById(masterId);
		auditor = codeAudit == null ? null : codeAudit.getAuditor();
		status = codeAudit != null && codeAudit.isDraftMode() && super.getRequest().getPrincipal().hasRole(auditor);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		CodeAudit object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneCodeAuditById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final CodeAudit object) {
		assert object != null;

		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);

		super.bind(object, "code", "execution", "correctiveActions", "type", "link");
		object.setProject(project);
	}

	@Override
	public void validate(final CodeAudit object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			CodeAudit existing;
			existing = this.repository.findOneCodeAuditByCode(object.getCode());
			super.state(existing == null || existing.getId() == object.getId(), "code", "auditor.code-audit.form.error.code.duplicated");
		}

	}

	@Override
	public void perform(final CodeAudit object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final CodeAudit object) {
		assert object != null;

		Collection<Project> projects;
		Collection<AuditRecord> auditRecords;

		SelectChoices projectChoices;
		SelectChoices typeChoices;
		typeChoices = SelectChoices.from(Type.class, object.getType());
		projects = this.repository.findPublishedProjects();
		projectChoices = SelectChoices.from(projects, "title", object.getProject());
		Dataset dataset;
		dataset = super.unbind(object, "code", "execution", "correctiveActions", "type", "link");
		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("type", typeChoices.getSelected().getKey());
		dataset.put("types", typeChoices);
		super.getResponse().addData(dataset);

		boolean auditRecordsDraftModeState = true;
		auditRecords = this.repository.findAuditRecordsById(object.getId());
		for (AuditRecord a : auditRecords)
			if (auditRecords.isEmpty() || a.isDraftMode())
				break;
			else
				auditRecordsDraftModeState = false;
		dataset.put("auditRecordsDraftModeState", auditRecordsDraftModeState);
		super.getResponse().addData(dataset);
	}

}
