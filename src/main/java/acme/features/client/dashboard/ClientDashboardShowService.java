
package acme.features.client.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ClientDashboard;
import acme.roles.Client;

@Service
public class ClientDashboardShowService extends AbstractService<Client, ClientDashboard> {

	@Autowired
	private ClientDashboardRepository repository;


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Client.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ClientDashboard dashboard;
		int clientId;

		clientId = super.getRequest().getPrincipal().getActiveRoleId();
		dashboard = new ClientDashboard();

		int totalNumberOfProgLogsWithCompletenessBelow25;
		int totalNumberOfProgLogsWithCompletenessBetween25and50;
		int totalNumberOfProgLogsWithCompletenessBetween50and75;
		int totalNumberOfProgLogsWithCompletenessAbove75;
		Double contractsBudgetAverage;
		Double contractsBudgetDeviation;
		double contractsBudgetMinimum;
		double contractsBudgetMaximum;

		totalNumberOfProgLogsWithCompletenessBelow25 = this.repository.totalNumberOfProgLogsWithCompletenessBelow25(clientId);
		totalNumberOfProgLogsWithCompletenessBetween25and50 = this.repository.totalNumberOfProgLogsWithCompletenessBetween25and50(clientId);
		totalNumberOfProgLogsWithCompletenessBetween50and75 = this.repository.totalNumberOfProgLogsWithCompletenessBetween50and75(clientId);
		totalNumberOfProgLogsWithCompletenessAbove75 = this.repository.totalNumberOfProgLogsWithCompletenessAbove75(clientId);
		contractsBudgetAverage = this.repository.contractsBudgetAverage(clientId);
		contractsBudgetDeviation = this.repository.contractsBudgetDeviation(clientId);
		contractsBudgetMinimum = this.repository.contractsBudgetMinimum(clientId);
		contractsBudgetMaximum = this.repository.contractsBudgetMaximum(clientId);

		dashboard.setTotalNumberOfProgLogsWithCompletenessBelow25(totalNumberOfProgLogsWithCompletenessBelow25);
		dashboard.setTotalNumberOfProgLogsWithCompletenessBetween25and50(totalNumberOfProgLogsWithCompletenessBetween25and50);
		dashboard.setTotalNumberOfProgLogsWithCompletenessBetween50and75(totalNumberOfProgLogsWithCompletenessBetween50and75);
		dashboard.setTotalNumberOfProgLogsWithCompletenessAbove75(totalNumberOfProgLogsWithCompletenessAbove75);
		dashboard.setContractsBudgetAverage(contractsBudgetAverage);
		dashboard.setContractsBudgetDeviation(contractsBudgetDeviation);
		dashboard.setContractsBudgetMinimum(contractsBudgetMinimum);
		dashboard.setContractsBudgetMaximum(contractsBudgetMaximum);

		super.getBuffer().addData(dashboard);

	}

	@Override
	public void unbind(final ClientDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumberOfProgLogsWithCompletenessBelow25", "totalNumberOfProgLogsWithCompletenessBetween25and50", "totalNumberOfProgLogsWithCompletenessBetween50and75", "totalNumberOfProgLogsWithCompletenessAbove75",
			"contractsBudgetAverage", "contractsBudgetDeviation", "contractsBudgetMinimum", "contractsBudgetMaximum");

		super.getResponse().addData(dataset);
	}

}
