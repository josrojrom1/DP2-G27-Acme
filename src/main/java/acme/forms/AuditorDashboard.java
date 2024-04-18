
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
	private double				AverageOfAuditRecords;
	private double				DeviationOfAuditRecords;
	private int					MinimumOfAuditRecords;
	private int					MaximumOfAuditRecords;

	//average, deviation, minimum, and maximum time of the period lengths in their audit records
	private double				AuditRecordsPeriodLengthAverage;
	private double				AuditRecordsPeriodLengthDeviation;
	private double				AuditRecordsPeriodLengthMinimum;
	private double				AuditRecordsPeriodLengthMaximum;

}
