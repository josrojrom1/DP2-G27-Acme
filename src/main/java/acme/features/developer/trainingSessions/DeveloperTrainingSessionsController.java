
package acme.features.developer.trainingSessions;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.training.TrainingSessions;
import acme.roles.Developer;

@Controller
public class DeveloperTrainingSessionsController extends AbstractController<Developer, TrainingSessions> {

	@Autowired
	private DeveloperTrainingSessionsListService	listService;

	@Autowired
	private DeveloperTrainingSessionsShowService	showService;

	@Autowired
	private DeveloperTrainingSessionsCreateService	createService;

	@Autowired
	private DeveloperTrainingSessionsUpdateService	updateService;

	@Autowired
	private DeveloperTrainingSessionsDeleteService	deleteService;

	@Autowired
	private DeveloperTrainingSessionsPublishService	publishService;


	@PostConstruct
	protected void initialise() {

		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("publish", "update", this.publishService);
	}
}
