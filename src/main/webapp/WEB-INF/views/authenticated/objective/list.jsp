<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="authenticated.objective.list.label.heading" path="tittle" width="10%"/>
	<acme:list-column code="authenticated.objective.list.label.instantiationMoment" path="instantiationMoment" width="10%"/>
	<acme:list-column code="authenticated.objective.list.label.critical" path="critical" width="10%"/>
</acme:list>