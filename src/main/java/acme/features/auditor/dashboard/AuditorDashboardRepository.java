
package acme.features.auditor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AuditorDashboardRepository extends AbstractRepository {

	//Code Audits
	//------- Static type
	@Query("select count(c) from CodeAudit c where c.type = acme.entities.audits.Type.STATIC and c.auditor.id = :id and c.draftMode = false")
	int totalNumberOfStaticCodeAudits(int id);
	//------- Dynamic type
	@Query("select count(c) from CodeAudit c where c.type = acme.entities.audits.Type.DYNAMIC and c.auditor.id = :id and c.draftMode = false")
	int totalNumberOfDynamicCodeAudits(int id);

	//Audit Records
	//------- Average
	@Query("select ABS(avg(TIME_TO_SEC(TIMEDIFF(au.startPeriod, au.endPeriod))) / 3600) from AuditRecord au where au.codeAudit.auditor.id = :id")
	double auditRecordsPeriodLengthAverage(int id);
	//------- Deviation
	@Query("select ABS(stddev(TIME_TO_SEC(TIMEDIFF(au.startPeriod, au.endPeriod))) / 3600) from AuditRecord au where au.codeAudit.auditor.id = :id")
	double auditRecordsPeriodLengthDeviation(int id);
	//------- Minimum
	@Query("select ABS(min(TIME_TO_SEC(TIMEDIFF(au.startPeriod, au.endPeriod))) / 3600) from AuditRecord au where au.codeAudit.auditor.id = :id")
	double auditRecordsPeriodLengthMinimum(int id);
	//------- Maximum
	@Query("select ABS(max(TIME_TO_SEC(TIMEDIFF(au.startPeriod, au.endPeriod))) / 3600) from AuditRecord au where au.codeAudit.auditor.id = :id")
	double auditRecordsPeriodLengthMaximum(int id);

}
