
package acme.features.sponsor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Invoice i where i.tax <= 21.00 and i.sponsorship.sponsor.id = :id and i.sponsorship.draftMode = false")
	int totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne(int id);

	@Query("select count(s) from Sponsorship s where s.link is not null and s.sponsor.id =:id and s.draftMode = false")
	int totalNumOfSponsorshipsWithLink(int id);

	@Query("select avg(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false")
	double sponsorshipAmountAverage(int id);

	@Query("select min(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false")
	double minimumSponsorshipAmount(int id);

	@Query("select max(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false")
	double maximumSponsorshipAmount(int id);

	@Query("select avg(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false")
	double invoiceQuantityAverage(int id);

	@Query("select min(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false")
	double minimumInvoiceQuantity(int id);

	@Query("select max(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false")
	double maximumInvoiceQuantity(int id);

}
