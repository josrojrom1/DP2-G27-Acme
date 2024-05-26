
package acme.features.any.progressLog;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;

@Repository
public interface AnyProgressLogRepository extends AbstractRepository {

	@Query("select pl from ProgressLog pl where pl.id = :id")
	ProgressLog findProgressLogById(int id);

	@Query("select pl from ProgressLog pl where pl.contract.id = :contractId and pl.published = true")
	Collection<ProgressLog> findManyProgressLogByContractId(int contractId);

	@Query("select c from Contract c where c.id = :id")
	Contract findContractById(int id);

}
