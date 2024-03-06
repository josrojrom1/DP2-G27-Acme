
package acme.forms;

import acme.client.data.AbstractForm;

public class AuditorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	//total number of code audits for “Static” and “Dynamic” types
	int							totalNumberOfStaticCodeAudits;
	int							totalNumberOfDynamicCodeAudits;

	//average, deviation, minimum, and maximum number of audit records in their audits
	double						codeAuditAverage;
	double						codeAuditDeviation;
	double						codeAuditMinimum;
	double						codeAuditMaximum;

	//average, deviation, minimum, and maximum time of the period lengths in their audit records
	double						codeAuditPeriodLengthAverage;
	double						codeAuditPeriodLengthDeviation;
	double						codeAuditPeriodLengthMinimum;
	double						codeAuditPeriodLengthMaximum;

}
