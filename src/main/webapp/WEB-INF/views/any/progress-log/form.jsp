<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="any.progress-log.form.label.recordId" path="recordId"/>
	<acme:input-double code="any.progress-log.form.label.completeness" path="completeness"/>
	<acme:input-textbox code="any.progress-log.form.label.comment" path="comment"/>
	<acme:input-moment code="any.progress-log.form.label.registrationMoment" path="registrationMoment" readonly="true"/>	
	<acme:input-textbox code="any.progress-log.form.label.responsiblePerson" path="responsiblePerson"/>
	<acme:input-textbox code="any.progress-log.form.label.contract" path="contract.code" readonly="true"/>	
</acme:form>
