
package acme.features.any.codeAudit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.projects.Project;

@Repository
public interface AnyCodeAuditRepository extends AbstractRepository {

	@Query("select c from CodeAudit c where c.draftMode = false")
	Collection<CodeAudit> findAllPublishedCodeAudits();

	@Query("select c from CodeAudit c where c.id = :id")
	CodeAudit findOneCodeAuditById(int id);

	@Query("select a from AuditRecord a where a.codeAudit.id=:id")
	Collection<AuditRecord> findAuditRecordsById(int id);

	@Query("select p from Project p where p.draftMode = false")
	Collection<Project> findPublishedProjects();

}
