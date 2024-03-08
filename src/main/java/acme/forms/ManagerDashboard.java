
package acme.forms;

import javax.validation.constraints.Min;

import acme.client.data.AbstractForm;

public class ManagerDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	// Attributes

	//User Story Priorities
	@Min(0)
	int							totalNumOfMustUserStories;

	@Min(0)
	int							totalNumOfShouldUserStories;

	@Min(0)
	int							totalNumOfCouldUserStories;

	@Min(0)
	int							totalNumOfWontUserStories;

	//User Story Cost 
	@Min(0)
	Double						averageEstimatedCostOfUS;

	double						deviationOfEstimatedCostUS;

	@Min(0)
	double						minimumEstimatedCostOfUS;

	@Min(0)
	double						maximumEstimatedCostOfUS;

	//Project Cost
	@Min(0)
	Double						averageProjectCost;

	double						deviationOfProjectCost;

	@Min(0)
	double						minimumProjectCost;

	@Min(0)
	double						maximumProjectCost;

}
