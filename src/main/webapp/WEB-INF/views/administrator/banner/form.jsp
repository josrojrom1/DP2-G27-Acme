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
	<acme:input-moment code="administrator.banner.list.label.instantiationOrUpdateMoment" path="instantiationOrUpdateMoment"/>
	<acme:input-moment code="administrator.banner.list.label.displayStartDate" path="displayStartDate"/>
	<acme:input-moment code="administrator.banner.list.label.displayEndDate" path="displayEndDate"/>
	<acme:input-textbox code="administrator.banner.list.label.picture" path="picture"/>	
	<acme:input-textbox code="administrator.banner.list.label.slogan" path="slogan"/>	
	<acme:input-url code="administrator.banner.list.label.webDocument" path="webDocument"/>	


	<jstl:choose>	 
			<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
				<acme:submit code="administrator.banner.delete" action="/administrator/banner/delete"/>	
				<acme:submit code="administrator.banner.update" action="/administrator/banner/update"/>			
			</jstl:when>
			<jstl:when test="${_command == 'create'}">
				<acme:submit code="administrator.banner.create" action="/administrator/banner/create"/>
			</jstl:when>
	</jstl:choose>	

</acme:form>

