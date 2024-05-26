<%--
- list.jsp
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

	<jstl:choose>
		<jstl:when test="${_command == 'create'}">
			<acme:input-select code="manager.project-user-story.list.label.user-story" path="userStory" choices="${userStories}"/>
		</jstl:when>
		
		<jstl:when test="${acme:anyOf(_command, 'show|delete')}">
			<acme:input-textbox code="manager.project-user-story.list.label.project" path="code" readonly="${true}"/>
			<acme:input-textbox code="manager.project-user-story.list.label.user-story" path="title" readonly="${true}"/>
			
		</jstl:when>
	</jstl:choose>

	<jstl:choose>
			<jstl:when test="${_command == 'show' && draftMode}">
				<acme:submit code="manager.project-user-story.delete" action="/manager/project-user-story/delete"/>
			</jstl:when>
			<jstl:when test="${_command == 'create'}">
				<acme:submit code="manager.project-user-story.create" action="/manager/project-user-story/create?masterId=${param.masterId}"/>
			</jstl:when>
	</jstl:choose>	
	
</acme:form>