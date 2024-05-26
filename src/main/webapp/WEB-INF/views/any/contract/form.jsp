<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="any.contract.form.label.code" path="code" />
	<acme:input-moment code="any.contract.form.label.instantiationMoment" path="instantiationMoment" readonly="true"/>	
	<acme:input-textbox code="any.contract.form.label.provider" path="provider"/>
	<acme:input-textbox code="any.contract.form.label.customer" path="customer"/>
	<acme:input-textarea code="any.contract.form.label.goals" path="goals"/>
	<acme:input-money code="any.contract.form.label.budget" path="budget"/>
	<acme:input-textbox code="any.contract.form.label.project" path="project.code" readonly="true"/>	
		
	
	<acme:button code="any.contract.form.button.progress-log" action="/any/progress-log/list?masterId=${id}"/>

</acme:form>
