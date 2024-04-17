<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="client.contract.list.label.code" path="code" width="10%"/>
	<acme:list-column code="client.contract.list.label.instantiationMoment" path="instantiationMoment" width="10%"/>
	<acme:list-column code="client.contract.list.label.provider" path="provider" width="10%"/>
	<acme:list-column code="client.contract.list.label.project" path="project.code" width="10%"/>
</acme:list>

<jstl:if test="${_command == 'list-mine' }">
	<acme:button code="client.contract.list.button.create" action="/client/contract/create"/>
</jstl:if>