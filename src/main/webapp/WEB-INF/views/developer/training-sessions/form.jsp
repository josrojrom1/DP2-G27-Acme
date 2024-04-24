<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="developer.training-sessions.form.label.code" path="code"/>
	<acme:input-moment code="developer.training-sessions.form.label.periodStart" path="periodStart"/>
	<acme:input-moment code="developer.training-sessions.form.label.periodFinish" path="periodFinish"/>
	<acme:input-textbox code="developer.training-sessions.form.label.location" path="location"/>	
	<acme:input-textbox code="developer.training-sessions.form.label.instructor" path="instructor"/>
	<acme:input-textbox code="developer.training-sessions.form.label.contactEmail" path="contactEmail"/>
	<acme:input-url code="developer.training-sessions.form.label.link" path="link"/>

	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:submit code="developer.training-sessions.form.button.update" action="/developer/training-sessions/update"/>
			<acme:submit code="developer.training-sessions.form.button.delete" action="/developer/training-sessions/delete"/>
			<acme:submit code="developer.training-sessions.form.button.publish" action="/developer/training-sessions/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="developer.training-sessions.form.button.create" action="/developer/training-sessions/create?masterId=${param.masterId}"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>