<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="developer.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	
	<tr>
	<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.total-number-training-update"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTrainingModuleWithAnUpdateMoment}"/>
		</td>
	</tr>
		
	<tr>
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.total-number-training-link"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTrainingSessionsWithALink}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.training-module-average-time"/>
		</th>
		<td>
			<acme:print value="${trainingModuleAverageTime}"/>
		</td>
	</tr>

	<tr>
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.training-module-deviation-time"/>
		</th>
		<td>
			<acme:print value="${trainingModuleDeviationTime}"/>
		</td>
	</tr>

	<tr>
	<jstl:if test="${trainingModuleMinimumTime != -1.00}">
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.training-module-minimum-time"/>
		</th>
		<td>
			<acme:print value="${trainingModuleMinimumTime}"/>
		</td>
		</jstl:if>	
	</tr>
	<tr>
	<jstl:if test="${trainingModuleMinimumTime == -1.00}">
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.training-module-minimum-time"/>
		</th>
		<td>
			<acme:print value="${''}"/>
		</td>
		</jstl:if>	
	</tr>
	
	<tr>
	<jstl:if test="${trainingModuleMaximumTime != -1.00}">
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.training-module-minimum-time"/>
		</th>
		<td>
			<acme:print value="${trainingModuleMaximumTime}"/>
		</td>
		</jstl:if>	
	</tr>
	<tr>
	<jstl:if test="${trainingModuleMaximumTime == -1.00}">
		<th scope="row">
			<acme:message code="developer.developer-dashboard.form.label.training-module-minimum-time"/>
		</th>
		<td>
			<acme:print value="${''}"/>
		</td>
		</jstl:if>	
	</tr>
</table>
<acme:return/>