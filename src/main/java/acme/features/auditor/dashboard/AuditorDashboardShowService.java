
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
	public void authorise() {//REPASAR AUTHORISE!!!!!!!!!!!!!!!!!!
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		AuditorDashboard dashboard;
		int id;
		id = super.getRequest().getPrincipal().getActiveRoleId();
		int totalNumberOfStaticCodeAudits;
		int totalNumberOfDynamicCodeAudits;

		Double averageOfAuditRecords;
		Double deviationOfAuditRecords;
		//int minimumOfAuditRecords;
		//int maximumOfAuditRecords;

		//Double auditRecordsPeriodLengthAverage;
		//Double auditRecordsPeriodLengthDeviation;
		//Double auditRecordsPeriodLengthMinimum;
		//Double auditRecordsPeriodLengthMaximum;

		totalNumberOfStaticCodeAudits = this.repository.totalNumberOfStaticCodeAudits(id);
		totalNumberOfDynamicCodeAudits = this.repository.totalNumberOfDynamicCodeAudits(id);
		averageOfAuditRecords = this.repository.averageOfAuditRecords();
		deviationOfAuditRecords = this.repository.deviationOfAuditRecords(id);
		//minimumOfAuditRecords = this.repository.minimumOfAuditRecords(id);
		//maximumOfAuditRecords = this.repository.maximumOfAuditRecords(id);

		dashboard = new AuditorDashboard();

		dashboard.setTotalNumberOfStaticCodeAudits(totalNumberOfStaticCodeAudits);
		dashboard.setTotalNumberOfDynamicCodeAudits(totalNumberOfDynamicCodeAudits);
		dashboard.setAverageOfAuditRecords(averageOfAuditRecords);
		dashboard.setDeviationOfAuditRecords(deviationOfAuditRecords);
		//dashboard.setMinimumOfAuditRecords(0);//CORREGIR QUERY CORRESPONDIENTE
		//dashboard.setMaximumOfAuditRecords(0);//CORREGIR QUERY CORRESPONDIENTE
		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final AuditorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalNumberOfStaticCodeAudits", "totalNumberOfDynamicCodeAudits", "averageOfAuditRecords", "deviationOfAuditRecords"/* , "minimumOfAuditRecords", "maximumOfAuditRecords" */);

		super.getResponse().addData(dataset);
	}

}
