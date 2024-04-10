
package acme.features.sponsor.invoice;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invoice.Invoice;
import acme.entities.sponsorship.Sponsorship;

@Repository
public interface SponsorInvoiceRepository extends AbstractRepository {

	@Query("select s from Sponsorship s where s.id = :id")
	Sponsorship findOneSponsorshipById(int id);

	@Query("select i.sponsorship from Invoice i where i.id = :id")
	Sponsorship findOneSponsorshipByInvoiceId(int id);

	@Query("select i from Invoice i where i.id = :id")
	Invoice findOneDutyById(int id);

	@Query("select i from Invoice i where i.sponsorship.id = :masterId")
	Collection<Invoice> findManyInvoicesByMasterId(int masterId);

}
