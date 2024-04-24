
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	//total number of code audits for “Static” and “Dynamic” types
	private int					totalNumberOfStaticCodeAudits;
	private int					totalNumberOfDynamicCodeAudits;

	//average, deviation, minimum, and maximum number of audit records in their audits
	private double				averageOfAuditRecords;
	private double				deviationOfAuditRecords;
	private int					minimumOfAuditRecords;
	private int					maximumOfAuditRecords;

	//average, deviation, minimum, and maximum time of the period lengths in their audit records
	private double				auditRecordsPeriodLengthAverage;
	private double				auditRecordsPeriodLengthDeviation;
	private double				auditRecordsPeriodLengthMinimum;
	private double				auditRecordsPeriodLengthMaximum;

}
