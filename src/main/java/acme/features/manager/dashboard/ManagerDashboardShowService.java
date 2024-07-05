
package acme.features.manager.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.PriorityEnum;
import acme.forms.ManagerDashboard;
import acme.roles.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, ManagerDashboard> {

	// Internal state ---------------------------------------------------------
	@Autowired
	protected ManagerDashboardRepository repository;

	// AbstractService Interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		Principal principal = super.getRequest().getPrincipal();
		int id = principal.getAccountId();
		Manager manager = this.repository.findManagerByAccountId(id);
		status = manager != null && principal.hasRole(Manager.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		final Principal principal = super.getRequest().getPrincipal();
		int id = principal.getAccountId();
		int managerId = this.repository.findManagerByAccountId(id).getId();

		// Total US Statistics
		int totalMustUserStories;
		int totalShouldUserStories;
		int totalCouldUserStories;
		int totalWontUserStories;

		// US Cost Statistics
		double averageUserStoryCost;
		double deviationUserStoryCost;
		double minimumUserStoryCost;
		double maximumUserStoryCost;

		// Project Cost Statistics (USD)
		double averageProjectCostUSD;
		double deviationOfProjectCostUSD;
		double minimumProjectCostUSD;
		double maximumProjectCostUSD;

		// Project Cost Statistics (EUR)
		double averageProjectCostEUR;
		double deviationOfProjectCostEUR;
		double minimumProjectCostEUR;
		double maximumProjectCostEUR;

		// Project Cost Statistics (GBP)
		double averageProjectCostGBP;
		double deviationOfProjectCostGBP;
		double minimumProjectCostGBP;
		double maximumProjectCostGBP;

		totalMustUserStories = this.repository.totalNumOfUserStoriesByPriority(managerId, PriorityEnum.MUST);
		totalShouldUserStories = this.repository.totalNumOfUserStoriesByPriority(managerId, PriorityEnum.SHOULD);
		totalCouldUserStories = this.repository.totalNumOfUserStoriesByPriority(managerId, PriorityEnum.COULD);
		totalWontUserStories = this.repository.totalNumOfUserStoriesByPriority(managerId, PriorityEnum.WONT);

		try {
			averageUserStoryCost = this.repository.findAverageUserStoryCost(managerId);
			deviationUserStoryCost = this.repository.findDeviationUserStoryCost(managerId);
			minimumUserStoryCost = this.repository.findMinimumUserStoryCost(managerId);
			maximumUserStoryCost = this.repository.findMaximumUserStoryCost(managerId);

		} catch (Exception e) {
			averageUserStoryCost = 0;
			deviationUserStoryCost = 0;
			minimumUserStoryCost = 0;
			maximumUserStoryCost = 0;
		}

		try {
			averageProjectCostUSD = this.repository.findAverageProjectCost(managerId, "USD");
			deviationOfProjectCostUSD = this.repository.findDeviationProjectCost(managerId, "USD");
			minimumProjectCostUSD = this.repository.findMinimumProjectCost(managerId, "USD");
			maximumProjectCostUSD = this.repository.findMaximumProjectCost(managerId, "USD");
		} catch (Exception e) {
			averageProjectCostUSD = 0;
			deviationOfProjectCostUSD = 0;
			minimumProjectCostUSD = 0;
			maximumProjectCostUSD = 0;
		}

		try {
			averageProjectCostEUR = this.repository.findAverageProjectCost(managerId, "EUR");
			deviationOfProjectCostEUR = this.repository.findDeviationProjectCost(managerId, "EUR");
			minimumProjectCostEUR = this.repository.findMinimumProjectCost(managerId, "EUR");
			maximumProjectCostEUR = this.repository.findMaximumProjectCost(managerId, "EUR");
		} catch (Exception e) {
			averageProjectCostEUR = 0;
			deviationOfProjectCostEUR = 0;
			minimumProjectCostEUR = 0;
			maximumProjectCostEUR = 0;
		}

		try {
			averageProjectCostGBP = this.repository.findAverageProjectCost(managerId, "GBP");
			deviationOfProjectCostGBP = this.repository.findDeviationProjectCost(managerId, "GBP");
			minimumProjectCostGBP = this.repository.findMinimumProjectCost(managerId, "GBP");
			maximumProjectCostGBP = this.repository.findMaximumProjectCost(managerId, "GBP");
		} catch (Exception e) {
			averageProjectCostGBP = 0;
			deviationOfProjectCostGBP = 0;
			minimumProjectCostGBP = 0;
			maximumProjectCostGBP = 0;
		}

		ManagerDashboard dashboard = new ManagerDashboard();

		// Total US Statistics
		dashboard.setTotalMustUserStories(totalMustUserStories);
		dashboard.setTotalShouldUserStories(totalShouldUserStories);
		dashboard.setTotalCouldUserStories(totalCouldUserStories);
		dashboard.setTotalWontUserStories(totalWontUserStories);

		// US Cost Statistics
		dashboard.setAverageUserStoryCost(averageUserStoryCost);
		dashboard.setDeviationUserStoryCost(deviationUserStoryCost);
		dashboard.setMinimumUserStoryCost(minimumUserStoryCost);
		dashboard.setMaximumUserStoryCost(maximumUserStoryCost);

		// Project Cost Statistics (USD)
		dashboard.setAverageProjectCostUSD(averageProjectCostUSD);
		dashboard.setDeviationOfProjectCostUSD(deviationOfProjectCostUSD);
		dashboard.setMinimumProjectCostUSD(minimumProjectCostUSD);
		dashboard.setMaximumProjectCostUSD(maximumProjectCostUSD);

		// Project Cost Statistics (EUR)
		dashboard.setAverageProjectCostEUR(averageProjectCostEUR);
		dashboard.setDeviationOfProjectCostEUR(deviationOfProjectCostEUR);
		dashboard.setMinimumProjectCostEUR(minimumProjectCostEUR);
		dashboard.setMaximumProjectCostEUR(maximumProjectCostEUR);

		// Project Cost Statistics (GBP)
		dashboard.setAverageProjectCostGBP(averageProjectCostGBP);
		dashboard.setDeviationOfProjectCostGBP(deviationOfProjectCostGBP);
		dashboard.setMinimumProjectCostGBP(minimumProjectCostGBP);
		dashboard.setMaximumProjectCostGBP(maximumProjectCostGBP);

		super.getBuffer().addData(dashboard);

	}

	@Override
	public void unbind(final ManagerDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalMustUserStories", "totalShouldUserStories", "totalCouldUserStories", "totalWontUserStories", "averageUserStoryCost", "deviationUserStoryCost", "minimumUserStoryCost", "maximumUserStoryCost", "averageProjectCostUSD",
			"deviationOfProjectCostUSD", "minimumProjectCostUSD", "maximumProjectCostUSD", "averageProjectCostEUR", "deviationOfProjectCostEUR", "minimumProjectCostEUR", "maximumProjectCostEUR", "averageProjectCostGBP", "deviationOfProjectCostGBP",
			"minimumProjectCostGBP", "maximumProjectCostGBP");

		super.getResponse().addData(dataset);
	}

}
