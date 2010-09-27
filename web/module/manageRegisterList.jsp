<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="View Registers" otherwise="/login.htm"	redirect="/module/register/manageRegister.list" />

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="localHeader.jsp"%>

<p>
	<h2>
		<spring:message code="register.title" />
	</h2>
</p>

<openmrs:hasPrivilege privilege="Manage Registers">
	<a href="createRegister.form"><spring:message code="register.create.link" />
	</a>
</openmrs:hasPrivilege>

<%@ include file="/WEB-INF/template/footer.jsp"%>

