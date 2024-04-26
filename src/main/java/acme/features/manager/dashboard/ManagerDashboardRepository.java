
package acme.features.manager.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select count(us) from UserStory us where us.priority = 'MUST' and us.draftMode = false and us.manager.id = :clientId")
	int totalNumOfMustUserStories(int clientId);

	@Query("select count(us) from UserStory us where us.priority = 'SHOULD' and us.draftMode = false and us.manager.id = :clientId")
	int totalNumOfShouldUserStories(int clientId);

	@Query("select count(us) from UserStory us where us.priority = 'COULD' and us.draftMode = false and us.manager.id = :clientId")
	int totalNumOfCouldUserStories(int clientId);

	@Query("select count(us) from UserStory us where us.priority = 'WONT' and us.draftMode = false and us.manager.id = :clientId")
	int totalNumOfWontUserStories(int clientId);
	/*
	 * @Query("select avg(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'USD'")
	 * double averageEstimatedCostOfUSUSD(int clientId);
	 * 
	 * @Query("select stddev(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'USD'")
	 * double deviationOfEstimatedCostUSUSD(int clientId);
	 * 
	 * @Query("select min(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'USD'")
	 * double minimumEstimatedCostOfUSUSD(int clientId);
	 * 
	 * @Query("select max(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'USD'")
	 * double maximumEstimatedCostOfUSUSD(int clientId);
	 * 
	 * @Query("select avg(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'EUR'")
	 * double averageEstimatedCostOfUSEUR(int clientId);
	 * 
	 * @Query("select stddev(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'EUR'")
	 * double deviationOfEstimatedCostUSEUR(int clientId);
	 * 
	 * @Query("select min(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'EUR'")
	 * double minimumEstimatedCostOfUSEUR(int clientId);
	 * 
	 * @Query("select max(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'EUR'")
	 * double maximumEstimatedCostOfUSEUR(int clientId);
	 * 
	 * @Query("select avg(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'GBP'")
	 * double averageEstimatedCostOfUSGBP(int clientId);
	 * 
	 * @Query("select stddev(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'GBP'")
	 * double deviationOfEstimatedCostUSGBP(int clientId);
	 * 
	 * @Query("select min(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'GBP'")
	 * double minimumEstimatedCostOfUSGBP(int clientId);
	 * 
	 * @Query("select max(us.estimatedCost.amount) from UserStory us where us.draftMode = false and us.manager.id = :clientId and us.estimatedCost.currency = 'GBP'")
	 * double maximumEstimatedCostOfUSGBP(int clientId);
	 * 
	 * @Query("select avg(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'USD'")
	 * double averageProjectCostUSD(int clientId);
	 * 
	 * @Query("select stddev(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'USD'")
	 * double deviationOfProjectCostUSD(int clientId);
	 * 
	 * @Query("select min(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'USD'")
	 * double minimumProjectCostUSD(int clientId);
	 * 
	 * @Query("select max(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.estimatedCost.currency = 'USD'")
	 * double maximumProjectCostUSD(int clientId);
	 * 
	 * @Query("select avg(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'EUR'")
	 * double averageProjectCostEUR(int clientId);
	 * 
	 * @Query("select stddev(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'EUR'")
	 * double deviationOfProjectCostEUR(int clientId);
	 * 
	 * @Query("select min(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'EUR'")
	 * double minimumProjectCostEUR(int clientId);
	 * 
	 * @Query("select max(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.estimatedCost.currency = 'EUR'")
	 * double maximumProjectCostEUR(int clientId);
	 * 
	 * @Query("select avg(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'GBP'")
	 * double averageProjectCostGBP(int clientId);
	 * 
	 * @Query("select stddev(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'GBP'")
	 * double deviationOfProjectCostGBP(int clientId);
	 * 
	 * @Query("select min(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.cost.currency = 'GBP'")
	 * double minimumProjectCostGBP(int clientId);
	 * 
	 * @Query("select max(p.cost.amount) from Project p where p.draftMode = false and p.manager.id = :clientId and p.estimatedCost.currency = 'GBP'")
	 * double maximumProjectCostGBP(int clientId);
	 */

}
