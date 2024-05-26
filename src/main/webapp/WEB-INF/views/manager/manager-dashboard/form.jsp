<%--
- list.jsp
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
	<acme:message code="manager.manager-dasboard.form.title.total-user-stories"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.total-Must"/>
		</th>
		<td>
			<acme:print value = "${totalMustUserStories}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.total-Should"/>
		</th>
		<td>
			<acme:print value = "${totalShouldUserStories}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.total-Could"/>
		</th>
		<td>
			<acme:print value = "${totalCouldUserStories}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.total-Wont"/>
		</th>
		<td>
			<acme:print value = "${totalWontUserStories}"/>
		</td>
	</tr>

</table>

<h2>
	<acme:message code="manager.manager-dasboard.form.title.user-stories-cost"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.user-stories-average"/>
		</th>
		<td>
			<jstl:if test="${averageUserStoryCost != 0}">
				<acme:print value = "${averageUserStoryCost}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.user-stories-deviation"/>
		</th>
		<td>
			<jstl:if test="${deviationUserStoryCost >= 0}">
				<acme:print value = "${deviationUserStoryCost}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.user-stories-minimum"/>
		</th>
		<td>
			<jstl:if test="${averageUserStoryCost != 0}">
				<acme:print value = "${minimumUserStoryCost}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.user-stories-maximum"/>
		</th>
		<td>
			<jstl:if test="${maximumUserStoryCost != 0}">
				<acme:print value = "${maximumUserStoryCost}"/>
			</jstl:if>
		</td>
	</tr>

</table>

<h2>
	<acme:message code="manager.manager-dasboard.form.title.project-USD-cost"/>
</h2>
<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-average"/>
		</th>
		<td>
			<jstl:if test="${averageProjectCostUSD >= 0}">
				<acme:print value = "${averageProjectCostUSD}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-deviation"/>
		</th>
		<td>
			<jstl:if test="${deviationOfProjectCostUSD >= 0}">
				<acme:print value = "${deviationOfProjectCostUSD}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-minimum"/>
		</th>
		<td>
			<jstl:if test="${minimumProjectCostUSD >= 0}">
				<acme:print value = "${minimumProjectCostUSD}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-maximum"/>
		</th>
		<td>
			<jstl:if test="${maximumProjectCostUSD >= 0}">
				<acme:print value = "${maximumProjectCostUSD}"/>
			</jstl:if>
		</td>
	</tr>

</table>

<h2>
	<acme:message code="manager.manager-dasboard.form.title.project-EUR-cost"/>
</h2>
<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-average"/>
		</th>
		<td>
			<jstl:if test="${averageProjectCostEUR >= 0}">
				<acme:print value = "${averageProjectCostEUR}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-deviation"/>
		</th>
		<td>
			<jstl:if test="${deviationOfProjectCostEUR >= 0}">
				<acme:print value = "${deviationOfProjectCostEUR}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-minimum"/>
		</th>
		<td>
			<jstl:if test="${minimumProjectCostEUR >= 0}">
				<acme:print value = "${minimumProjectCostEUR}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-maximum"/>
		</th>
		<td>
			<jstl:if test="${maximumProjectCostEUR >= 0}">
				<acme:print value = "${maximumProjectCostEUR}"/>
			</jstl:if>
		</td>
	</tr>

</table>

<h2>
	<acme:message code="manager.manager-dasboard.form.title.project-GBP-cost"/>
</h2>
<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-average"/>
		</th>
		<td>
			<jstl:if test="${averageProjectCostGBP >= 0}">
				<acme:print value = "${averageProjectCostGBP}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-deviation"/>
		</th>
		<td>
			<jstl:if test="${deviationOfProjectCostGBP >= 0}">
				<acme:print value = "${deviationOfProjectCostGBP}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-minimum"/>
		</th>
		<td>
			<jstl:if test="${minimumProjectCostGBP >= 0}">
				<acme:print value = "${minimumProjectCostGBP}"/>
			</jstl:if>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="manager.manager-dashboard.form.label.project-maximum"/>
		</th>
		<td>
			<jstl:if test="${maximumProjectCostGBP >= 0}">
				<acme:print value = "${maximumProjectCostGBP}"/>
			</jstl:if>
		</td>
	</tr>

</table>

