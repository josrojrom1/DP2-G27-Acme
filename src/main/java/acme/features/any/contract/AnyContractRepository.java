
package acme.features.any.contract;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.entities.projects.Project;

@Repository
public interface AnyContractRepository extends AbstractRepository {

	@Query("select c from Contract c where c.published = true")
	Collection<Contract> findAllPublishedContracts();

	@Query("select c from Contract c where c.id = :id")
	Contract findContractById(int id);

	@Query("select pl from ProgressLog pl where pl.contract.id = :contractId")
	Collection<ProgressLog> findProgressLogsByContractId(int contractId);

	@Query("select p from Project p where p.draftMode = false")
	Collection<Project> findPublishedProjects();

}
