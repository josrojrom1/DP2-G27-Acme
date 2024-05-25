
package acme.features.client.contracts;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.entities.projects.Project;
import acme.roles.Client;

@Repository
public interface ClientContractsRepository extends AbstractRepository {

	@Query("select c from Contract c where c.id = :id")
	Contract findContractById(int id);

	@Query("select c from Contract c where c.client.id = :clientId")
	List<Contract> findContractsByClientId(int clientId);

	@Query("select c from Client c where c.id = :clientId")
	Client findClientById(int clientId);

	@Query("select p from Project p where p.id = :projectId")
	Project findProjectById(int projectId);

	@Query("select c from Contract c where c.code = :code")
	Contract findContractByCode(String code);

	@Query("select p from Project p")
	Collection<Project> findAllProjects();

	@Query("select p from Project p where p.draftMode = false")
	Collection<Project> findAllPublishedProjects();

	@Query("select pl from ProgressLog pl where pl.contract.id = :contractId")
	Collection<ProgressLog> findProgressLogsByContractId(int contractId);

	@Query("select c from Contract c where c.project.id = :projectId")
	Collection<Contract> findManyContractsByProjectId(int projectId);

	@Query("select pl from ProgressLog pl where pl.contract.id = :contractId and pl.published = false")
	Collection<ProgressLog> findAnyNotPublishedProgressLogByContractId(int contractId);

}
