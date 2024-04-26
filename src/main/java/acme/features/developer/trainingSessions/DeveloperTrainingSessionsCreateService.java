
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
public class DeveloperTrainingSessionsCreateService extends AbstractService<Developer, TrainingSessions> {

	@Autowired
	private DeveloperTrainingSessionsRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		TrainingModule trainingModule;

		masterId = super.getRequest().getData("masterId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(masterId);
		status = trainingModule != null && trainingModule.isDraftMode() && super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper()) && trainingModule.getDeveloper().getId() == super.getRequest().getPrincipal().getActiveRoleId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		TrainingSessions object;
		TrainingModule trainingModule;
		int masterId;

		masterId = super.getRequest().getData("masterId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(masterId);

		object = new TrainingSessions();
		object.setDraftMode(true);
		object.setTrainingModule(trainingModule);

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
			super.state(object.getPeriodFinish().after(object.getPeriodStart()), "periodFinish", "developer.training-sessions.form.error.periodFinish");

		if (!super.getBuffer().getErrors().hasErrors("periodFinish")) {
			Date minimunPeriodFinish;
			minimunPeriodFinish = MomentHelper.deltaFromMoment(object.getPeriodStart(), 7, ChronoUnit.DAYS);
			super.state(MomentHelper.isAfter(object.getPeriodFinish(), minimunPeriodFinish), "periodFinish", "developer.training-sessions.form.error.too-close");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodStart")) {
			Date minimunPeriodStart;
			minimunPeriodStart = MomentHelper.deltaFromMoment(object.getTrainingModule().getCreationMoment(), 7, ChronoUnit.DAYS);
			super.state(MomentHelper.isAfter(object.getPeriodStart(), minimunPeriodStart), "periodStart", "developer.training-sessions.form.error.nomore");
		}
	}

	@Override
	public void perform(final TrainingSessions object) {
		assert object != null;
		this.repository.save(object);
	}

	@Override
	public void unbind(final TrainingSessions object) {
		assert object != null;

		//int masterId;
		//masterId = super.getRequest().getData("masterId", int.class);

		Dataset dataset;

		dataset = super.unbind(object, "code", "periodStart", "periodFinish", "location", "instructor", "contactEmail", "link");
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));
		dataset.put("draftMode", object.getTrainingModule().isDraftMode());
		dataset.put("draftMode", object.isDraftMode());

		super.getResponse().addData(dataset);
	}
}
