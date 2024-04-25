<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="any.claims.form.label.code" path="code"/>
	<acme:input-moment code="any.claims.form.label.instantiationMoment" path="instantiationMoment"/>
	<acme:input-textbox code="any.claims.form.label.heading" path="heading"/>	
	<acme:input-textbox code="any.claims.form.label.description" path="description"/>	
	<acme:input-textbox code="any.claims.form.label.department" path="department"/>
	<acme:input-textbox code="any.claims.form.label.email" path="email"/>		
	<acme:input-url code="any.claims.form.label.link" path="link"/>
	<jstl:choose>
	<jstl:when test="${_command == 'create'}">
			<acme:submit code="any.claims.form.button.create" action="/any/claim/create"/>
	</jstl:when>
	</jstl:choose>
</acme:form>