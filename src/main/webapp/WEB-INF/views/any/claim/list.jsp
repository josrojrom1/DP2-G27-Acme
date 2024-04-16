<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.principals.claims.list.label.heading" path="heading" width="10%"/>
	<acme:list-column code="any.principals.claims.list.label.instantiationMoment" path="instantiationMoment" width="10%"/>
	<acme:list-column code="any.principals.claims.list.label.description" path="description" width="10%"/>
</acme:list>