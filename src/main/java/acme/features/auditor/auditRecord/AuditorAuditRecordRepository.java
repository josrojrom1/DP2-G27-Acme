
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.roles.Auditor;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id = :id")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select a from AuditRecord a where a.codeAudit.id = :id")
	Collection<AuditRecord> findManyAuditRecordsByCodeAuditId(int id);

	@Query("select c from CodeAudit c where c.id = :id")
	CodeAudit findOneCodeAuditById(int id);

	@Query("select a from Auditor a where a.userAccount.username = :username")
	Auditor findOneAuditorByUsername(String username);

	@Query("select a from AuditRecord a where a.codeAudit.id = :id")
	Collection<AuditRecord> findManyAuditRecordsByMasterId(int id);

	@Query("select a.codeAudit from AuditRecord a where a.id = :id")
	CodeAudit findOneCodeAuditByAuditRecordId(int id);

	@Query("select a from AuditRecord a where a.code = :code")
	AuditRecord findOneAuditRecordByCode(String code);

}
