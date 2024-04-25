
package acme.features.developer.trainingSessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.training.TrainingModule;
import acme.entities.training.TrainingSessions;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionsDeleteService extends AbstractService<Developer, TrainingSessions> {

	@Autowired
	private DeveloperTrainingSessionsRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int trainingSessionsId;
		TrainingModule trainingModule;

		trainingSessionsId = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findOneTrainingModuleByTrainingSessionsId(trainingSessionsId);
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
	}

	@Override
	public void perform(final TrainingSessions object) {
		assert object != null;

		this.repository.delete(object);
	}

	@Override
	public void unbind(final TrainingSessions object) {
		assert object != null;

		int masterId;
		masterId = super.getRequest().getData("masterId", int.class);

		Dataset dataset;

		dataset = super.unbind(object, "code", "periodStart", "periodFinish", "location", "instructor", "contactEmail", "link", "draftMode");
		dataset.put("masterId", masterId);
		//dataset.put("draftMode", object.isDraftMode());

		super.getResponse().addData(dataset);
	}
}
