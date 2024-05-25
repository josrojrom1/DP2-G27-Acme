
package acme.features.auditor.dashboard;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.AuditorDashboard;
import acme.roles.Auditor;

@Service
public class AuditorDashboardShowService extends AbstractService<Auditor, AuditorDashboard> {

	@Autowired
	private AuditorDashboardRepository repository;


	@Override
	public void authorise() {
		boolean status;
		status = super.getRequest().getPrincipal().hasRole(Auditor.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditorDashboard dashboard;
		int id;
		id = super.getRequest().getPrincipal().getActiveRoleId();
		int totalNumberOfStaticCodeAudits;
		int totalNumberOfDynamicCodeAudits;

		double averageOfAuditRecords;
		Collection<Double> auditRecordPerCodeAudit = this.repository.getAuditRecordsPerAudit(id);
		double deviationOfAuditRecords;
		int minimumOfAuditRecords;
		int maximumOfAuditRecords;

		double auditRecordsPeriodLengthAverage;
		double auditRecordsPeriodLengthDeviation;
		double auditRecordsPeriodLengthMinimum;
		double auditRecordsPeriodLengthMaximum;

		try {
			totalNumberOfStaticCodeAudits = this.repository.totalNumberOfStaticCodeAudits(id);
		} catch (Exception e) {
			totalNumberOfStaticCodeAudits = 0;
		}

		try {
			totalNumberOfDynamicCodeAudits = this.repository.totalNumberOfDynamicCodeAudits(id);
		} catch (Exception e) {
			totalNumberOfDynamicCodeAudits = 0;
		}

		try {
			auditRecordsPeriodLengthAverage = this.repository.auditRecordsPeriodLengthAverage(id);
		} catch (Exception e) {
			auditRecordsPeriodLengthAverage = 0;
		}

		try {
			auditRecordsPeriodLengthDeviation = this.repository.auditRecordsPeriodLengthDeviation(id);
		} catch (Exception e) {
			auditRecordsPeriodLengthDeviation = 0;
		}

		try {
			auditRecordsPeriodLengthMinimum = this.repository.auditRecordsPeriodLengthMinimum(id);
		} catch (Exception e) {
			auditRecordsPeriodLengthMinimum = -1;
		}

		try {
			auditRecordsPeriodLengthMaximum = this.repository.auditRecordsPeriodLengthMaximum(id);
		} catch (Exception e) {
			auditRecordsPeriodLengthMaximum = -1;
		}

		try {
			averageOfAuditRecords = this.repository.averageAuditRecordsPerCodeAudit(id);
		} catch (Exception e) {
			averageOfAuditRecords = 0;
		}

		try {
			deviationOfAuditRecords = this.deviation(auditRecordPerCodeAudit);
		} catch (Exception e) {
			deviationOfAuditRecords = 0;
		}

		try {
			minimumOfAuditRecords = this.repository.minimumAuditRecords(id);
		} catch (Exception e) {
			minimumOfAuditRecords = -1;
		}

		try {
			maximumOfAuditRecords = this.repository.maximumAuditRecords(id);
		} catch (Exception e) {
			maximumOfAuditRecords = -1;
		}

		dashboard = new AuditorDashboard();

		dashboard.setTotalNumberOfStaticCodeAudits(totalNumberOfStaticCodeAudits);
		dashboard.setTotalNumberOfDynamicCodeAudits(totalNumberOfDynamicCodeAudits);

		dashboard.setAverageOfAuditRecords(averageOfAuditRecords);
		dashboard.setDeviationOfAuditRecords(deviationOfAuditRecords);
		dashboard.setMinimumOfAuditRecords(minimumOfAuditRecords);
		dashboard.setMaximumOfAuditRecords(maximumOfAuditRecords);

		dashboard.setAuditRecordsPeriodLengthAverage(auditRecordsPeriodLengthAverage);
		dashboard.setAuditRecordsPeriodLengthDeviation(auditRecordsPeriodLengthDeviation);
		dashboard.setAuditRecordsPeriodLengthMinimum(auditRecordsPeriodLengthMinimum);
		dashboard.setAuditRecordsPeriodLengthMaximum(auditRecordsPeriodLengthMaximum);
		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final AuditorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalNumberOfStaticCodeAudits", "totalNumberOfDynamicCodeAudits", "averageOfAuditRecords", "deviationOfAuditRecords", "minimumOfAuditRecords", "maximumOfAuditRecords", "auditRecordsPeriodLengthAverage", "auditRecordsPeriodLengthDeviation",
			"auditRecordsPeriodLengthMinimum", "auditRecordsPeriodLengthMaximum");

		super.getResponse().addData(dataset);
	}

	public Double deviation(final Collection<Double> values) {
		if (values.isEmpty())
			return 1.0 * 0;
		return values.stream().reduce(0.0, (sum, value) -> sum + Math.pow(value - this.average(values), 2), Double::sum) / values.size();
	}

	private Double average(final Collection<Double> values) {
		return values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
	}

}
