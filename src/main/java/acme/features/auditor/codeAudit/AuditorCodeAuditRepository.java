
package acme.features.auditor.codeAudit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.CodeAudit;

@Repository
public interface AuditorCodeAuditRepository extends AbstractRepository {

	@Query("select c from CodeAudit c where c.auditor.id = :auditorId")
	Collection<CodeAudit> findManyCodeAuditsByAuditorId(int auditorId);

}
