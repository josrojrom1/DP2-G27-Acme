
package acme.features.any.codeAudit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.CodeAudit;

@Repository
public interface AnyCodeAuditRepository extends AbstractRepository {

	@Query("select c from CodeAudit c where c.draftMode = false")
	Collection<CodeAudit> findAllPublishedCodeAudits();

	@Query("select c from CodeAudit c where c.id = :id")
	CodeAudit findOneCodeAuditById(int id);

}
