<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.objective.form.label.title" path="title"/>
	<acme:input-textbox code="authenticated.objective.form.label.instantiationMoment" path="instantiationMoment"/>	
	<acme:input-textbox code="authenticated.objective.form.label.description" path="description"/>	
	<acme:input-textbox code="authenticated.objective.form.label.priority" path="priority"/>
	<acme:input-textbox code="authenticated.objective.form.label.executionPeriodStart" path="executionPeriodStart"/>	
	<acme:input-textbox code="authenticated.objective.form.label.executionPeriodFinish" path="executionPeriodFinish"/>		
	<acme:input-textbox code="authenticated.objective.form.label.link" path="link"/>
</acme:form>