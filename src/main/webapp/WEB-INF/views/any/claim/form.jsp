<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="any.principals.claims.list.label.instantiationMoment" path="instantiationMoment"/>
	<acme:input-textbox code="any.principals.claims.list.label.heading" path="heading"/>	
	<acme:input-textbox code="any.principals.claims.list.label.description" path="description"/>	
	<acme:input-textbox code="any.principals.claims.list.label.department" path="department"/>
	<acme:input-textbox code="any.principals.claims.list.label.email" path="email"/>		
	<acme:input-textbox code="any.principals.claims.list.label.link" path="link"/>
</acme:form>