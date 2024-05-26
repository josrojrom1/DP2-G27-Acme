
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	// Attributes

	//User Story Priorities
	int							totalMustUserStories;

	int							totalShouldUserStories;

	int							totalCouldUserStories;

	int							totalWontUserStories;

	//User Story Cost 
	double						averageUserStoryCost;

	double						deviationUserStoryCost;

	double						minimumUserStoryCost;

	double						maximumUserStoryCost;

	//Project Cost (USD)
	double						averageProjectCostUSD;

	double						deviationOfProjectCostUSD;

	double						minimumProjectCostUSD;

	double						maximumProjectCostUSD;

	//Project Cost (EUR)
	double						averageProjectCostEUR;

	double						deviationOfProjectCostEUR;

	double						minimumProjectCostEUR;

	double						maximumProjectCostEUR;

	//Project Cost (GBP)
	double						averageProjectCostGBP;

	double						deviationOfProjectCostGBP;

	double						minimumProjectCostGBP;

	double						maximumProjectCostGBP;

}
