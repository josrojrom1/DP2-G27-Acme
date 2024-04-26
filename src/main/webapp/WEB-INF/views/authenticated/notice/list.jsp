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

<acme:list>
	<acme:list-column code="authenticated.notice.list.label.title"
		path="title" width="20%"></acme:list-column>
	<acme:list-column
		code="authenticated.notice.list.label.instantiationMoment"
		path="instantiationMoment" width="30%"></acme:list-column>
	<acme:list-column code="authenticated.notice.list.label.author"
		path="author" width="50%"></acme:list-column>
</acme:list>
<acme:button code="authenticated.notice.list.button.create"
		action="/authenticated/notice/create" />

