
package acme.forms;

import java.util.Map;

import acme.client.data.AbstractForm;

public class AdministratorDashboard extends AbstractForm {

	private static final long		serialVersionUID	= 1L;

	//total number of principals with each rol
	private Map<String, Integer>	totalNumOfPrincipal;

	//ratio of notices with both an email address and a link
	private double					ratioOfNotices;

	//ratios of critical and non-critical objectives
	private double					ratioOfCriticalObjectives;
	private double					ratioOfNonCriticalObjectives;

	//average, deviation, minimum, and maximum number of the value in the risks
	private double					riskValueAverage;
	private double					riskValueDeviation;
	private double					riskValueMinimum;
	private double					riskValueMaximum;

	//average, deviation, minimum, and maximum time of the number of claims posted over the last 10 weeks
	private double					numberOfClaimsAverage;
	private double					numberOfClaimsDeviation;
	private double					numberOfClaimsMinimum;
	private double					numberOfClaimsMaximum;

}
