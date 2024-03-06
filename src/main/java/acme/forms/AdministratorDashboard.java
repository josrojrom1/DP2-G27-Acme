
package acme.forms;

import java.util.Map;

import acme.client.data.AbstractForm;

public class AdministratorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	//total number of principals with each rol
	Map<String, Integer>		totalNumOfPrincipal;

	//ratio of notices with both an email address and a link
	double						ratioOfNotices;

	//ratios of critical and non-critical objectives
	double						ratioOfCriticalObjectives;
	double						ratioOfNonCriticalObjectives;

	//average, deviation, minimum, and maximum number of the value in the risks
	double						riskValueAverage;
	double						riskValueDeviation;
	double						riskValueMinimum;
	double						riskValueMaximum;

	//average, deviation, minimum, and maximum time of the number of claims posted over the last 10 weeks
	double						numberOfClaimsAverage;
	double						numberOfClaimsDeviation;
	double						numberOfClaimsMinimum;
	double						numberOfClaimsMaximum;

}
