
package acme.forms;

import acme.client.data.AbstractForm;

public class DeveloperDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	//total number of training modules with an update moment
	int							totalNumberOfTrainingModuleWithAnUpdateMoment;

	//total number of training sessions with a link
	int							totalNumberOfTrainingModuleWithALink;

	//average, deviation, minimum, and maximum time of the training modules
	double						trainingModuleAverageTime;
	double						trainingModuleDeviationTime;
	double						trainingModuleMinimumTime;
	double						trainingModuleMaximumTime;
}
