
package acme.features.manager.project;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.projects.Project;
import acme.roles.Manager;

@Controller
public class ManagerProjectController extends AbstractController<Manager, Project> {

	@Autowired
	private ManagerProjectListService		listService;

	@Autowired
	private ManagerProjectDeleteService		deleteService;

	@Autowired
	private ManagerProjectCreateService		createService;

	@Autowired
	private ManagerProjectUpdateService		updateService;

	@Autowired
	private ManagerProjectShowService		showService;

	@Autowired
	private ManagerProjectPublishService	publishService;


	@PostConstruct
	protected void initialise() {
		super.addCustomCommand("list-mine", "list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("create", this.createService);
		super.addCustomCommand("publish", "update", this.publishService);

	}
}
