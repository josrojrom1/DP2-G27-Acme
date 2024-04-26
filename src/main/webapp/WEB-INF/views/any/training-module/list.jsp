<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.training-module.list.label.code" path="code" width="33%"/>
	<acme:list-column code="any.training-module.list.label.difficultyLevel" path="difficultyLevel" width="33%"/>
	<acme:list-column code="any.training-module.list.label.draftMode" path="draftMode" width="33%"/>	
</acme:list>