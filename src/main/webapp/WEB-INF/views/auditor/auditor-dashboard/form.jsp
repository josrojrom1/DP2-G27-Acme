<%--
- form.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="auditor.auditor-dasboard.form.title.audits.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.total-number-static-type-codeAudits"/>
		</th>
		<td>
		
				<acme:print value="${totalNumberOfStaticCodeAudits}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.total-number-dynamic-type-codeAudits"/>
		</th>
		<td>
		
				<acme:print value="${totalNumberOfDynamicCodeAudits}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="auditor.auditor-dasboard.form.title.auditRecords.general-indicators"/>
</h2>

<table class="table table-sm">

	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.average-auditRecords"/>
		</th>
		<td>			
			<jstl:if test="${averageOfAuditRecords != 0 }">
		
				<acme:print value="${averageOfAuditRecords}"/>
			</jstl:if>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.deviation-auditRecords"/>
		</th>
		<td>			
			<jstl:if test="${deviationOfAuditRecords != 0 }">
		
				<acme:print value="${deviationOfAuditRecords}"/>
			</jstl:if>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.minimum-auditRecords"/>
		</th>
		<td>			
			<jstl:if test="${minimumOfAuditRecords > -1 }">
				<acme:print value="${minimumOfAuditRecords}"/>
			</jstl:if>
				
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.maximum-auditRecords"/>
		</th>
		<td>			
			<jstl:if test="${maximumOfAuditRecords > -1 }">
				<acme:print value="${maximumOfAuditRecords}"/>
			</jstl:if>
				
		</td>
	</tr>
	
	
	
	
</table>
	

<h2>
	<acme:message code="auditor.auditor-dasboard.form.title.auditRecords.period-indicators"/>
</h2>

<table class="table table-sm">



	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.average-auditRecords-periodLength"/>
		</th>
		<td>			
			<jstl:if test="${auditRecordsPeriodLengthAverage != 0 }">
		
				<acme:print value="${auditRecordsPeriodLengthAverage}"/>
			</jstl:if>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.deviation-auditRecords-periodLength"/>
		</th>
		<td>			
			<jstl:if test="${auditRecordsPeriodLengthDeviation != 0 }">
		
				<acme:print value="${auditRecordsPeriodLengthDeviation}"/>
			</jstl:if>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.min-auditRecords-periodLength"/>
		</th>
		<td>			
			<jstl:if test="${auditRecordsPeriodLengthMinimum > -1 }">
				<acme:print value="${auditRecordsPeriodLengthMinimum}"/>
			</jstl:if>
			
		</td>
	</tr>
	
		<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.max-auditRecords-periodLength"/>
		</th>
		<td>			
			<jstl:if test="${auditRecordsPeriodLengthMaximum > -1 }">
				<acme:print value="${auditRecordsPeriodLengthMaximum}"/>
			</jstl:if>
				
		</td>
	</tr>
	

</table>

<acme:return/>