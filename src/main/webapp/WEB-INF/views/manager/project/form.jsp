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

		<acme:input-textbox code="manager.project.list.label.code" path="code" readonly="${draftMode == false}"/>
		<acme:input-textbox code="manager.project.list.label.title" path="title" readonly="${draftMode == false}"/>
		<acme:input-textbox code="manager.project.list.label.description" path="description" readonly="${draftMode == false}"/>
		<acme:input-checkbox code="manager.project.list.label.indication" path="indication" readonly="${draftMode == false}"/>
		<acme:input-integer code="manager.project.list.label.cost" path="cost" readonly="${draftMode == false}"/>
		<acme:input-url code="manager.project.list.label.link" path="link" readonly="${draftMode == false}"/>

	<jstl:choose>	 
			<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish')}">
				<acme:button code="manager.user-story.list-project" action="/manager/user-story/list-project?masterId=${id}"/>
				<jstl:if  test = "${draftMode == true}">
					<acme:submit code="manager.project.delete" action="/manager/project/delete"/>	
					<acme:submit code="manager.project.update" action="/manager/project/update"/>
					<acme:submit code="manager.project.publish" action="/manager/project/publish"/>	
				</jstl:if>		
			</jstl:when>
			<jstl:when test="${_command == 'create'}">
				<acme:submit code="manager.project.create" action="/manager/project/create"/>
			</jstl:when>
	</jstl:choose>	

</acme:form>

