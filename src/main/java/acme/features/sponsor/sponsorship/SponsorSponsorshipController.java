
package acme.features.sponsor.sponsorship;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.sponsorship.Sponsorship;
import acme.roles.Sponsor;

@Controller
public class SponsorSponsorshipController extends AbstractController<Sponsor, Sponsorship> {

	// Internal state ---------------------------------------------------

	@Autowired
	private SponsorSponsorshipListService listService;


	@PostConstruct
	protected void initialise() {
		super.addCustomCommand("list-mine", "list", this.listService);
	}

}
