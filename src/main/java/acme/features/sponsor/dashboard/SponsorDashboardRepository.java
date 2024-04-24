
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

	@Query("select avg(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false and s.amount.currency = 'EUR'")
	double sponsorshipAmountAverageEUR(int id);
	@Query("select avg(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false and s.amount.currency = 'USD'")
	double sponsorshipAmountAverageUSD(int id);
	@Query("select avg(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false and s.amount.currency = 'GBP'")
	double sponsorshipAmountAverageGBP(int id);

	@Query("select stddev(s.amount.amount) from Sponsorship s where s.sponsor.id = :id and s.draftMode = false and s.amount.currency = 'EUR'")
	double sponsorshipAmountDeviationEUR(int id);
	@Query("select stddev(s.amount.amount) from Sponsorship s where s.sponsor.id = :id and s.draftMode = false and s.amount.currency = 'USD'")
	double sponsorshipAmountDeviationUSD(int id);
	@Query("select stddev(s.amount.amount) from Sponsorship s where s.sponsor.id = :id and s.draftMode = false and s.amount.currency = 'GBP'")
	double sponsorshipAmountDeviationGBP(int id);

	@Query("select min(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false and s.amount.currency = 'EUR'")
	double minimumSponsorshipAmountEUR(int id);
	@Query("select min(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false and s.amount.currency = 'USD'")
	double minimumSponsorshipAmountUSD(int id);
	@Query("select min(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false and s.amount.currency = 'GBP'")
	double minimumSponsorshipAmountGBP(int id);

	@Query("select max(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false and s.amount.currency = 'EUR'")
	double maximumSponsorshipAmountEUR(int id);
	@Query("select max(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false and s.amount.currency = 'USD'")
	double maximumSponsorshipAmountUSD(int id);
	@Query("select max(s.amount.amount) from Sponsorship s where s.sponsor.id =:id and s.draftMode = false and s.amount.currency = 'GBP'")
	double maximumSponsorshipAmountGBP(int id);

	@Query("select avg(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'EUR'")
	double invoiceQuantityAverageEUR(int id);
	@Query("select avg(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'USD'")
	double invoiceQuantityAverageUSD(int id);
	@Query("select avg(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'GBP'")
	double invoiceQuantityAverageGBP(int id);

	@Query("select stddev(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'EUR'")
	double invoiceQuantityDeviationEUR(int id);
	@Query("select stddev(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'USD'")
	double invoiceQuantityDeviationUSD(int id);
	@Query("select stddev(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'GBP'")
	double invoiceQuantityDeviationGBP(int id);

	@Query("select min(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'EUR'")
	double minimumInvoiceQuantityEUR(int id);
	@Query("select min(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'USD'")
	double minimumInvoiceQuantityUSD(int id);
	@Query("select min(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'GBP'")
	double minimumInvoiceQuantityGBP(int id);

	@Query("select max(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'EUR'")
	double maximumInvoiceQuantityEUR(int id);
	@Query("select max(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'USD'")
	double maximumInvoiceQuantityUSD(int id);
	@Query("select max(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id =:id and i.sponsorship.draftMode = false and i.quantity.currency = 'GBP'")
	double maximumInvoiceQuantityGBP(int id);

}
