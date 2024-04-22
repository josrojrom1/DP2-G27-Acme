
package acme.features.auditor.codeAudit;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.Mark;
import acme.roles.Auditor;

@Service
public class AuditorCodeAuditListMineService extends AbstractService<Auditor, CodeAudit> {

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
		Collection<CodeAudit> objects;
		Principal principal;

		principal = super.getRequest().getPrincipal();
		objects = this.repository.findManyCodeAuditsByAuditorId(principal.getActiveRoleId());

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final CodeAudit object) {
		assert object != null;

		Dataset dataset;

		Mark auditMark;
		auditMark = this.getModeOfAuditRecordMarks(object.getId());

		dataset = super.unbind(object, "code", "mark", "execution");

		if (this.repository.findAuditRecordsById(object.getId()).isEmpty())
			dataset.put("mark", " - ");
		else
			dataset.put("mark", auditMark);

		if (this.repository.findOneCodeAuditById(object.getId()).isDraftMode())
			dataset.put("draftMode", "✖");
		else
			dataset.put("draftMode", "✔");

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
