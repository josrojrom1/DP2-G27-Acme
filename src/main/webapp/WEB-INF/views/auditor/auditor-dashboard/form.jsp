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
			<acme:print value="${averageOfAuditRecords}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.deviation-auditRecords"/>
		</th>
		<td>
			<acme:print value="${deviationOfAuditRecords}"/>
		</td>
	</tr>
	<!-- 
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.minimum-auditRecords"/>
		</th>
		<td>
			<acme:print value="${minimumOfAuditRecords}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="auditor.auditor-dasboard.form.label.maximum-auditRecords"/>
		</th>
		<td>
			<acme:print value="${maximumOfAuditRecords}"/>
		</td>
	</tr>
	 -->
</table>
<!-- 
<h2>
	<acme:message code="auditor.auditor-dasboard.form.title.application-statuses"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"PENDING", "ACCEPTED", "REJECTED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${ratioOfPendingApplications}"/>, 
						<jstl:out value="${ratioOfAcceptedApplications}"/>, 
						<jstl:out value="${ratioOfRejectedApplications}"/>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
 -->
<acme:return/>