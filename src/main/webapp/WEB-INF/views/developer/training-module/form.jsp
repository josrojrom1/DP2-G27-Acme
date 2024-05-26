<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="developer.training-module.form.label.code" path="code"/>
	<acme:input-moment code="developer.training-module.form.label.creationMoment" path="creationMoment"/>
	<jstl:if test="${trainningModuleUpDa == false}">
		<acme:input-moment code="developer.training-module.form.label.updateMoment" path="updateMoment"/>
	</jstl:if>	
	<acme:input-select code="developer.training-module.form.label.difficultyLevel" path="difficultyLevel" choices="${difficultyLevels}"/>
	<acme:input-textbox code="developer.training-module.form.label.details" path="details"/>
	<acme:input-textbox code="developer.training-module.form.label.totalTime" path="totalTime"/>
	<acme:input-select code="developer.training-module.form.label.project" path="project" choices="${projects}"/>
	<acme:input-url code="developer.training-module.form.label.link" path="link"/>
	
	<jstl:choose>	 
		<jstl:when test="${_command == 'show' && draftMode == false}">
			<acme:button code="developer.training-module.form.button.training-sessions" action="/developer/training-sessions/list?masterId=${id}"/>			
		</jstl:when>
		
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:button code="developer.training-module.form.button.training-sessions" action="/developer/training-sessions/list?masterId=${id}"/>
			<acme:submit code="developer.training-module.form.button.update" action="/developer/training-module/update"/>
			<acme:submit code="developer.training-module.form.button.delete" action="/developer/training-module/delete"/>
			<jstl:if test="${trainingSessionsDraft == false}">
				<acme:submit code="developer.training-module.form.button.publish" action="/developer/training-module/publish"/>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="developer.training-module.form.button.create" action="/developer/training-module/create"/>
		</jstl:when>
	</jstl:choose>

</acme:form>