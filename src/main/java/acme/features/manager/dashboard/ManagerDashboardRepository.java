
package acme.features.manager.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.PriorityEnum;
import acme.roles.Manager;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select m from Manager m where m.userAccount.id = :userAccountId")
	Manager findManagerByAccountId(int userAccountId);

	@Query("select count(us) from UserStory us where us.priority = :priority and us.draftMode = false and us.manager.id = :managerId")
	int totalNumOfUserStoriesByPriority(int managerId, PriorityEnum priority);

	@Query("select avg(us.estimatedCost) FROM UserStory us WHERE us.draftMode = false and us.manager.id = :managerId ")
	double findAverageUserStoryCost(int managerId);

	@Query("select stddev(us.estimatedCost) FROM UserStory us WHERE us.draftMode = false and us.manager.id = :managerId")
	double findDeviationUserStoryCost(int managerId);

	@Query("select max(us.estimatedCost) FROM UserStory us WHERE us.draftMode = false and us.manager.id = :managerId")
	double findMaximumUserStoryCost(int managerId);

	@Query("select min(us.estimatedCost) FROM UserStory us WHERE us.draftMode = false and us.manager.id = :managerId")
	double findMinimumUserStoryCost(int managerId);

	@Query("select avg(p.cost.amount) FROM Project p WHERE p.manager.id = :managerId and p.draftMode = false and p.cost.currency = :currency")
	double findAverageProjectCost(int managerId, String currency);

	@Query("select stddev(p.cost.amount) FROM Project p WHERE p.manager.id = :managerId and p.draftMode = false and p.cost.currency = :currency")
	double findDeviationProjectCost(int managerId, String currency);

	@Query("select max(p.cost.amount) FROM Project p WHERE p.manager.id = :managerId and p.draftMode = false and p.cost.currency = :currency")
	double findMaximumProjectCost(int managerId, String currency);

	@Query("select min(p.cost.amount) FROM Project p WHERE p.manager.id = :managerId and p.draftMode = false and p.cost.currency = :currency")
	double findMinimumProjectCost(int managerId, String currency);

}
