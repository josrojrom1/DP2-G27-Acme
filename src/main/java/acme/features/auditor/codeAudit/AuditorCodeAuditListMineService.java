
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
		super.getResponse().setAuthorised(true);
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

		super.getResponse().addData(dataset);
	}

	//	private Mark getModeOfAuditRecordMarks(final int codeAuditId) {
	//		Collection<AuditRecord> ls = this.repository.findAuditRecordsById(codeAuditId);
	//		Collection<Mark> markList = new ArrayList<>();
	//		Mark mark;
	//
	//		for (final AuditRecord ar : ls)
	//			markList.add(ar.getMark());
	//
	//		if (markList.size() == 0)
	//			mark = Mark.F_MINUS;
	//		else {
	//			Map<Mark, Integer> markCount = new HashMap<>();
	//			for (final Mark m : markList) {
	//				Integer count = markCount.get(m);
	//				if (count == null)
	//					count = 0;
	//				markCount.put(m, count + 1);
	//			}
	//
	//			int maxValue;
	//			maxValue = Collections.max(markCount.values());
	//			List<Mark> maxMarks = new ArrayList<>();
	//			for (final Map.Entry<Mark, Integer> entry : markCount.entrySet())
	//				if (entry.getValue() == maxValue)
	//					maxMarks.add(entry.getKey());
	//
	//			Mark result;
	//			if (maxMarks.size() == 1)
	//				result = maxMarks.get(0);
	//			else
	//				//En caso de empate devolvemos la menor nota
	//
	//				//Ordenamos según la nota para que la más baja aparezca primero
	//				Collections.reverse(maxMarks);
	//
	//			result = maxMarks.get(0);
	//
	//			mark = result;
	//		}
	//		return mark;
	//	}

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
