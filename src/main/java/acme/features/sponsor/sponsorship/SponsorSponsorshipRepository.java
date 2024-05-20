
package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invoice.Invoice;
import acme.entities.projects.Project;
import acme.entities.sponsorship.Sponsorship;
import acme.roles.Sponsor;

@Repository
public interface SponsorSponsorshipRepository extends AbstractRepository {

	@Query("select s from Sponsorship s where s.id = :id")
	Sponsorship findOneSponsorshipById(int id);

	@Query("select s from Sponsorship s where s.sponsor.id = :sponsorId")
	Collection<Sponsorship> findManySponsorshipBySponsorId(int sponsorId);

	@Query("select sp from Sponsor sp where sp.id = :id")
	Sponsor findOneSponsorById(int id);

	@Query("select p from Project p where p.id = :projectId")
	Project findOneProjectById(int projectId);

	@Query("select p from Project p where p.draftMode = false")
	Collection<Project> findPublishedProjects();

	@Query("select s from Sponsorship s where s.code = :c")
	Sponsorship findOneSponsorshipByCode(String c);

	@Query("select i from Invoice i where i.sponsorship.id = :masterId and i.draftMode = false")
	Collection<Invoice> findManyInvoicesByMasterId(int masterId);

}
