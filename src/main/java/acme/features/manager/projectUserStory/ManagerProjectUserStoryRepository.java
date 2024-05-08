
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectUserStory;
import acme.entities.projects.UserStory;

@Repository
public interface ManagerProjectUserStoryRepository extends AbstractRepository {

	@Query("select p from Project p")
	Collection<Project> getAll();

	@Query("select p from ProjectUserStory p where p.project.id = :projectId")
	Collection<ProjectUserStory> getRelationsByProject(int projectId);

	@Query("select p from ProjectUserStory p where p.userStory.id = :userStoryId")
	Collection<ProjectUserStory> getRelationsByUserStory(int userStoryId);

	@Query("select p.userStory from ProjectUserStory p where p.project.id = :projectId")
	Collection<UserStory> getUserStoryByProject(int projectId);

	@Query("select p from Project p where p.id = :projectId")
	Project findProjectById(int projectId);
}
