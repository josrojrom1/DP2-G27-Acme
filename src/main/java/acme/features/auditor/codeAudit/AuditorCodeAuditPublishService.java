
package acme.features.auditor.codeAudit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.Mark;
import acme.entities.audits.Type;
import acme.entities.projects.Project;
import acme.roles.Auditor;

@Service
public class AuditorCodeAuditPublishService extends AbstractService<Auditor, CodeAudit> {

	@Autowired
	private AuditorCodeAuditRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int codeAuditId;
		CodeAudit codeAudit;
		Auditor auditor;

		codeAuditId = super.getRequest().getData("id", int.class);
		codeAudit = this.repository.findOneCodeAuditById(codeAuditId);
		auditor = codeAudit == null ? null : codeAudit.getAuditor();
		status = codeAudit != null && codeAudit.isDraftMode() && super.getRequest().getPrincipal().hasRole(auditor) && codeAudit.getAuditor().getId() == super.getRequest().getPrincipal().getActiveRoleId();

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

		super.bind(object, "code", "execution", "correctiveActions", "type", "mark", "link");
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

		if (!super.getBuffer().getErrors().hasErrors("mark"))
			super.state(object.getMark() != Mark.F && object.getMark() != Mark.F_MINUS, "mark", "auditor.code-audit.form.error.mark.minimumMark");
	}
	@Override
	public void perform(final CodeAudit object) {
		assert object != null;

		object.setDraftMode(false);
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
		dataset = super.unbind(object, "code", "execution", "correctiveActions", "type", "mark", "link", "draftMode");
		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("type", typeChoices.getSelected().getKey());
		dataset.put("types", typeChoices);

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