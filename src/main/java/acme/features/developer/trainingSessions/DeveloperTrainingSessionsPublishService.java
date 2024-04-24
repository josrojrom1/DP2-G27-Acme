
package acme.features.developer.trainingSessions;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.training.TrainingModule;
import acme.entities.training.TrainingSessions;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionsPublishService extends AbstractService<Developer, TrainingSessions> {

	@Autowired
	private DeveloperTrainingSessionsRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int trainingSessionId;
		TrainingModule trainingModule;

		trainingSessionId = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findOneTrainingModuleByTrainingSessionsId(trainingSessionId);
		status = trainingModule != null && trainingModule.isDraftMode() && super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper()) && trainingModule.getDeveloper().getId() == super.getRequest().getPrincipal().getActiveRoleId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		TrainingSessions object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneTrainingSessionsById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final TrainingSessions object) {
		assert object != null;

		super.bind(object, "code", "periodStart", "periodFinish", "location", "instructor", "contactEmail", "link");
	}

	@Override
	public void validate(final TrainingSessions object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingSessions existing;

			existing = this.repository.findTrainingSessionsByCode(object.getCode(), object.getId());
			super.state(existing == null, "code", "developer.training-sessions.form.error.code.duplicated");
		}
		if (!super.getBuffer().getErrors().hasErrors("periodFinish"))
			super.state(object.getPeriodFinish().after(object.getPeriodStart()), "periodFinish", "developer.training-sessions.form.error.NotperiodFinish");

		if (!super.getBuffer().getErrors().hasErrors("periodFinish")) {
			Date minimunPeriodFinish;
			minimunPeriodFinish = MomentHelper.deltaFromMoment(object.getPeriodStart(), 7, ChronoUnit.DAYS);
			super.state(MomentHelper.isAfter(object.getPeriodFinish(), minimunPeriodFinish), "periodFinish", "developer.training-sessions.form.error.tooooo-close");
		}
	}

	@Override
	public void perform(final TrainingSessions object) {
		assert object != null;
		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final TrainingSessions object) {
		assert object != null;

		//int masterId;
		//masterId = super.getRequest().getData("masterId", int.class);

		Dataset dataset;

		dataset = super.unbind(object, "code", "periodStart", "periodFinish", "location", "instructor", "contactEmail", "link", "draftMode");
		dataset.put("masterId", object.getTrainingModule().getId());
		//dataset.put("draftMode", object.isDraftMode());

		super.getResponse().addData(dataset);
	}
}
