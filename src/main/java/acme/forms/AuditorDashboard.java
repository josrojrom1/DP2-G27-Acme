
package acme.forms;

import acme.client.data.AbstractForm;

public class AuditorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	//total number of code audits for “Static” and “Dynamic” types
	private int					totalNumberOfStaticCodeAudits;
	private int					totalNumberOfDynamicCodeAudits;

	//average, deviation, minimum, and maximum number of audit records in their audits
	private double				codeAuditAverage;
	private double				codeAuditDeviation;
	private double				codeAuditMinimum;
	private double				codeAuditMaximum;

	//average, deviation, minimum, and maximum time of the period lengths in their audit records
	private double				codeAuditPeriodLengthAverage;
	private double				codeAuditPeriodLengthDeviation;
	private double				codeAuditPeriodLengthMinimum;
	private double				codeAuditPeriodLengthMaximum;

}
