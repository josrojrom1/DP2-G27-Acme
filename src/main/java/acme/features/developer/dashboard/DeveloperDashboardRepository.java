
package acme.features.developer.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface DeveloperDashboardRepository extends AbstractRepository {

	@Query("select count(t) from TrainingModule t where t.updateMoment != null and t.draftMode = false and t.developer.id = :developerId")
	Integer totalNumberOfTrainingModuleWithAnUpdateMoment(int developerId);

	@Query("select count(t) from TrainingSessions t where t.link != null and t.draftMode = false and t.trainingModule.developer.id = :developerId")
	Integer totalNumberOfTrainingSessionsWithALink(int developerId);

	@Query("select avg(t.totalTime) from TrainingModule t where t.draftMode = false and t.developer.id = :developerId")
	Double trainingModuleAverageTime(int developerId);

	@Query("select stddev(t.totalTime) from TrainingModule t where t.draftMode = false and t.developer.id = :developerId")
	Double trainingModuleDeviationTime(int developerId);

	@Query("select min(t.totalTime) from TrainingModule t where t.draftMode = false and t.developer.id = :developerId")
	double trainingModuleMinimumTime(int developerId);

	@Query("select max(t.totalTime) from TrainingModule t where t.draftMode = false and t.developer.id = :developerId")
	double trainingModuleMaximumTime(int developerId);

}
