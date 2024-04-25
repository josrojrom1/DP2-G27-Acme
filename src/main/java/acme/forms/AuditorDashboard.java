
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	//total number of code audits for “Static” and “Dynamic” types
	int							totalNumberOfStaticCodeAudits;
	int							totalNumberOfDynamicCodeAudits;

	//average, deviation, minimum, and maximum number of audit records in their audits
	double						averageOfAuditRecords;
	double						deviationOfAuditRecords;
	int							minimumOfAuditRecords;
	int							maximumOfAuditRecords;

	//average, deviation, minimum, and maximum time of the period lengths in their audit records
	double						auditRecordsPeriodLengthAverage;
	double						auditRecordsPeriodLengthDeviation;
	double						auditRecordsPeriodLengthMinimum;
	double						auditRecordsPeriodLengthMaximum;

}
