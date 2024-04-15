
package acme.features.any.codeAudit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.audits.CodeAudit;

@Service
public class AnyCodeAuditShowService extends AbstractService<Any, CodeAudit> {

	@Autowired
	private AnyCodeAuditRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		CodeAudit codeAudit;

		id = super.getRequest().getData("id", int.class);
		codeAudit = this.repository.findOneCodeAuditById(id);
		status = codeAudit != null && !codeAudit.isDraftMode();

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

		//Collection<Company> contractors;
		//SelectChoices choices;
		Dataset dataset;

		//contractors = this.repository.findAllContractors();
		//choices = SelectChoices.from(contractors, "name", object.getContractor());

		dataset = super.unbind(object, "code", "execution", "type", "correctiveActions", "mark", "link");
		//dataset.put("contractor", choices.getSelected().getKey());
		//dataset.put("contractors", choices);

		super.getResponse().addData(dataset);
	}
}
