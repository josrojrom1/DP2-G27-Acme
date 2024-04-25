<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="any.training-module.form.label.code" path="code"/>
	<acme:input-moment code="any.training-module.form.label.creationMoment" path="creationMoment"/>
	<acme:input-moment code="any.training-module.form.label.updateMoment" path="updateMoment"/>	
	<acme:input-select code="any.training-module.form.label.difficultyLevel" path="difficultyLevel" choices="${difficultyLevels}"/>
	<acme:input-textbox code="any.training-module.form.label.details" path="details"/>
	<acme:input-textbox code="any.training-module.form.label.totalTime" path="totalTime"/>
	<acme:input-select code="any.training-module.form.label.project" path="project" choices="${projects}"/>
	<acme:input-url code="any.training-module.form.label.link" path="link"/>
</acme:form>