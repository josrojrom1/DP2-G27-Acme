
package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.entities.sponsorship.SponsorshipType;
import acme.roles.Sponsor;

@Service
public class SponsorSponsorshipShowService extends AbstractService<Sponsor, Sponsorship> {

	// Internal state -----------------------------------------------

	@Autowired
	private SponsorSponsorshipRepository repository;


	// Este metodo deberia mostrar si la consulta es legal o no
	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Sponsorship sponsorship;
		Sponsor sponsor;

		masterId = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(masterId);
		sponsor = sponsorship == null ? null : sponsorship.getSponsor();
		status = super.getRequest().getPrincipal().hasRole(sponsor) && super.getRequest().getPrincipal().getActiveRoleId() == sponsor.getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Sponsorship sponsorship;
		int id;

		id = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);

		super.getBuffer().addData(sponsorship);

	}

	@Override
	public void unbind(final Sponsorship sponsorship) {

		assert sponsorship != null;

		Dataset dataset;

		SelectChoices typeChoices;
		SelectChoices projectChoices;
		Collection<Project> projects = this.repository.findPublishedProjects();
		typeChoices = SelectChoices.from(SponsorshipType.class, sponsorship.getType());
		projectChoices = SelectChoices.from(projects, "title", sponsorship.getProject());

		dataset = super.unbind(sponsorship, "code", "moment", "startDate", "expirationDate", "amount", "contact", "link", "draftMode");
		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("type", typeChoices.getSelected().getKey());
		dataset.put("types", typeChoices);
		dataset.put("readOnly", true);

		super.getResponse().addData(dataset);

	}

}
