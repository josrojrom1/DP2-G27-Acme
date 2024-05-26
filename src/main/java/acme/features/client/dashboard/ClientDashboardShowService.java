
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
		double contractsBudgetAverageUSD;
		double contractsBudgetDeviationUSD;
		double contractsBudgetMinimumUSD;
		double contractsBudgetMaximumUSD;
		double contractsBudgetAverageEUR;
		double contractsBudgetDeviationEUR;
		double contractsBudgetMinimumEUR;
		double contractsBudgetMaximumEUR;
		double contractsBudgetAverageGBP;
		double contractsBudgetDeviationGBP;
		double contractsBudgetMinimumGBP;
		double contractsBudgetMaximumGBP;

		try {
			totalNumberOfProgLogsWithCompletenessBelow25 = this.repository.totalNumberOfProgLogsWithCompletenessBelow25(clientId);
			totalNumberOfProgLogsWithCompletenessBetween25and50 = this.repository.totalNumberOfProgLogsWithCompletenessBetween25and50(clientId);
			totalNumberOfProgLogsWithCompletenessBetween50and75 = this.repository.totalNumberOfProgLogsWithCompletenessBetween50and75(clientId);
			totalNumberOfProgLogsWithCompletenessAbove75 = this.repository.totalNumberOfProgLogsWithCompletenessAbove75(clientId);
		} catch (Exception e) {
			totalNumberOfProgLogsWithCompletenessBelow25 = 0;
			totalNumberOfProgLogsWithCompletenessBetween25and50 = 0;
			totalNumberOfProgLogsWithCompletenessBetween50and75 = 0;
			totalNumberOfProgLogsWithCompletenessAbove75 = 0;
		}

		try {
			contractsBudgetAverageUSD = this.repository.contractsBudgetAverageUSD(clientId);
		} catch (Exception e) {
			contractsBudgetAverageUSD = 0;
		}

		try {
			contractsBudgetDeviationUSD = this.repository.contractsBudgetDeviationUSD(clientId);
		} catch (Exception e) {
			contractsBudgetDeviationUSD = -1;
		}

		try {
			contractsBudgetMinimumUSD = this.repository.contractsBudgetMinimumUSD(clientId);
		} catch (Exception e) {
			contractsBudgetMinimumUSD = 0;
		}

		try {
			contractsBudgetMaximumUSD = this.repository.contractsBudgetMaximumUSD(clientId);
		} catch (Exception e) {
			contractsBudgetMaximumUSD = 0;
		}

		try {
			contractsBudgetAverageEUR = this.repository.contractsBudgetAverageEUR(clientId);
		} catch (Exception e) {
			contractsBudgetAverageEUR = 0;
		}

		try {
			contractsBudgetDeviationEUR = this.repository.contractsBudgetDeviationEUR(clientId);
		} catch (Exception e) {
			contractsBudgetDeviationEUR = -1;
		}

		try {
			contractsBudgetMinimumEUR = this.repository.contractsBudgetMinimumEUR(clientId);
		} catch (Exception e) {
			contractsBudgetMinimumEUR = 0;
		}

		try {
			contractsBudgetMaximumEUR = this.repository.contractsBudgetMaximumEUR(clientId);
		} catch (Exception e) {
			contractsBudgetMaximumEUR = 0;
		}

		try {
			contractsBudgetAverageGBP = this.repository.contractsBudgetAverageGBP(clientId);
		} catch (Exception e) {
			contractsBudgetAverageGBP = 0;
		}

		try {
			contractsBudgetDeviationGBP = this.repository.contractsBudgetDeviationGBP(clientId);
		} catch (Exception e) {
			contractsBudgetDeviationGBP = -1;
		}

		try {
			contractsBudgetMinimumGBP = this.repository.contractsBudgetMinimumGBP(clientId);
		} catch (Exception e) {
			contractsBudgetMinimumGBP = 0;
		}

		try {
			contractsBudgetMaximumGBP = this.repository.contractsBudgetMaximumGBP(clientId);
		} catch (Exception e) {
			contractsBudgetMaximumGBP = 0;
		}

		dashboard.setTotalNumberOfProgLogsWithCompletenessBelow25(totalNumberOfProgLogsWithCompletenessBelow25);
		dashboard.setTotalNumberOfProgLogsWithCompletenessBetween25and50(totalNumberOfProgLogsWithCompletenessBetween25and50);
		dashboard.setTotalNumberOfProgLogsWithCompletenessBetween50and75(totalNumberOfProgLogsWithCompletenessBetween50and75);
		dashboard.setTotalNumberOfProgLogsWithCompletenessAbove75(totalNumberOfProgLogsWithCompletenessAbove75);
		dashboard.setContractsBudgetAverageUSD(contractsBudgetAverageUSD);
		dashboard.setContractsBudgetDeviationUSD(contractsBudgetDeviationUSD);
		dashboard.setContractsBudgetMinimumUSD(contractsBudgetMinimumUSD);
		dashboard.setContractsBudgetMaximumUSD(contractsBudgetMaximumUSD);
		dashboard.setContractsBudgetAverageEUR(contractsBudgetAverageEUR);
		dashboard.setContractsBudgetDeviationEUR(contractsBudgetDeviationEUR);
		dashboard.setContractsBudgetMinimumEUR(contractsBudgetMinimumEUR);
		dashboard.setContractsBudgetMaximumEUR(contractsBudgetMaximumEUR);
		dashboard.setContractsBudgetAverageGBP(contractsBudgetAverageGBP);
		dashboard.setContractsBudgetDeviationGBP(contractsBudgetDeviationGBP);
		dashboard.setContractsBudgetMinimumGBP(contractsBudgetMinimumGBP);
		dashboard.setContractsBudgetMaximumGBP(contractsBudgetMaximumGBP);

		super.getBuffer().addData(dashboard);

	}

	@Override
	public void unbind(final ClientDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumberOfProgLogsWithCompletenessBelow25", "totalNumberOfProgLogsWithCompletenessBetween25and50", "totalNumberOfProgLogsWithCompletenessBetween50and75", "totalNumberOfProgLogsWithCompletenessAbove75",
			"contractsBudgetAverageUSD", "contractsBudgetDeviationUSD", "contractsBudgetMinimumUSD", "contractsBudgetMaximumUSD", "contractsBudgetAverageEUR", "contractsBudgetDeviationEUR", "contractsBudgetMinimumEUR", "contractsBudgetMaximumEUR",
			"contractsBudgetAverageGBP", "contractsBudgetDeviationGBP", "contractsBudgetMinimumGBP", "contractsBudgetMaximumGBP");

		super.getResponse().addData(dataset);
	}

}
