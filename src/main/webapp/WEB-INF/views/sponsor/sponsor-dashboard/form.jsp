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
	<acme:message code="sponsor.sponsor-dasboard.form.title.invoice.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.total-number-tax-invoices"/>
		</th>
		<td>
			<acme:print value="${totalNumOfInvoicesWithTaxLessOrEqualToTwentyOne}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="sponsor.sponsor-dasboard.form.title.sponsorship.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.total-number-link-sponsorships"/>
		</th>
		<td>
			<acme:print value="${totalNumOfSponsorshipsWithLink}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="sponsor.sponsor-dasboard.form.title.sponsorships"/>
</h2>

<table class="table table-sm">
	<jstl:if test="${sponsorshipAmountAverageEUR != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.averageEUR"/>
		</th>
		<td>
		<acme:print value="${sponsorshipAmountAverageEUR}"/>	
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${sponsorshipAmountAverageUSD != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.averageUSD"/>
		</th>
		<td>
		<acme:print value="${sponsorshipAmountAverageUSD}"/>	
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${sponsorshipAmountAverageGBP != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.averageGBP"/>
		</th>
		<td>
		<acme:print value="${sponsorshipAmountAverageGBP}"/>
		</td>
	</tr>
	</jstl:if>	
	<jstl:if test="${sponsorshipAmountDeviationEUR >= 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.deviationEUR"/>
		</th>
		<td>
		<acme:print value="${sponsorshipAmountDeviationEUR}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${sponsorshipAmountDeviationUSD >= 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.deviationUSD"/>
		</th>
		<td>
		<acme:print value="${sponsorshipAmountDeviationUSD}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${sponsorshipAmountDeviationGBP >= 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.deviationGBP"/>
		</th>
		<td>
		<acme:print value="${sponsorshipAmountDeviationGBP}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${minimumSponsorshipAmountEUR != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.minimumEUR"/>
		</th>
		<td>
		<acme:print value="${minimumSponsorshipAmountEUR}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${minimumSponsorshipAmountUSD != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.minimumUSD"/>
		</th>
		<td>
		<acme:print value="${minimumSponsorshipAmountUSD}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${minimumSponsorshipAmountGBP != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.minimumGBP"/>
		</th>
		<td>
		<acme:print value="${minimumSponsorshipAmountGBP}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${maximumSponsorshipAmountEUR != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.maximumEUR"/>
		</th>
		<td>
		<acme:print value="${maximumSponsorshipAmountEUR}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${maximumSponsorshipAmountUSD != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.maximumUSD"/>
		</th>
		<td>
		<acme:print value="${maximumSponsorshipAmountUSD}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${maximumSponsorshipAmountGBP != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.maximumGBP"/>
		</th>
		<td>
		<acme:print value="${maximumSponsorshipAmountGBP}"/>
		</td>
	</tr>
	</jstl:if>
</table>

<h2>
	<acme:message code="sponsor.sponsor-dasboard.form.title.invoices"/>
</h2>

<table class="table table-sm">
	<jstl:if test="${invoiceQuantityAverageEUR != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.averageEUR"/>
		</th>
		<td>
		<acme:print value="${invoiceQuantityAverageEUR}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${invoiceQuantityAverageUSD != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.averageUSD"/>
		</th>
		<td>
		<acme:print value="${invoiceQuantityAverageUSD}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${invoiceQuantityAverageGBP != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.averageGBP"/>
		</th>
		<td>
		<acme:print value="${invoiceQuantityAverageGBP}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${invoiceQuantityDeviationEUR >= 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.deviationEUR"/>
		</th>
		<td>
		<acme:print value="${invoiceQuantityDeviationEUR}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${invoiceQuantityDeviationUSD >= 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.deviationUSD"/>
		</th>
		<td>
		<acme:print value="${invoiceQuantityDeviationUSD}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${invoiceQuantityDeviationGBP >= 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.deviationGBP"/>
		</th>
		<td>
		<acme:print value="${invoiceQuantityDeviationGBP}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${minimumInvoiceQuantityEUR != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.minimumEUR"/>
		</th>
		<td>
		<acme:print value="${minimumInvoiceQuantityEUR}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${minimumInvoiceQuantityUSD != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.minimumUSD"/>
		</th>
		<td>
		<acme:print value="${minimumInvoiceQuantityUSD}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${minimumInvoiceQuantityGBP != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.minimumGBP"/>
		</th>
		<td>
		<acme:print value="${minimumInvoiceQuantityGBP}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${maximumInvoiceQuantityEUR != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.maximumEUR"/>
		</th>
		<td>
		<acme:print value="${maximumInvoiceQuantityEUR}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${maximumInvoiceQuantityUSD != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.maximumUSD"/>
		</th>
		<td>
		<acme:print value="${maximumInvoiceQuantityUSD}"/>
		</td>
	</tr>
	</jstl:if>
	<jstl:if test="${maximumInvoiceQuantityGBP != 0}">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.maximumGBP"/>
		</th>
		<td>
		<acme:print value="${maximumInvoiceQuantityGBP}"/>
		</td>
	</tr>
	</jstl:if>
</table>

<acme:return/>