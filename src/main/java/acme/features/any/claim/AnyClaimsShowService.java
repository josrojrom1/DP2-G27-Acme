
package acme.features.any.claim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.claims.Claim;

@Service
public class AnyClaimsShowService extends AbstractService<Any, Claim> {

	@Autowired
	private AnyClaimsRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		Claim claim;

		id = super.getRequest().getData("id", int.class);
		claim = this.repository.findOneClaimById(id);
		status = claim != null && MomentHelper.isPast(claim.getInstantiationMoment());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Claim object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneClaimById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final Claim object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "instantiationMoment", "heading", "description", "department", "email", "link");

		super.getResponse().addData(dataset);
	}
}
