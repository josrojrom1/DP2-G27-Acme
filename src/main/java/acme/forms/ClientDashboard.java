
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	int							totalNumberOfProgLogsWithCompletenessBelow25;
	int							totalNumberOfProgLogsWithCompletenessBetween25and50;
	int							totalNumberOfProgLogsWithCompletenessBetween50and75;
	int							totalNumberOfProgLogsWithCompletenessAbove75;

	double						contractsBudgetAverageUSD;
	double						contractsBudgetDeviationUSD;
	double						contractsBudgetMinimumUSD;
	double						contractsBudgetMaximumUSD;

	double						contractsBudgetAverageEUR;
	double						contractsBudgetDeviationEUR;
	double						contractsBudgetMinimumEUR;
	double						contractsBudgetMaximumEUR;

	double						contractsBudgetAverageGBP;
	double						contractsBudgetDeviationGBP;
	double						contractsBudgetMinimumGBP;
	double						contractsBudgetMaximumGBP;

}
