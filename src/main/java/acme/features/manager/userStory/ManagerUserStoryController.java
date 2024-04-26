
package acme.features.manager.userStory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Controller
public class ManagerUserStoryController extends AbstractController<Manager, UserStory> {

	@Autowired
	private ManagerUserStoryListByProjectService	listByProjectService;

	@Autowired
	private ManagerUserStoryListService				listService;

	@Autowired
	private ManagerUserStoryDeleteService			deleteService;

	@Autowired
	private ManagerUserStoryCreateByProjectService	createByProjectService;

	@Autowired
	private ManagerUserStoryCreateService			createService;

	@Autowired
	private ManagerUserStoryUpdateService			updateService;

	@Autowired
	private ManagerUserStoryShowService				showService;

	@Autowired
	private ManagerUserStoryPublishService			publishService;


	@PostConstruct
	protected void initialise() {
		super.addCustomCommand("list-mine", "list", this.listService);
		super.addCustomCommand("list-project", "list", this.listByProjectService);
		super.addBasicCommand("show", this.showService);
		super.addCustomCommand("create-project", "create", this.createByProjectService);
		super.addCustomCommand("publish", "update", this.publishService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("update", this.updateService);

	}
}
