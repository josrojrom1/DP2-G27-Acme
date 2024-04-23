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
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.average"/>
		</th>
		<td>
			<acme:print value="${sponsorshipAmountAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.minimum"/>
		</th>
		<td>
			<acme:print value="${minimumSponsorshipAmount}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.sponsorship.maximum"/>
		</th>
		<td>
			<acme:print value="${maximumSponsorshipAmount}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="sponsor.sponsor-dasboard.form.title.invoices"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.average"/>
		</th>
		<td>
			<acme:print value="${invoiceQuantityAverage}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.minimum"/>
		</th>
		<td>
			<acme:print value="${minimumInvoiceQuantity}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="sponsor.sponsor-dasboard.form.label.invoice.maximum"/>
		</th>
		<td>
			<acme:print value="${maximumInvoiceQuantity}"/>
		</td>
	</tr>
</table>

<acme:return/>