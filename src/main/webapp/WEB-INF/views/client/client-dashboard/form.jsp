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
			<acme:message code="client.client-dasboard.form.label.contract.budget-average-USD"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetAverageUSD != 0}">
				<acme:print value="${contractsBudgetAverageUSD}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-deviation-USD"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetDeviationUSD != 0}">
				<acme:print value="${contractsBudgetDeviationUSD}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-minimum-USD"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetMinimumUSD != 0}">
				<acme:print value="${contractsBudgetMinimumUSD}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-maximum-USD"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetMaximumUSD != 0}">
				<acme:print value="${contractsBudgetMaximumUSD}"/>
			</jstl:if>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-average-EUR"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetAverageEUR != 0}">
				<acme:print value="${contractsBudgetAverageEUR}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-deviation-EUR"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetDeviationEUR != 0}">
				<acme:print value="${contractsBudgetDeviationEUR}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-minimum-EUR"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetMinimumEUR != 0}">
				<acme:print value="${contractsBudgetMinimumEUR}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-maximum-EUR"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetMaximumEUR != 0}">
				<acme:print value="${contractsBudgetMaximumEUR}"/>
			</jstl:if>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-average-GBP"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetAverageGBP != 0}">
				<acme:print value="${contractsBudgetAverageGBP}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-deviation-GBP"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetDeviationGBP != 0}">
				<acme:print value="${contractsBudgetDeviationGBP}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-minimum-GBP"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetMinimumGBP != 0}">
				<acme:print value="${contractsBudgetMinimumGBP}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dasboard.form.label.contract.budget-maximum-GBP"/>
		</th>
		<td>
			<jstl:if test="${contractsBudgetMaximumGBP != 0}">
				<acme:print value="${contractsBudgetMaximumGBP}"/>
			</jstl:if>
		</td>
	</tr>
</table>

<acme:return/>
