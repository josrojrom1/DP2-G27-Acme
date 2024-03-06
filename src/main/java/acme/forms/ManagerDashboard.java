
package acme.forms;

import acme.client.data.AbstractForm;

public class ManagerDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	// Attributes

	Integer						totalNumOfMustUserStories;
	Integer						totalNumOfShouldUserStories;
	Integer						totalNumOfCouldUserStories;
	Integer						totalNumOfWontUserStories;

	Double						averageDeviationOfEstimatedCostUS;
	Double						averageMinimumEstimatedCostOfUS;
	Double						averageMaximumEstimatedCostOfUS;

	Double						averageDeviationOfProjectCost;
	Double						averageMinimumProjectCost;
	Double						averageMaximumProjectCost;

}
