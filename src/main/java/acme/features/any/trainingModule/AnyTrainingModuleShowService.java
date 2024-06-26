
package acme.features.any.trainingModule;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.training.DifficultyLevel;
import acme.entities.training.TrainingModule;

@Service
public class AnyTrainingModuleShowService extends AbstractService<Any, TrainingModule> {

	@Autowired
	private AnyTrainingModuleRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		TrainingModule trainingModule;

		id = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(id);
		status = trainingModule != null && !trainingModule.isDraftMode();
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		TrainingModule object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneTrainingModuleById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Dataset dataset;

		SelectChoices difficultyLevelChoices;
		SelectChoices projectChoices;
		Collection<Project> projects = this.repository.findPublishedProjects();
		difficultyLevelChoices = SelectChoices.from(DifficultyLevel.class, object.getDifficultyLevel());
		projectChoices = SelectChoices.from(projects, "title", object.getProject());

		dataset = super.unbind(object, "code", "creationMoment", "updateMoment", "details", "totalTime", "link");

		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("draftMode", object.isDraftMode());
		dataset.put("difficultyLevel", difficultyLevelChoices.getSelected().getKey());
		dataset.put("difficultyLevels", difficultyLevelChoices);

		super.getResponse().addData(dataset);
	}

}
