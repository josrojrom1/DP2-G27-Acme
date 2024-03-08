
package acme.forms;

import acme.client.data.AbstractForm;

public class DeveloperDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	//total number of training modules with an update moment
	Integer						totalNumberOfTrainingModuleWithAnUpdateMoment;

	//total number of training sessions with a link
	Integer						totalNumberOfTrainingModuleWithALink;

	//average, deviation, minimum, and maximum time of the training modules
	Double						trainingModuleAverageTime;
	Double						trainingModuleDeviationTime;
	double						trainingModuleMinimumTime;
	double						trainingModuleMaximumTime;
}
