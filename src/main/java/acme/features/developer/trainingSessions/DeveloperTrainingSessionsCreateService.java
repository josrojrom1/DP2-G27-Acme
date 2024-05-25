
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

		final Date baseDate = MomentHelper.parse("2000/01/01 00:00", "yyyy/MM/dd HH:mm");
		final Date topDate = MomentHelper.parse("2200/12/31 23:59", "yyyy/MM/dd HH:mm");

		if (!super.getBuffer().getErrors().hasErrors("periodFinish"))
			super.state(object.getPeriodStart() != null, "periodFinish", "developer.training-sessions.form.error.NotPeriodStart");

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingSessions existing;

			existing = this.repository.findTrainingSessionsByCode(object.getCode(), object.getId());
			super.state(existing == null, "code", "developer.training-sessions.form.error.code.duplicated");
		}

		// if (!super.getBuffer().getErrors().hasErrors("periodFinish"))
		//	super.state(object.getPeriodFinish().after(object.getPeriodStart()), "periodFinish", "developer.training-sessions.form.error.periodFinish");

		if (!super.getBuffer().getErrors().hasErrors("periodStart")) {
			super.state(MomentHelper.isAfterOrEqual(object.getPeriodStart(), baseDate), "periodStart", "developer.training-sessions.form.error.tooLittle");
			super.state(MomentHelper.isBeforeOrEqual(object.getPeriodStart(), topDate), "periodStart", "developer.training-module.form.error.tooBig");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodFinish")) {
			super.state(MomentHelper.isAfterOrEqual(object.getPeriodFinish(), baseDate), "periodFinish", "developer.training-sessions.form.error.tooLittle");
			super.state(MomentHelper.isBeforeOrEqual(object.getPeriodFinish(), topDate), "periodFinish", "developer.training-module.form.error.tooBig");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodFinish")) {
			Date minimunPeriodFinish;
			minimunPeriodFinish = MomentHelper.deltaFromMoment(object.getPeriodStart(), 7, ChronoUnit.DAYS);
			super.state(MomentHelper.isAfterOrEqual(object.getPeriodFinish(), minimunPeriodFinish), "periodFinish", "developer.training-sessions.form.error.too-close");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodStart"))
			if (object.getTrainingModule().getCreationMoment() != null) {
				Date minimunPeriodStart;
				minimunPeriodStart = MomentHelper.deltaFromMoment(object.getTrainingModule().getCreationMoment(), 7, ChronoUnit.DAYS);
				super.state(MomentHelper.isAfterOrEqual(object.getPeriodStart(), minimunPeriodStart), "periodStart", "developer.training-sessions.form.error.nomore");

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
