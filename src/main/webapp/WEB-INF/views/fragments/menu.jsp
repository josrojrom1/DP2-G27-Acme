<%--
- menu.jsp
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
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<!-- MENU BAR -->
<acme:menu-bar code="master.menu.home">
	<!-- LEFT MENU -->
	<acme:menu-left>
		<!-- ANONYMOUS MENU -->
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-pepe" action="http://www.artistapirata.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-dani" action="https://www.kawasaki.es/es/products"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-guadalupe" action="https://www.amazon.es/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-benji" action="https://chat.openai.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-agus" action="https://loldle.net/"/>
		</acme:menu-option>

		<!-- ADMINISTRATOR MENU -->
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.configuration.show" action="/administrator/configuration/show"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/system/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/system/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/system/shut-down"/>
			<acme:menu-suboption code="master.menu.administrator.banner.list" action="/administrator/banner/list"/>
			<acme:menu-suboption code="master.menu.administrator.risk.list" action="/administrator/risk/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.create.objective" action="/administrator/objective/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.client" access="hasRole('Client')">
			<acme:menu-suboption code="master.menu.client.contract.list" action="/client/contract/list-mine"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.client.dashboard" action="/client/client-dashboard/show"/>
		</acme:menu-option>
		<!-- ANY PRINCIPAL MENU OPTIONS -->
		<acme:menu-option code="master.menu.any">
			<acme:menu-suboption code="master.menu.any.code-audit.list" action="/any/code-audit/list"/>
			<acme:menu-suboption code="master.menu.anonymous.all-claims" action="/any/claim/list"/>
			<acme:menu-suboption code="master.menu.any.training-module.list" action="/any/training-module/list"/>
			<acme:menu-suboption code="master.menu.any.sponsorship.list" action="/any/sponsorship/list"/>
			<acme:menu-suboption code="master.menu.any.contract.list" action="/any/contract/list"/>
		</acme:menu-option>
		<!-- AUDITOR SECTION -->
		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">			
			<acme:menu-suboption code="master.menu.auditor.my-code-audits" action="/auditor/code-audit/list-mine"/>	
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.auditor.dashboard" action="/auditor/auditor-dashboard/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.sponsor.sponsorship" action="/sponsor/sponsorship/list-mine"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.sponsor.dashboard" action="/sponsor/sponsor-dashboard/show"/>
		</acme:menu-option>
		<!-- DEVELOPER SECTION -->
		<acme:menu-option code="master.menu.developer" access="hasRole('Developer')">			
			<acme:menu-suboption code="master.menu.developer.my-training-modules" action="/developer/training-module/list-mine"/>
			<acme:menu-suboption code="master.menu.developer.dashboard" action="/developer/developer-dashboard/show"/>	
		</acme:menu-option>
		<!-- AUTHENTICATED PRINCIPALS SECTION -->
		<acme:menu-option code="master.menu.principal-authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.objective" action="/authenticated/objective/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.manager" access="hasRole('Manager')">
			<acme:menu-suboption code="master.menu.manager.project.list" action="/manager/project/list-mine"/>
			<acme:menu-suboption code="master.menu.manager.user-story.list" action="/manager/user-story/list-mine"/>
			<acme:menu-separator/>
			<acme:menu-suboption  code="master.menu.manager.dashboard" action="manager/manager-dashboard/show"/>
		</acme:menu-option>
	</acme:menu-left>
	<!-- RIGHT MENU -->
	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/anonymous/system/sign-in" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.notice.list" action="/authenticated/notice/list"/>
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create" access="!hasRole('Sponsor')"/>
			<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update" access="hasRole('Sponsor')"/>
			<!-- BECOME AUDITOR -->
			<acme:menu-suboption code="master.menu.user-account.become-auditor" action="/authenticated/auditor/create" access="!hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/update" access="hasRole('Auditor')"/>
			<!-- BECOME DEVELOPER -->
			<acme:menu-suboption code="master.menu.user-account.become-developer" action="/authenticated/developer/create" access="!hasRole('Developer')"/>
			<acme:menu-suboption code="master.menu.user-account.developer" action="/authenticated/developer/update" access="hasRole('Developer')"/>
			<!-- BECOME CLIENT -->
			<acme:menu-suboption code="master.menu.user-account.become-client" action="/authenticated/client/create" access="!hasRole('Client')"/>
			<acme:menu-suboption code="master.menu.user-account.client" action="/authenticated/client/update" access="hasRole('Client')"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.sign-out" action="/authenticated/system/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

