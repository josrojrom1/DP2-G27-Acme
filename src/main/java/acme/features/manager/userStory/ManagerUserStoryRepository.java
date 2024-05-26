
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectUserStory;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerUserStoryRepository extends AbstractRepository {

	@Query("select us from UserStory us")
	Collection<UserStory> getAll();

	@Query("select us from UserStory us where us.id = :userStoryId")
	UserStory findUserStoryById(int userStoryId);

	@Query("select us from UserStory us where us.manager.id = :managerId")
	Collection<UserStory> findUserStoryByManager(int managerId);

	@Query("select m from Manager m where m.id = :managerId")
	Manager findManagerById(int managerId);

	@Query("select p from Project p where p.id = :projectId")
	Project findProjectById(int projectId);

	@Query("select p.userStory from ProjectUserStory p where p.project.id = :projectId")
	Collection<UserStory> getUserStoryByProject(int projectId);

	@Query("select p from ProjectUserStory p where p.userStory.id = :userStoryId")
	Collection<ProjectUserStory> getRelationsByUserStory(int userStoryId);

}
