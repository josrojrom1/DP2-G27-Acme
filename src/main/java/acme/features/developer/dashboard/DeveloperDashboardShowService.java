
package acme.features.developer.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.DeveloperDashboard;
import acme.roles.Developer;

@Service
public class DeveloperDashboardShowService extends AbstractService<Developer, DeveloperDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		DeveloperDashboard dashboard;
		int id;
		id = super.getRequest().getPrincipal().getActiveRoleId();
		Integer totalNumberOfTrainingModuleWithAnUpdateMoment;
		Integer totalNumberOfTrainingSessionsWithALink;
		Double trainingModuleAverageTime;
		Double trainingModuleDeviationTime;
		double trainingModuleMinimumTime;
		double trainingModuleMaximumTime;

		try {
			totalNumberOfTrainingModuleWithAnUpdateMoment = this.repository.totalNumberOfTrainingModuleWithAnUpdateMoment(id);
		} catch (Exception e) {
			totalNumberOfTrainingModuleWithAnUpdateMoment = 0;
		}

		try {
			totalNumberOfTrainingSessionsWithALink = this.repository.totalNumberOfTrainingSessionsWithALink(id);
		} catch (Exception e) {
			totalNumberOfTrainingSessionsWithALink = 0;
		}

		try {
			trainingModuleAverageTime = this.repository.trainingModuleAverageTime(id);
		} catch (Exception e) {
			trainingModuleAverageTime = 0.0;
		}

		try {
			trainingModuleDeviationTime = this.repository.trainingModuleDeviationTime(id);
		} catch (Exception e) {
			trainingModuleDeviationTime = 0.0;
		}

		try {
			trainingModuleMinimumTime = this.repository.trainingModuleMinimumTime(id);
		} catch (Exception e) {
			trainingModuleMinimumTime = -1.00;
		}

		try {
			trainingModuleMaximumTime = this.repository.trainingModuleMaximumTime(id);
		} catch (Exception e) {
			trainingModuleMaximumTime = -1.00;
		}

		dashboard = new DeveloperDashboard();
		dashboard.setTotalNumberOfTrainingModuleWithAnUpdateMoment(totalNumberOfTrainingModuleWithAnUpdateMoment);
		dashboard.setTotalNumberOfTrainingSessionsWithALink(totalNumberOfTrainingSessionsWithALink);
		dashboard.setTrainingModuleAverageTime(trainingModuleAverageTime);
		dashboard.setTrainingModuleDeviationTime(trainingModuleDeviationTime);
		dashboard.setTrainingModuleMaximumTime(trainingModuleMaximumTime);
		dashboard.setTrainingModuleMinimumTime(trainingModuleMinimumTime);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final DeveloperDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalNumberOfTrainingModuleWithAnUpdateMoment", "totalNumberOfTrainingSessionsWithALink", // 
			"trainingModuleAverageTime", "trainingModuleDeviationTime", //
			"trainingModuleMinimumTime", "trainingModuleMaximumTime");

		super.getResponse().addData(dataset);
	}

}
