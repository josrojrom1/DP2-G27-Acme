
package acme.features.auditor.codeAudit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.audits.CodeAudit;
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

		dataset = super.unbind(object, "code", "mark", "execution");

		super.getResponse().addData(dataset);
	}

}
