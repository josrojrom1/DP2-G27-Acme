
package acme.features.manager.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select count(us) from UserStory us where us.priority = PriorityEnum.MUST and us.draftMode = false and us.manager.id = :clientId")
	int totalNumOfMustUserStories(int clientId);

	@Query("select count(us) from UserStory us where us.priority = PriorityEnum.SHOULD and us.draftMode = false and us.manager.id = :clientId")
	int totalNumOfShouldUserStories(int clientId);

	@Query("select count(us) from UserStory us where us.priority = PriorityEnum.COULD and us.draftMode = false and us.manager.id = :clientId")
	int totalNumOfCouldUserStories(int clientId);

	@Query("select count(us) from UserStory us where us.priority = PriorityEnum.WONT and us.draftMode = false and us.manager.id = :clientId")
	int totalNumOfWontUserStories(int clientId);

	@Query("select avg(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'USD'")
	Double averageEstimatedCostOfUSUSD(int clientId);

	@Query("select stddev(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'USD'")
	Double deviationOfEstimatedCostUSUSD(int clientId);

	@Query("select min(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'USD'")
	Double minimumEstimatedCostOfUSUSD(int clientId);

	@Query("select max(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'USD'")
	Double maximumEstimatedCostOfUSUSD(int clientId);

	@Query("select avg(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'EUR'")
	Double averageEstimatedCostOfUSEUR(int clientId);

	@Query("select stddev(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'EUR'")
	Double deviationOfEstimatedCostUSEUR(int clientId);

	@Query("select min(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'EUR'")
	Double minimumEstimatedCostOfUSEUR(int clientId);

	@Query("select max(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'EUR'")
	Double maximumEstimatedCostOfUSEUR(int clientId);

	@Query("select avg(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'GBP'")
	Double averageEstimatedCostOfUSGBP(int clientId);

	@Query("select stddev(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'GBP'")
	Double deviationOfEstimatedCostUSGBP(int clientId);

	@Query("select min(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'GBP'")
	Double minimumEstimatedCostOfUSGBP(int clientId);

	@Query("select max(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'GBP'")
	Double maximumEstimatedCostOfUSGBP(int clientId);

	@Query("select avg(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'USD'")
	Double averageProjectCostUSD(int clientId);

	@Query("select stddev(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'USD'")
	Double deviationOfProjectCostUSD(int clientId);

	@Query("select min(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'USD'")
	Double minimumProjectCostUSD(int clientId);

	@Query("select max(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.estimatedCost.currency = 'USD'")
	Double maximumProjectCostUSD(int clientId);

	@Query("select avg(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'EUR'")
	Double averageProjectCostEUR(int clientId);

	@Query("select stddev(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'EUR'")
	Double deviationOfProjectCostEUR(int clientId);

	@Query("select min(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'EUR'")
	Double minimumProjectCostEUR(int clientId);

	@Query("select max(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.estimatedCost.currency = 'EUR'")
	Double maximumProjectCostEUR(int clientId);

	@Query("select avg(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'GBP'")
	Double averageProjectCostGBP(int clientId);

	@Query("select stddev(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'GBP'")
	Double deviationOfProjectCostGBP(int clientId);

	@Query("select min(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'GBP'")
	Double minimumProjectCostGBP(int clientId);

	@Query("select max(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.estimatedCost.currency = 'GBP'")
	Double maximumProjectCostGBP(int clientId);

}
