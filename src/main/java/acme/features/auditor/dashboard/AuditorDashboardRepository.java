
package acme.features.auditor.dashboard;

import java.util.Collection;

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

	//Audit Records period
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

	//Audit record in their code audits
	//------- Average
	@Query("select avg(select count(a) from AuditRecord a where a.codeAudit.id = c.id) from CodeAudit c where c.auditor.id = :id")
	double averageAuditRecordsPerCodeAudit(int id);
	//------- Deviation
	@Query("select (select count(a) from AuditRecord a where a.codeAudit.id = c.id) from CodeAudit c where c.auditor.id = :id")
	Collection<Double> getAuditRecordsPerAudit(int id);
	//------- Minimum
	@Query("select min(select count(a) from AuditRecord a where a.codeAudit.id = c.id) from CodeAudit c where c.auditor.id = :id")
	int minimumAuditRecords(int id);
	//------- Maximum
	@Query("select max(select count(a) from AuditRecord a where a.codeAudit.id = c.id) from CodeAudit c where c.auditor.id = :id")
	int maximumAuditRecords(int id);

}
