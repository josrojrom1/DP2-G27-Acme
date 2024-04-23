<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="client.client-dasboard.form.title.progress-log"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.total-number-pl-completeness-25"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProgLogsWithCompletenessBelow25}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.total-number-pl-completeness-25-50"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProgLogsWithCompletenessBetween25and50}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.total-number-pl-completeness-50-75"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProgLogsWithCompletenessBetween50and75}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.total-number-pl-completeness-75"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProgLogsWithCompletenessAbove75}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="client.client-dasboard.form.title.contract"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-average"/>
		</th>
		<td>
			<acme:print value="${contractsBudgetAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-deviation"/>
		</th>
		<td>
			<acme:print value="${contractsBudgetDeviation}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-minimum"/>
		</th>
		<td>
			<acme:print value="${contractsBudgetMinimum}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-maximum"/>
		</th>
		<td>
			<acme:print value="${contractsBudgetMaximum}"/>
		</td>
	</tr>
</table>

<acme:return/>
