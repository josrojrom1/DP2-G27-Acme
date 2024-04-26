
package acme.forms;

import javax.validation.constraints.Min;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	Double						averageEstimatedCostOfUSUSD;

	double						deviationOfEstimatedCostUSUSD;

	@Min(0)
	double						minimumEstimatedCostOfUSUSD;

	@Min(0)
	double						maximumEstimatedCostOfUSUSD;

	//Project Cost
	@Min(0)
	Double						averageProjectCostUSD;

	double						deviationOfProjectCostUSD;

	@Min(0)
	double						minimumProjectCostUSD;

	@Min(0)
	double						maximumProjectCostUSD;

	//User Story Cost 
	@Min(0)
	Double						averageEstimatedCostOfUSEUR;

	double						deviationOfEstimatedCostUSEUR;

	@Min(0)
	double						minimumEstimatedCostOfUSEUR;

	@Min(0)
	double						maximumEstimatedCostOfUSEUR;

	//Project Cost
	@Min(0)
	Double						averageProjectCostEUR;

	double						deviationOfProjectCostEUR;

	@Min(0)
	double						minimumProjectCostEUR;

	@Min(0)
	double						maximumProjectCostEUR;

	//User Story Cost 
	@Min(0)
	Double						averageEstimatedCostOfUSGBP;

	double						deviationOfEstimatedCostUSGBP;

	@Min(0)
	double						minimumEstimatedCostOfUSGBP;

	@Min(0)
	double						maximumEstimatedCostOfUSGBP;

	//Project Cost
	@Min(0)
	Double						averageProjectCostGBP;

	double						deviationOfProjectCostGBP;

	@Min(0)
	double						minimumProjectCostGBP;

	@Min(0)
	double						maximumProjectCostGBP;

}
