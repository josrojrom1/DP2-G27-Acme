
package acme.features.any.trainingModule;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.training.TrainingModule;

@Service
public class AnyTrainingModuleListService extends AbstractService<Any, TrainingModule> {

	@Autowired
	private AnyTrainingModuleRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<TrainingModule> objects;

		objects = this.repository.findTrainingModulePublish();

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "difficultyLevel", "creationMoment");

		if (this.repository.findOneTrainingModuleById(object.getId()).isDraftMode())
			dataset.put("draftMode", "✖");
		else
			dataset.put("draftMode", "✔");

		super.getResponse().addData(dataset);
	}

}
