<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.objective.list.label.tittle" path="tittle"/>
	<acme:input-textbox code="authenticated.objective.list.label.instantiationMoment" path="instantiationMoment"/>	
	<acme:input-textbox code="authenticated.objective.list.label.description" path="description"/>	
	<acme:input-textbox code="authenticated.objective.list.label.priority" path="priority"/>
	<acme:input-textbox code="authenticated.objective.list.label.executionPeriodStart" path="executionPeriodStart"/>	
	<acme:input-textbox code="authenticated.objective.list.label.executionPeriodFinish" path="executionPeriodFinish"/>		
	<acme:input-textbox code="authenticated.objective.list.label.link" path="link"/>
</acme:form>