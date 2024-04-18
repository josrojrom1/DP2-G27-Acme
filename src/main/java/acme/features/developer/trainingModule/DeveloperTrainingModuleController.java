
package acme.features.developer.trainingModule;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.training.TrainingModule;
import acme.roles.Developer;

@Controller
public class DeveloperTrainingModuleController extends AbstractController<Developer, TrainingModule> {

	@Autowired
	private DeveloperTrainingModuleListMineService	listService;

	@Autowired
	private DeveloperTrainingModuleShowService		showService;

	@Autowired
	private DeveloperTrainingModuleCreateService	createService;


	@PostConstruct
	protected void initialise() {

		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);

		super.addCustomCommand("list-mine", "list", this.listService);
	}
}
