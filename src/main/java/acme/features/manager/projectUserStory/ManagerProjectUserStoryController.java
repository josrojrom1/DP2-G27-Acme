
package acme.features.manager.projectUserStory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.projects.ProjectUserStory;
import acme.roles.Manager;

@Controller
public class ManagerProjectUserStoryController extends AbstractController<Manager, ProjectUserStory> {

	@Autowired
	protected ManagerProjectUserStoryCreateService	createService;

	@Autowired
	protected ManagerProjectUserStoryListService	listService;

	@Autowired
	protected ManagerProjectUserStoryShowService	showService;

	@Autowired
	protected ManagerProjectUserStoryDeleteService	deleteService;


	@PostConstruct
	protected void initialise() {
		super.addCustomCommand("list-by-project", "list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
	}

}
