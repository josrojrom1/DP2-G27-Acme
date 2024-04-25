
package acme.features.auditor.dashboard;

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
			auditRecordsPeriodLengthMinimum = 0;
		}

		try {
			auditRecordsPeriodLengthMaximum = this.repository.auditRecordsPeriodLengthMaximum(id);
		} catch (Exception e) {
			auditRecordsPeriodLengthMaximum = 0;
		}

		dashboard = new AuditorDashboard();

		dashboard.setTotalNumberOfStaticCodeAudits(totalNumberOfStaticCodeAudits);
		dashboard.setTotalNumberOfDynamicCodeAudits(totalNumberOfDynamicCodeAudits);
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
			"totalNumberOfStaticCodeAudits", "totalNumberOfDynamicCodeAudits", "auditRecordsPeriodLengthAverage", "auditRecordsPeriodLengthDeviation", "auditRecordsPeriodLengthMinimum", "auditRecordsPeriodLengthMaximum");

		super.getResponse().addData(dataset);
	}

}
