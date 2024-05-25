
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
	int							totalMustUserStories;

	@Min(0)
	int							totalShouldUserStories;

	@Min(0)
	int							totalCouldUserStories;

	@Min(0)
	int							totalWontUserStories;

	//User Story Cost 
	@Min(0)
	double						averageUserStoryCost;

	double						deviationUserStoryCost;

	@Min(0)
	double						minimumUserStoryCost;

	@Min(0)
	double						maximumUserStoryCost;

	//Project Cost (USD)
	@Min(0)
	double						averageProjectCostUSD;

	double						deviationOfProjectCostUSD;

	@Min(0)
	double						minimumProjectCostUSD;

	@Min(0)
	double						maximumProjectCostUSD;

	//Project Cost (EUR)
	@Min(0)
	double						averageProjectCostEUR;

	double						deviationOfProjectCostEUR;

	@Min(0)
	double						minimumProjectCostEUR;

	@Min(0)
	double						maximumProjectCostEUR;

	//Project Cost (GBP)
	@Min(0)
	double						averageProjectCostGBP;

	double						deviationOfProjectCostGBP;

	@Min(0)
	double						minimumProjectCostGBP;

	@Min(0)
	double						maximumProjectCostGBP;

}
