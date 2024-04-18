
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
import acme.features.developer.trainingModule.DeveloperTrainingModuleRepository;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionsCreateService extends AbstractService<Developer, TrainingSessions> {

	@Autowired
	private DeveloperTrainingModuleRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		TrainingModule trainingModule;
		masterId = super.getRequest().getData("masterId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(masterId);
		status = trainingModule != null && trainingModule.isDraftMode() && super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper());
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
			TrainingModule existing;

			existing = this.repository.findTrainingModuleByCode(object.getCode());
			super.state(existing == null, "code", "developer.training-sessions.form.error.code.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("periodFinish"))
			super.state(object.getPeriodFinish().after(object.getPeriodStart()), "periodFinish", "developer.training-sessions.form.error.periodFinish");

		if (!super.getBuffer().getErrors().hasErrors("periodFinish")) {
			Date minimunPeriodFinish;
			minimunPeriodFinish = MomentHelper.deltaFromMoment(object.getPeriodStart(), 7, ChronoUnit.DAYS);
			super.state(MomentHelper.isAfter(object.getPeriodFinish(), minimunPeriodFinish), "periodFinish", "developer.training-sessions.form.error.too-close");
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

		int masterId;
		masterId = super.getRequest().getData("masterId", int.class);

		Dataset dataset;

		dataset = super.unbind(object, "code", "periodStart", "periodFinish", "location", "instructor", "contactEmail", "link");
		dataset.put("masterId", masterId);
		dataset.put("draftMode", object.isDraftMode());

		super.getResponse().addData(dataset);
	}
}
