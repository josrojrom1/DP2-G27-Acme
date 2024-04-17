
package acme.features.developer.trainingSessions;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.training.TrainingModule;
import acme.entities.training.TrainingSessions;
import acme.roles.Developer;

@Repository
public interface DeveloperTrainingSessionsRepository extends AbstractRepository {

	@Query("select t from TrainingSessions t where t.id = :id")
	TrainingSessions findOneTrainingSessionsById(int id);

	@Query("select t from TrainingSessions t where t.trainingModule.id = :id")
	Collection<TrainingSessions> findManyTrainingSessionsByTrainingModuleId(int id);

	@Query("select t from TrainingModule t where t.id = :id")
	TrainingModule findOneTrainingModuleById(int id);

	@Query("select d from Developer d where d.userAccount.username = :username")
	Developer findOneDeveloperByUsername(String username);

	@Query("select t.trainingModule from TrainingSessions t where t.id = :id")
	TrainingModule findOneTrainingModuleByTrainingSessionsId(int id);

}
