
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.configuration.Configuration;
import acme.entities.projects.Project;
import acme.entities.projects.ProjectUserStory;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerProjectRepository extends AbstractRepository {

	@Query("select p from Project p")
	Collection<Project> getAll();

	@Query("select p from Project p where p.manager.id = :managerId")
	Collection<Project> getProjectsByManager(int managerId);

	@Query("select p from Project p where p.id = :projectId")
	Project findProjectById(int projectId);

	@Query("select p from Project p where p.code = :code")
	Project findProjectByCode(String code);

	@Query("select m from Manager m where m.id = :managerId")
	Manager findManagerById(int managerId);

	@Query("select p from ProjectUserStory p where p.project.id = :projectId")
	Collection<ProjectUserStory> getRelationsByProject(int projectId);

	@Query("select p.userStory from ProjectUserStory p where p.project.id = :projectId")
	Collection<UserStory> getUserStoryByProject(int projectId);

	@Query("select sc from Configuration sc")
	Configuration findSystemConfiguration();
}
