
package acme.forms;

import acme.client.data.AbstractForm;

public class ManagerDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	// Attributes

	int							totalNumOfMustUserStories;
	int							totalNumOfShouldUserStories;
	int							totalNumOfCouldUserStories;
	int							totalNumOfWontUserStories;

	Double						averageEstimatedCostOfUS;
	double						deviationOfEstimatedCostUS;
	double						minimumEstimatedCostOfUS;
	double						maximumEstimatedCostOfUS;

	Double						averageProjectCost;
	double						deviationOfProjectCost;
	double						minimumProjectCost;
	double						maximumProjectCost;

}
