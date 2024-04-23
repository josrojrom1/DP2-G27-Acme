
package acme.features.any.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.entities.sponsorship.SponsorshipType;

@Service
public class AnySponsorshipShowService extends AbstractService<Any, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnySponsorshipRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		Sponsorship sponsorship;

		id = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);
		status = sponsorship != null && !sponsorship.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Sponsorship object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneSponsorshipById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final Sponsorship object) {
		assert object != null;

		Dataset dataset;

		SelectChoices typeChoices;
		SelectChoices projectChoices;
		Collection<Project> projects = this.repository.findPublishedProjects();
		typeChoices = SelectChoices.from(SponsorshipType.class, object.getType());
		projectChoices = SelectChoices.from(projects, "title", object.getProject());

		dataset = super.unbind(object, "code", "moment", "startDate", "expirationDate", "amount", "contact", "link");
		dataset.put("project", projectChoices.getSelected().getKey());
		dataset.put("projects", projectChoices);
		dataset.put("type", typeChoices.getSelected().getKey());
		dataset.put("types", typeChoices);

		super.getResponse().addData(dataset);
	}

}
