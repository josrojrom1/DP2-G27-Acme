
package acme.features.authenticated.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Authenticated;
import acme.client.data.accounts.DefaultUserIdentity;
import acme.client.data.accounts.UserAccount;
import acme.client.data.models.Dataset;
import acme.client.helpers.PrincipalHelper;
import acme.client.services.AbstractService;
import acme.entities.notice.Notice;

@Service
public class AuthenticatedNoticeCreateService extends AbstractService<Authenticated, Notice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedNoticeRepository repository;

	// AbstractService<Authenticated, Consumer> ---------------------------


	@Override
	public void authorise() {
		boolean status;
		status = super.getRequest().getPrincipal().hasRole(Authenticated.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Notice object;
		String username = super.getRequest().getPrincipal().getUsername();

		int userAccountId = super.getRequest().getPrincipal().getAccountId();
		UserAccount userAccount = this.repository.findOneUserAccountById(userAccountId);
		DefaultUserIdentity identity = userAccount.getIdentity();
		String fullName = identity.getFullName();
		String author = String.format("<%s>-<%s>", username, fullName);

		object = new Notice();
		object.setAuthor(author);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Notice object) {
		assert object != null;

		super.bind(object, "instantiationMoment", "title", "message", "email", "link");
	}

	@Override
	public void validate(final Notice object) {
		assert object != null;

		boolean isAccepted = this.getRequest().getData("accept", boolean.class);
		super.state(isAccepted, "accept", "authenticated.notice.form.error.must-accept");
	}

	@Override
	public void perform(final Notice object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Notice object) {
		Dataset dataset;

		dataset = super.unbind(object, "instantiationMoment", "author", "title", "message", "email", "link");

		if (super.getRequest().getMethod().equals("POST"))
			dataset.put("accept", super.getRequest().getData("accept", boolean.class));
		else
			dataset.put("accept", "false");

		super.getResponse().addData(dataset);
	}

	@Override
	public void onSuccess() {
		if (super.getRequest().getMethod().equals("POST"))
			PrincipalHelper.handleUpdate();
	}

}
