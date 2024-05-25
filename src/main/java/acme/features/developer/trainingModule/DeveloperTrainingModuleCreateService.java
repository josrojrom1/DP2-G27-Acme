
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
public class DeveloperTrainingModuleCreateService extends AbstractService<Developer, TrainingModule> {

	@Autowired
	private DeveloperTrainingModuleRepository repository;


	@Override
	public void authorise() {
		boolean status;
		status = super.getRequest().getPrincipal().hasRole(Developer.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		TrainingModule object;
		Developer developer;

		developer = this.repository.findDeveloperById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new TrainingModule();
		object.setDraftMode(true);
		object.setDeveloper(developer);
		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final TrainingModule object) {
		assert object != null;

		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findProjectById(projectId);

		super.bind(object, "code", "creationMoment", "details", "difficultyLevel", "link", "totalTime");
		object.setProject(project);
	}

	@Override
	public void validate(final TrainingModule object) {
		assert object != null;

		final Date baseDate = MomentHelper.parse("2000/01/01 00:00", "yyyy/MM/dd HH:mm");
		final Date topDate = MomentHelper.parse("2200/12/31 23:59", "yyyy/MM/dd HH:mm");

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingModule existing;

			existing = this.repository.findTrainingModuleByCode(object.getCode(), object.getId());
			super.state(existing == null, "code", "developer.training-module.form.error.code.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("creationMoment")) {
			super.state(MomentHelper.isAfterOrEqual(object.getCreationMoment(), baseDate), "creationMoment", "developer.training-module.form.error.tooLittle");
			super.state(MomentHelper.isBeforeOrEqual(object.getCreationMoment(), topDate), "creationMoment", "developer.training-module.form.error.tooBig");
		}

		if (object.getUpdateMoment() != null) {
			if (!super.getBuffer().getErrors().hasErrors("updateMoment")) {
				super.state(MomentHelper.isAfterOrEqual(object.getCreationMoment(), baseDate), "updateMoment", "developer.training-module.form.error.tooLittle");
				super.state(MomentHelper.isBeforeOrEqual(object.getCreationMoment(), topDate), "updateMoment", "developer.training-module.form.error.tooBig");
			}

			if (!super.getBuffer().getErrors().hasErrors("updateMoment"))
				super.state(object.getUpdateMoment() == null || object.getUpdateMoment().after(object.getCreationMoment()), "updateMoment", "developer.training-module.form.error.updateMoment");

		}

		if (!super.getBuffer().getErrors().hasErrors("totalTime"))
			super.state(object.getTotalTime() > 0.0, "totalTime", "developer.training-module.form.error.negative-totalTime");

		if (!super.getBuffer().getErrors().hasErrors("project")) {
			Project project = object.getProject();
			super.state(!project.isDraftMode(), "project", "developer.training-module.form.error.code.projectNotPublish");
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
		projectChoices = SelectChoices.from(projects, "title", object.getProject());

		Dataset dataset;

		dataset = super.unbind(object, "code", "creationMoment", "details", "difficultyLevel", "link", "totalTime");
		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("difficultyLevel", difficultyLevelChoices.getSelected().getKey());
		dataset.put("difficultyLevels", difficultyLevelChoices);
		boolean trainningModuleUpDa = true;
		dataset.put("trainningModuleUpDa", trainningModuleUpDa);

		super.getResponse().addData(dataset);
	}

}
