<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="developer.training-sessions.list.label.code" path="code" width="33%"/>
	<acme:list-column code="developer.training-sessions.list.label.draftMode" path="draftMode" width="33%"/>
	<acme:list-column code="developer.training-sessions.list.label.periodStart" path="periodStart" width="33%"/>
	<acme:list-column code="developer.training-sessions.list.label.periodFinish" path="periodFinish" width="33%"/>
</acme:list>

<acme:button test="${showCreate}" code="developer.training-sessions.list.button.create" action="/developer/training-sessions/create?masterId=${param.masterId}"/>