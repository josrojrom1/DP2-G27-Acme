
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
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.Mark;
import acme.roles.Auditor;

@Service
public class AuditorCodeAuditShowService extends AbstractService<Auditor, CodeAudit> {

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

		//SelectChoices choices;
		Dataset dataset;
		Mark auditMark;
		auditMark = this.getModeOfAuditRecordMarks(object.getId());

		//if (!object.isDraftMode())
		//	contractors = this.repository.findAllContractors();
		//else {
		//	employerId = super.getRequest().getPrincipal().getActiveRoleId();
		//	contractors = this.repository.findManyContractorsByEmployerId(employerId);
		//}
		//choices = SelectChoices.from(contractors, "name", object.getContractor());

		dataset = super.unbind(object, "code", "execution", "type", "correctiveActions", "mark", "link", "draftMode");
		if (this.repository.findAuditRecordsById(object.getId()).isEmpty())
			dataset.put("mark", " - ");
		else
			dataset.put("mark", auditMark);

		dataset.put("draftMode", object.isDraftMode());

		//dataset.put("contractor", choices.getSelected().getKey());
		//dataset.put("contractors", choices);

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
