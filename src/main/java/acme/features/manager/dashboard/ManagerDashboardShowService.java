
package acme.features.manager.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ManagerDashboard;
import acme.roles.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, ManagerDashboard> {

	@Autowired
	private ManagerDashboardRepository repository;


	@Override
	public void load() {
		ManagerDashboard dashboard;
		int managerId;

		managerId = super.getRequest().getPrincipal().getActiveRoleId();
		dashboard = new ManagerDashboard();

		int totalNumOfMustUserStories;
		int totalNumOfShouldUserStories;
		int totalNumOfCouldUserStories;
		int totalNumOfWontUserStories;

		Double averageEstimatedCostOfUSUSD;
		double deviationOfEstimatedCostUSUSD;
		double minimumEstimatedCostOfUSUSD;
		double maximumEstimatedCostOfUSUSD;
		Double averageProjectCostUSD;
		double deviationOfProjectCostUSD;
		double minimumProjectCostUSD;
		double maximumProjectCostUSD;

		Double averageEstimatedCostOfUSEUR;
		double deviationOfEstimatedCostUSEUR;
		double minimumEstimatedCostOfUSEUR;
		double maximumEstimatedCostOfUSEUR;
		Double averageProjectCostEUR;
		double deviationOfProjectCostEUR;
		double minimumProjectCostEUR;
		double maximumProjectCostEUR;

		Double averageEstimatedCostOfUSGBP;
		double deviationOfEstimatedCostUSGBP;
		double minimumEstimatedCostOfUSGBP;
		double maximumEstimatedCostOfUSGBP;
		Double averageProjectCostGBP;
		double deviationOfProjectCostGBP;
		double minimumProjectCostGBP;
		double maximumProjectCostGBP;

		try {
			totalNumOfMustUserStories = this.repository.totalNumOfMustUserStories(managerId);
			totalNumOfShouldUserStories = this.repository.totalNumOfShouldUserStories(managerId);
			totalNumOfCouldUserStories = this.repository.totalNumOfCouldUserStories(managerId);
			totalNumOfWontUserStories = this.repository.totalNumOfWontUserStories(managerId);
		} catch (Exception e) {
			totalNumOfMustUserStories = 0;
			totalNumOfShouldUserStories = 0;
			totalNumOfCouldUserStories = 0;
			totalNumOfWontUserStories = 0;
		}

		/*
		 * try {
		 * averageEstimatedCostOfUSUSD = this.repository.averageEstimatedCostOfUSUSD(managerId);
		 * deviationOfEstimatedCostUSUSD = this.repository.deviationOfEstimatedCostUSUSD(managerId);
		 * minimumEstimatedCostOfUSUSD = this.repository.minimumEstimatedCostOfUSUSD(managerId);
		 * maximumEstimatedCostOfUSUSD = this.repository.maximumEstimatedCostOfUSUSD(managerId);
		 * } catch (Exception e) {
		 * averageEstimatedCostOfUSUSD = 0.0;
		 * deviationOfEstimatedCostUSUSD = 0;
		 * minimumEstimatedCostOfUSUSD = 0;
		 * maximumEstimatedCostOfUSUSD = 0;
		 * }
		 * 
		 * try {
		 * averageEstimatedCostOfUSEUR = this.repository.averageEstimatedCostOfUSEUR(managerId);
		 * deviationOfEstimatedCostUSEUR = this.repository.deviationOfEstimatedCostUSEUR(managerId);
		 * minimumEstimatedCostOfUSEUR = this.repository.minimumEstimatedCostOfUSEUR(managerId);
		 * maximumEstimatedCostOfUSEUR = this.repository.maximumEstimatedCostOfUSEUR(managerId);
		 * } catch (Exception e) {
		 * averageEstimatedCostOfUSEUR = 0.0;
		 * deviationOfEstimatedCostUSEUR = 0;
		 * minimumEstimatedCostOfUSEUR = 0;
		 * maximumEstimatedCostOfUSEUR = 0;
		 * }
		 * 
		 * try {
		 * averageEstimatedCostOfUSGBP = this.repository.averageEstimatedCostOfUSGBP(managerId);
		 * deviationOfEstimatedCostUSGBP = this.repository.deviationOfEstimatedCostUSGBP(managerId);
		 * minimumEstimatedCostOfUSGBP = this.repository.minimumEstimatedCostOfUSGBP(managerId);
		 * maximumEstimatedCostOfUSGBP = this.repository.maximumEstimatedCostOfUSGBP(managerId);
		 * } catch (Exception e) {
		 * averageEstimatedCostOfUSGBP = 0.0;
		 * deviationOfEstimatedCostUSGBP = 0;
		 * minimumEstimatedCostOfUSGBP = 0;
		 * maximumEstimatedCostOfUSGBP = 0;
		 * }
		 * 
		 * try {
		 * averageProjectCostUSD = this.repository.averageProjectCostUSD(managerId);
		 * deviationOfProjectCostUSD = this.repository.deviationOfProjectCostUSD(managerId);
		 * minimumProjectCostUSD = this.repository.minimumProjectCostUSD(managerId);
		 * maximumProjectCostUSD = this.repository.maximumProjectCostUSD(managerId);
		 * } catch (Exception e) {
		 * averageProjectCostUSD = 0.0;
		 * deviationOfProjectCostUSD = 0;
		 * minimumProjectCostUSD = 0;
		 * maximumProjectCostUSD = 0;
		 * }
		 * 
		 * try {
		 * averageProjectCostEUR = this.repository.averageProjectCostEUR(managerId);
		 * deviationOfProjectCostEUR = this.repository.deviationOfProjectCostEUR(managerId);
		 * minimumProjectCostEUR = this.repository.minimumProjectCostEUR(managerId);
		 * maximumProjectCostEUR = this.repository.maximumProjectCostEUR(managerId);
		 * } catch (Exception e) {
		 * averageProjectCostEUR = 0.0;
		 * deviationOfProjectCostEUR = 0;
		 * minimumProjectCostEUR = 0;
		 * maximumProjectCostEUR = 0;
		 * }
		 * 
		 * try {
		 * averageProjectCostGBP = this.repository.averageProjectCostGBP(managerId);
		 * deviationOfProjectCostGBP = this.repository.deviationOfProjectCostGBP(managerId);
		 * minimumProjectCostGBP = this.repository.minimumProjectCostGBP(managerId);
		 * maximumProjectCostGBP = this.repository.maximumProjectCostGBP(managerId);
		 * } catch (Exception e) {
		 * averageProjectCostGBP = 0.0;
		 * deviationOfProjectCostGBP = 0;
		 * minimumProjectCostGBP = 0;
		 * maximumProjectCostGBP = 0;
		 * }
		 */
		dashboard.setTotalNumOfMustUserStories(totalNumOfMustUserStories);
		dashboard.setTotalNumOfShouldUserStories(totalNumOfShouldUserStories);
		dashboard.setTotalNumOfCouldUserStories(totalNumOfCouldUserStories);
		dashboard.setTotalNumOfWontUserStories(totalNumOfWontUserStories);

		/*
		 * dashboard.setAverageEstimatedCostOfUSUSD(averageEstimatedCostOfUSUSD);
		 * dashboard.setDeviationOfEstimatedCostUSUSD(deviationOfEstimatedCostUSEUR);
		 * dashboard.setMinimumEstimatedCostOfUSUSD(minimumEstimatedCostOfUSUSD);
		 * dashboard.setMaximumEstimatedCostOfUSUSD(maximumEstimatedCostOfUSUSD);
		 * dashboard.setAverageProjectCostUSD(averageProjectCostUSD);
		 * dashboard.setDeviationOfProjectCostUSD(deviationOfProjectCostUSD);
		 * dashboard.setMinimumProjectCostUSD(minimumProjectCostUSD);
		 * dashboard.setMaximumProjectCostUSD(maximumProjectCostUSD);
		 * 
		 * dashboard.setAverageEstimatedCostOfUSEUR(averageEstimatedCostOfUSEUR);
		 * dashboard.setDeviationOfEstimatedCostUSEUR(deviationOfEstimatedCostUSEUR);
		 * dashboard.setMinimumEstimatedCostOfUSEUR(minimumEstimatedCostOfUSEUR);
		 * dashboard.setMaximumEstimatedCostOfUSEUR(maximumEstimatedCostOfUSEUR);
		 * dashboard.setAverageProjectCostEUR(averageProjectCostEUR);
		 * dashboard.setDeviationOfProjectCostEUR(deviationOfProjectCostEUR);
		 * dashboard.setMinimumProjectCostEUR(minimumProjectCostEUR);
		 * dashboard.setMaximumProjectCostEUR(maximumProjectCostEUR);
		 * 
		 * dashboard.setAverageEstimatedCostOfUSGBP(averageEstimatedCostOfUSGBP);
		 * dashboard.setDeviationOfEstimatedCostUSGBP(deviationOfEstimatedCostUSGBP);
		 * dashboard.setMinimumEstimatedCostOfUSGBP(minimumEstimatedCostOfUSGBP);
		 * dashboard.setMaximumEstimatedCostOfUSGBP(maximumEstimatedCostOfUSGBP);
		 * dashboard.setAverageProjectCostGBP(averageProjectCostGBP);
		 * dashboard.setDeviationOfProjectCostGBP(deviationOfProjectCostGBP);
		 * dashboard.setMinimumProjectCostGBP(minimumProjectCostGBP);
		 * dashboard.setMaximumProjectCostGBP(maximumProjectCostGBP);
		 */
	}

	@Override
	public void unbind(final ManagerDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumOfMustUserStories", "totalNumOfShouldUserStories", "totalNumOfCouldUserStories", "totalNumOfWontUserStories", "averageEstimatedCostOfUSUSD", "deviationOfEstimatedCostUSUSD", "minimumEstimatedCostOfUSUSD",
			"maximumEstimatedCostOfUSUSD", "averageProjectCostUSD", "deviationOfProjectCostUSD", "minimumProjectCostUSD", "maximumProjectCostUSD", "averageEstimatedCostOfUSEUR", "deviationOfEstimatedCostUSEUR", "minimumEstimatedCostOfUSEUR",
			"maximumEstimatedCostOfUSEUR", "averageProjectCostEUR", "deviationOfProjectCostEUR", "minimumProjectCostEUR", "maximumProjectCostEUR", "averageEstimatedCostOfUSGBP", "deviationOfEstimatedCostUSGBP", "minimumEstimatedCostOfUSGBP",
			"maximumEstimatedCostOfUSGBP", "averageProjectCostGBP", "deviationOfProjectCostGBP", "minimumProjectCostGBP", "maximumProjectCostGBP");

		super.getResponse().addData(dataset);
	}

}
