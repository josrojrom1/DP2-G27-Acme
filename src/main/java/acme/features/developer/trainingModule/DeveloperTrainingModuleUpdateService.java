
package acme.features.developer.trainingModule;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.training.DifficultyLevel;
import acme.entities.training.TrainingModule;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleUpdateService extends AbstractService<Developer, TrainingModule> {

	@Autowired
	private DeveloperTrainingModuleRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		TrainingModule trainingModule;
		Developer developer;

		masterId = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(masterId);
		developer = trainingModule == null ? null : trainingModule.getDeveloper();
		status = trainingModule != null && trainingModule.isDraftMode() && super.getRequest().getPrincipal().hasRole(developer) && super.getRequest().getPrincipal().getActiveRoleId() == developer.getId();

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
	public void bind(final TrainingModule object) {
		assert object != null;

		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findProjectById(projectId);

		super.bind(object, "code", "details", "difficultyLevel", "link", "totalTime");
		object.setProject(project);
		object.setUpdateMoment(MomentHelper.getCurrentMoment());
	}

	@Override
	public void validate(final TrainingModule object) {
		assert object != null;

		final Date baseDate = MomentHelper.parse("2000/01/01 00:00", "yyyy/MM/dd HH:mm");

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingModule existing;

			existing = this.repository.findTrainingModuleByCode(object.getCode(), object.getId());
			super.state(existing == null, "code", "developer.training-module.form.error.code.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("creationMoment"))
			super.state(MomentHelper.isAfterOrEqual(object.getCreationMoment(), baseDate), "creationMoment", "developer.training-module.form.error.tooLittle");
		if (object.getUpdateMoment() != null) {
			if (!super.getBuffer().getErrors().hasErrors("updateMoment"))
				super.state(object.getCreationMoment() != null, "updateMoment", "developer.training-module.form.error.NotcreationMoment");
			if (object.getCreationMoment() != null)
				if (!super.getBuffer().getErrors().hasErrors("updateMoment"))
					super.state(object.getUpdateMoment() == null || object.getUpdateMoment().compareTo(object.getCreationMoment()) >= 0, "updateMoment", "developer.training-module.form.error.updateMoment");
		}

		if (!super.getBuffer().getErrors().hasErrors("project")) {
			Project objProject = object.getProject();
			super.state(!objProject.isDraftMode(), "project", "developer.training-module.form.error.code.projectNotPublish");
		}
	}

	@Override
	public void perform(final TrainingModule object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Collection<Project> projects;
		SelectChoices projectChoices;
		SelectChoices difficultyLevelChoices;

		difficultyLevelChoices = SelectChoices.from(DifficultyLevel.class, object.getDifficultyLevel());

		projects = this.repository.findPublishedProjects();
		projectChoices = SelectChoices.from(projects, "code", object.getProject());

		Dataset dataset;

		dataset = super.unbind(object, "code", "creationMoment", "updateMoment", "details", "difficultyLevel", "link", "totalTime", "draftMode");
		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("difficultyLevel", difficultyLevelChoices.getSelected().getKey());
		dataset.put("difficultyLevels", difficultyLevelChoices);

		super.getResponse().addData(dataset);
	}
}
