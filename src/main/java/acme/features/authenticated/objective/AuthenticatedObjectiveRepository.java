
package acme.features.authenticated.objective;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.objectives.Objective;

@Repository
public interface AuthenticatedObjectiveRepository extends AbstractRepository {

	@Query("select a from Objective a where a.id = :id")
	Objective findOneObjectiveById(int id);

	@Query("select c from Objective c")
	Collection<Objective> findManyObjectives();

}
