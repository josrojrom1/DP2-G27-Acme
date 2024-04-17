<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="developer.training-module.form.label.code" path="code"/>
	<acme:input-moment code="developer.training-module.form.label.creationMoment" path="creationMoment"/>	
	<acme:input-select code="developer.training-module.form.label.difficultyLevel" path="difficultyLevel" choices="${difficultyLevel}"/>
	<acme:input-textbox code="developer.training-module.form.label.details" path="details"/>
	<acme:input-textbox code="developer.training-module.form.label.totalTime" path="totalTime"/>
	<acme:input-select code="developer.training-module.form.label.project" path="project" choices="${projects}"/>
	<acme:input-url code="developer.training-module.form.label.link" path="link"/>
	
	<jstl:choose>	 
		<jstl:when test="${_command == 'show'}">
			<acme:button code="developer.training-module.form.button.training-sessions" action="/developer/training-sessions/list?masterId=${id}"/>			
		</jstl:when>
	</jstl:choose>

</acme:form>