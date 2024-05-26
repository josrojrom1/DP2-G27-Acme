
package acme.features.client.progressLog;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;

@Repository
public interface ClientProgressLogRepository extends AbstractRepository {

	@Query("select pl from ProgressLog pl where pl.contract.id = :contractId")
	Collection<ProgressLog> findManyProgressLogByContractId(int contractId);

	@Query("select pl from ProgressLog pl where pl.id = :id")
	ProgressLog findProgressLogById(int id);

	@Query("select c from Contract c where c.id = :contractId")
	Contract findContractById(int contractId);

	@Query("select pl from ProgressLog pl where pl.recordId = :recordId")
	ProgressLog findProgressLogByRecordId(String recordId);

	@Query("select pl from ProgressLog pl where pl.contract.id = :contractId and pl.published = true and pl.completeness = (select max(pl2.completeness) from ProgressLog pl2 where pl2.contract.id = :contractId and pl2.published = true)")
	ProgressLog findProgressLogWithMaxCompleteness(int contractId);

}
