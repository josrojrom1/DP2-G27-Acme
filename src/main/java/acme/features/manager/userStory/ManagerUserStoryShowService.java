
package acme.features.manager.userStory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.PriorityEnum;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Service
public class ManagerUserStoryShowService extends AbstractService<Manager, UserStory> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerUserStoryRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		UserStory userStory;
		int id = super.getRequest().getData("id", int.class);
		userStory = this.repository.findUserStoryById(id);
		status = userStory != null && super.getRequest().getPrincipal().hasRole(Manager.class) && super.getRequest().getPrincipal().getActiveRoleId() == userStory.getManager().getId();
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		UserStory object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findUserStoryById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final UserStory object) {
		assert object != null;
		Dataset dataset;
		SelectChoices priorityChoices;
		priorityChoices = SelectChoices.from(PriorityEnum.class, object.getPriority());

		dataset = super.unbind(object, "title", "description", "estimatedCost", "acceptanceCriteria", "draftMode", "priority", "link");
		dataset.put("priorities", priorityChoices);

		super.getResponse().addData(dataset);
	}

}
