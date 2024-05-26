
package acme.features.developer.trainingModule;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.training.TrainingModule;
import acme.entities.training.TrainingSessions;
import acme.roles.Developer;

@Repository
public interface DeveloperTrainingModuleRepository extends AbstractRepository {

	@Query("select t from TrainingModule t where t.developer.id = :developerId")
	Collection<TrainingModule> findManyTrainingModulesByDeveloperId(int developerId);

	@Query("select t from TrainingModule t where t.id = :id")
	TrainingModule findOneTrainingModuleById(int id);

	@Query("select d from Developer d where d.id = :id")
	Developer findDeveloperById(int id);

	@Query("select p from Project p where p.draftMode = false")
	Collection<Project> findPublishedProjects();

	@Query("select p from Project p where p.id =:id")
	Project findProjectById(int id);

	@Query("select t from TrainingModule t where t.code = :code and t.id != :id")
	TrainingModule findTrainingModuleByCode(String code, int id);

	@Query("select t from TrainingSessions t where t.trainingModule.id = :trainingModuleId")
	Collection<TrainingSessions> findManyTrainingSesionsByTrainingModuleId(int trainingModuleId);

	@Query("select count(t) from TrainingSessions t where t.trainingModule.id = :trainingModuleId and t.draftMode = true")
	Long countTrainingSessionsByTrainingModuleId(int trainingModuleId);

	@Query("select t from TrainingSessions t where t.trainingModule.id = :trainingModuleId and t.draftMode = false")
	Collection<TrainingSessions> findPublishTrainingSessionsByTrainingModuleId(int trainingModuleId);

	@Query("select t from TrainingSessions t where t.trainingModule.id = :trainingModuleId and t.draftMode = true")
	Collection<TrainingSessions> findNotPublishTrainingSessionsByTrainingModuleId(int trainingModuleId);

	@Query("select t from TrainingSessions t where t.trainingModule.id = :trainingModuleId")
	Collection<TrainingSessions> findTrainingSessionsByTrainingModuleId(int trainingModuleId);
}
