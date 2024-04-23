
package acme.features.client.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ClientDashboardRepository extends AbstractRepository {

	@Query("select count(pl) from ProgressLog pl where pl.completeness < 0.25 and pl.published = false and pl.contract.client.id = :clientId")
	int totalNumberOfProgLogsWithCompletenessBelow25(int clientId);

	@Query("select count(pl) from ProgressLog pl where 0.25 <= pl.completeness and pl.completeness < 0.50 and pl.published = false and pl.contract.client.id = :clientId")
	int totalNumberOfProgLogsWithCompletenessBetween25and50(int clientId);

	@Query("select count(pl) from ProgressLog pl where 0.50 <= pl.completeness and pl.completeness < 0.75 and pl.published = false and pl.contract.client.id = :clientId")
	int totalNumberOfProgLogsWithCompletenessBetween50and75(int clientId);

	@Query("select count(pl) from ProgressLog pl where pl.completeness >= 0.75 and pl.published = false and pl.contract.client.id = :clientId")
	int totalNumberOfProgLogsWithCompletenessAbove75(int clientId);

	@Query("select avg(c.budget.amount) from Contract c where c.published = false and c.client.id = :clientId")
	Double contractsBudgetAverage(int clientId);

	@Query("select stddev(c.budget.amount) from Contract c where c.published = false and c.client.id = :clientId")
	Double contractsBudgetDeviation(int clientId);

	@Query("select min(c.budget.amount) from Contract c where c.published = false and c.client.id = :clientId")
	double contractsBudgetMinimum(int clientId);

	@Query("select max(c.budget.amount) from Contract c where c.published = false and c.client.id = :clientId")
	double contractsBudgetMaximum(int clientId);

}
