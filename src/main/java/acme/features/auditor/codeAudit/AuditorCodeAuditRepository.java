
package acme.features.auditor.codeAudit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;

@Repository
public interface AuditorCodeAuditRepository extends AbstractRepository {

	@Query("select c from CodeAudit c where c.auditor.id = :auditorId")
	Collection<CodeAudit> findManyCodeAuditsByAuditorId(int auditorId);

	@Query("select c from CodeAudit c where c.id = :id")
	CodeAudit findOneCodeAuditById(int id);

	@Query("select a from AuditRecord a where a.codeAudit.id=:id")
	Collection<AuditRecord> findAuditRecordsById(int id);

}
