
package acme.features.sponsor.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import acme.client.controllers.AbstractController;
import acme.forms.SponsorDashboard;
import acme.roles.Sponsor;

@Repository
public class SponsorDashboardController extends AbstractController<Sponsor, SponsorDashboard> {

	@Autowired
	private SponsorDashboardShowService showService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
	}

}
