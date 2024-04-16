
package acme.features.auditor.codeAudit;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class AuditorCodeAuditShowService extends AbstractService<Auditor, CodeAudit> {

	@Autowired
	private AuditorCodeAuditRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		CodeAudit codeAudit;
		Auditor auditor;

		id = super.getRequest().getData("id", int.class);
		codeAudit = this.repository.findOneCodeAuditById(id);
		auditor = codeAudit == null ? null : codeAudit.getAuditor();
		status = super.getRequest().getPrincipal().hasRole(auditor) || codeAudit != null && !codeAudit.isDraftMode();
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
	public void unbind(final CodeAudit object) {
		assert object != null;

		Dataset dataset;
		Mark auditMark;
		auditMark = this.getModeOfAuditRecordMarks(object.getId());

		SelectChoices typeChoices;
		SelectChoices projectChoices;
		Collection<Project> projects = this.repository.findPublishedProjects();
		typeChoices = SelectChoices.from(Type.class, object.getType());
		projectChoices = SelectChoices.from(projects, "title", object.getProject());

		dataset = super.unbind(object, "code", "execution", "correctiveActions", "link");
		if (this.repository.findAuditRecordsById(object.getId()).isEmpty())
			dataset.put("mark", " - ");
		else
			dataset.put("mark", auditMark);

		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("draftMode", object.isDraftMode());
		dataset.put("type", typeChoices.getSelected().getKey());
		dataset.put("types", typeChoices);
		dataset.put("readOnly", true);

		super.getResponse().addData(dataset);
	}

	private Mark getModeOfAuditRecordMarks(final int codeAuditId) {
		Map<Mark, Integer> markCount = new HashMap<>();
		Mark[] marks = Mark.values();
		Collection<AuditRecord> audRec = this.repository.findAuditRecordsById(codeAuditId);

		for (Mark mark : marks) {
			int count = (int) audRec.stream().filter(x -> x.getMark() == mark).count();
			markCount.put(mark, count);
		}
		int maxCount = Collections.max(markCount.values());

		List<Mark> maxCountList = markCount.entrySet().stream().filter(x -> x.getValue() == maxCount).sorted((a, b) -> a.getKey().compareTo(b.getKey())).map(Map.Entry::getKey).collect(Collectors.toList());
		return maxCountList.get(0);
	}

}
