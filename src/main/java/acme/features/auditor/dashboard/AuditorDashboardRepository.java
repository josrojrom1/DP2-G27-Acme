
package acme.features.auditor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AuditorDashboardRepository extends AbstractRepository {

	@Query("select count(c) from CodeAudit c where c.type = acme.entities.audits.Type.STATIC and c.auditor.id = :id and c.draftMode = false")
	int totalNumberOfStaticCodeAudits(int id);

	@Query("select count(c) from CodeAudit c where c.type = acme.entities.audits.Type.DYNAMIC and c.auditor.id = :id and c.draftMode = false")
	int totalNumberOfDynamicCodeAudits(int id);

	@Query("select avg(select count(a) from AuditRecord a where a.codeAudit.auditor.id = d.id and a.draftMode = false) from Auditor d")
	Double averageOfAuditRecords();

	@Query("select stddev(a) from AuditRecord a where a.codeAudit.auditor.id = :id and a.draftMode = false")
	Double deviationOfAuditRecords(int id);

	//CORREGIR QUERY LA CUAL DA ERROR AL HACER SHOW DASHBOARD
	//@Query("select min(count(a)) from AuditRecord a where a.codeAudit.auditor.id = :id and a.draftMode = false")
	//int minimumOfAuditRecords(int id);

	//CORREGIR QUERY LA CUAL DA ERROR AL HACER SHOW DASHBOARD
	//@Query("select max(count(a)) from AuditRecord a where a.codeAudit.auditor.id = :id and a.draftMode = false")
	//int maximumOfAuditRecords(int id);

}
