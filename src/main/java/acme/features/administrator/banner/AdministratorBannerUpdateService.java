
package acme.features.administrator.banner;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.banner.Banner;

@Service
public class AdministratorBannerUpdateService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannerRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		Banner banner;
		int id = super.getRequest().getData("id", int.class);
		banner = this.repository.findBannerById(id);
		status = banner != null && super.getRequest().getPrincipal().hasRole(Administrator.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Banner object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findBannerById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;

		super.bind(object, "instantiationOrUpdateMoment", "displayStartDate", "displayEndDate", "picture", "slogan", "webDocument");
	}

	@Override
	public void validate(final Banner object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("displayStartDate"))
			super.state(MomentHelper.isAfter(object.getDisplayStartDate(), object.getInstantiationOrUpdateMoment()), "displayStartDate", "administrator.banner.form.error.invalid-display-start-date");

		if (!super.getBuffer().getErrors().hasErrors("displayEndDate")) {
			Date minimumExpirationDate;

			minimumExpirationDate = MomentHelper.deltaFromMoment(object.getDisplayStartDate(), 7, ChronoUnit.DAYS);
			super.state(MomentHelper.isAfter(object.getDisplayEndDate(), minimumExpirationDate), "displayEndDate", "administrator.banner.form.error.too-close");
		}
	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "instantiationOrUpdateMoment", "displayStartDate", "displayEndDate", "picture", "slogan", "webDocument");
	}
}
