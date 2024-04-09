
package acme.features.auditor.codeAudit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.Type;
import acme.entities.projects.Project;
import acme.roles.Auditor;

@Service
public class AuditorCodeAuditCreateService extends AbstractService<Auditor, CodeAudit> {

	@Autowired
	private AuditorCodeAuditRepository repository;


	@Override
	public void authorise() {
		boolean status;
		status = super.getRequest().getPrincipal().hasRole(Auditor.class);
		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		CodeAudit codeAudit;
		Auditor auditor;
		auditor = this.repository.findAuditorById(super.getRequest().getPrincipal().getActiveRoleId());

		codeAudit = new CodeAudit();
		codeAudit.setDraftMode(true);
		codeAudit.setAuditor(auditor);
		super.getBuffer().addData(codeAudit);
	}

	@Override
	public void bind(final CodeAudit object) {
		assert object != null;

		int projectId;
		Project project;
		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);
		super.bind(object, "code", "execution", "type", "mark", "correctiveActions", "link");
		object.setProject(project);
	}

	@Override
	public void validate(final CodeAudit object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			CodeAudit existing;

			existing = this.repository.findOneCodeAuditByCode(object.getCode());
			super.state(existing == null, "code", "auditor.code-audit.form.error.duplicated");
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
		SelectChoices projectChoices;
		SelectChoices typeChoices;
		typeChoices = SelectChoices.from(Type.class, object.getType());

		projects = this.repository.findPublishedProjects();
		projectChoices = SelectChoices.from(projects, "code", object.getProject());
		Dataset dataset;
		dataset = super.unbind(object, "code", "execution", "correctiveActions", "link");
		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("type", typeChoices.getSelected().getKey());
		dataset.put("types", typeChoices);
		super.getResponse().addData(dataset);
	}
}
