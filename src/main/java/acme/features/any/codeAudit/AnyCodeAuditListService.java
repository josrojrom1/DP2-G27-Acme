
package acme.features.any.codeAudit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.audits.CodeAudit;

@Service
public class AnyCodeAuditListService extends AbstractService<Any, CodeAudit> {

	@Autowired
	private AnyCodeAuditRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {

		Collection<CodeAudit> objects;
		objects = this.repository.findAllPublishedCodeAudits();
		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final CodeAudit object) {

		Dataset dataset;
		String payload;
		dataset = super.unbind(object, "code", "execution", "type", "correctiveActions", "mark", "link");
		payload = String.format(//
			"%s; %s; %s; %s", //
			object.getAuditor().getIdentity().getFullName(), //
			object.getAuditor().getFirm(), //
			object.getAuditor().getCertifications(), //
			object.getAuditor().getProfessionalId());
		dataset.put("payload", payload);
		super.getResponse().addData(dataset);
	}
}
