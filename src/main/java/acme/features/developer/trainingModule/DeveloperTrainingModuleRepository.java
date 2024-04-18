
package acme.features.developer.trainingModule;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.training.TrainingModule;
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

	@Query("select t from TrainingModule t where t.code = :code")
	TrainingModule findTrainingModuleByCode(String code);
}
