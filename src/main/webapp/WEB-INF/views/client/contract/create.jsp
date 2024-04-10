<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="client.contract.form.label.code" path="code" placeholder="client.contract.form.placeholder.code"/>
	<acme:input-textbox code="client.contract.form.label.instantiationMoment" path="instantiationMoment" placeholder="client.contract.form.placeholder.instantiationMoment"/>
	<acme:input-textbox code="client.contract.form.label.provider" path="provider"/>
	<acme:input-textbox code="client.contract.form.label.customer" path="customer"/>
	<acme:input-textarea code="client.contract.form.label.goals" path="goals"/>
	<acme:input-textbox code="client.contract.form.label.budget" path="budget" placeholder="client.contract.form.placeholder.budget"/>
	
	<jstl:if test="${_command == 'create'}">
		<acme:submit code="client.contract.form.button.create" action="/client/contract/create"/>
	</jstl:if>
</acme:form>