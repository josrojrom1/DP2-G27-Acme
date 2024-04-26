<%--
- form.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-moment code="authenticated.notice.form.label.instantiationMoment" path="instantiationMoment"/>
	<acme:input-textbox code="authenticated.notice.form.label.title" path="title"/>
	<acme:input-textbox code="authenticated.notice.form.label.author" path="author"/>
	<acme:input-textbox code="authenticated.notice.form.label.message" path="message"/>
	<acme:input-email code="authenticated.notice.form.label.email" path="email"/>
	<acme:input-url code="authenticated.notice.form.label.link" path="link"/>
		
	<jstl:if test="${_command == 'create'}">
	<acme:input-checkbox code="authenticated.notice.form.label.accept" path="accept"/>
	<acme:submit code="authenticated.notice.form.button.create" action="/authenticated/notice/create"/>
	</jstl:if>
</acme:form>
