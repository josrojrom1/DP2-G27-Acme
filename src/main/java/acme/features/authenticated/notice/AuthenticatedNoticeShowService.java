
package acme.features.authenticated.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Authenticated;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.notice.Notice;

@Service
public class AuthenticatedNoticeShowService extends AbstractService<Authenticated, Notice> {

	// Internal state -----------------------------------------------

	@Autowired
	private AuthenticatedNoticeRepository repository;


	// Este metodo deberia mostrar si la consulta es legal o no
	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Notice notice;

		masterId = super.getRequest().getData("id", int.class);
		notice = this.repository.findOneNoticeById(masterId);
		status = super.getRequest().getPrincipal().hasRole(Authenticated.class) && notice != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Notice notice;
		int id;

		id = super.getRequest().getData("id", int.class);
		notice = this.repository.findOneNoticeById(id);

		super.getBuffer().addData(notice);

	}

	@Override
	public void unbind(final Notice object) {

		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "title", "author", "instantiationMoment", "message", "email", "link");

		super.getResponse().addData(dataset);
	}

}
