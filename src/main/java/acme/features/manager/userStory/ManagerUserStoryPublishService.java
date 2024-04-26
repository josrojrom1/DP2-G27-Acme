
package acme.features.manager.userStory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.PriorityEnum;
import acme.entities.projects.UserStory;
import acme.features.manager.project.ManagerProjectRepository;
import acme.features.manager.projectUserStory.ManagerProjectUserStoryRepository;
import acme.roles.Manager;

@Service
public class ManagerUserStoryPublishService extends AbstractService<Manager, UserStory> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerUserStoryRepository			repository;
	@Autowired
	private ManagerProjectUserStoryRepository	pUSRepository;
	@Autowired
	private ManagerProjectRepository			pRepository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		Boolean status;

		status = super.getRequest().getPrincipal().hasRole(Manager.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		UserStory object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findUserStoryById(id);
		object.setDraftMode(false);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final UserStory object) {
		assert object != null;

		super.bind(object, "title", "description", "estimatedCost", "acceptanceCriteria", "priority", "draftMode", "link");
	}

	@Override
	public void validate(final UserStory object) {
		assert object != null;

	}

	@Override
	public void perform(final UserStory object) {
		assert object != null;

		object.setDraftMode(false);

		this.repository.save(object);
	}

	@Override
	public void unbind(final UserStory object) {
		assert object != null;

		int masterId = super.getRequest().getData("masterId", int.class);

		Dataset dataset;
		SelectChoices priorityChoices;
		priorityChoices = SelectChoices.from(PriorityEnum.class, object.getPriority());

		dataset = super.unbind(object, "title", "description", "estimatedCost", "acceptanceCriteria", "priority", "link");
		dataset.put("confirmation", false);
		dataset.put("readonly", false);
		dataset.put("priorities", priorityChoices);
		dataset.put("masterId", masterId);
		dataset.put("draftMode", true);

		super.getResponse().addData(dataset);
	}

}
