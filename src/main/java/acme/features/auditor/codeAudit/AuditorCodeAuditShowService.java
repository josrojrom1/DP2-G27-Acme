
package acme.features.auditor.codeAudit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.audits.CodeAudit;
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

		//int employerId;
		//Collection<Company> contractors;
		//SelectChoices choices;
		Dataset dataset;

		//if (!object.isDraftMode())
		//	contractors = this.repository.findAllContractors();
		//else {
		//	employerId = super.getRequest().getPrincipal().getActiveRoleId();
		//	contractors = this.repository.findManyContractorsByEmployerId(employerId);
		//}
		//choices = SelectChoices.from(contractors, "name", object.getContractor());

		dataset = super.unbind(object, "code", "execution", "type", "correctiveActions", "mark", "link", "draftMode");
		dataset.put("draftMode", object.isDraftMode());

		//dataset.put("contractor", choices.getSelected().getKey());
		//dataset.put("contractors", choices);

		super.getResponse().addData(dataset);
	}

}
