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

		<acme:input-textbox code="manager.user-story.list.label.title" path="title"/>
		<acme:input-textbox code="manager.user-story.list.label.description" path="description"/>
		<acme:input-integer code="manager.user-story.list.label.estimatedCost" path="estimatedCost"/>
		<acme:input-textbox code="manager.user-story.list.label.acceptanceCriteria" path="acceptanceCriteria"/>
		<acme:input-select code="manager.user-story.list.label.priority" path="priority" choices="${priorities}"/>
		<acme:input-url code="manager.user-story.list.label.link" path="link"/>

	

	<jstl:choose>	 
			<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
				<acme:submit code="manager.user-story.delete" action="/manager/user-story/delete"/>	
				<acme:submit code="manager.user-story.publish" action="/manager/user-story/publish"/>	
				<acme:submit code="manager.user-story.update" action="/manager/user-story/update"/>			
			</jstl:when>
			<jstl:when test="${acme:anyOf(_command, 'create-project')}">
				<acme:submit code="manager.user-story.create" action="/manager/user-story/create-project?masterId=${masterId}"/>
			</jstl:when>
			<jstl:when test="${acme:anyOf(_command, 'create')}">
				<acme:submit code="manager.user-story.create" action="/manager/user-story/create"/>
			</jstl:when>
	</jstl:choose>	

</acme:form>

