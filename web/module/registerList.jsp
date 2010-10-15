<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="View Registers" otherwise="/login.htm"	redirect="/module/register/manageRegister.list" />

<spring:message var="pageTitle" code="register.manage.page.title" scope="page"/>

<%@ include file="/WEB-INF/template/header.jsp"%>

<p>
	<h2>
		<spring:message code="register.management" />
	</h2>
</p>


<br />
<br />

<b class="boxHeader">
	<spring:message code="register.management"/>
</b>
<form class="box">
<table id="registerTable" cellspacing="5">
		<tr>
			<th> <spring:message code="general.name" /> </th>
			<th> <spring:message code="general.description" /> </th>
			<th> <spring:message code="register.type" /> </th>			
		</tr>
		<c:forEach var="register" items="${registers}">
			<tr>
				<td valign="top">
					<a href="registerEntry.list?registerId=${register.registerId}">${register.name}</a> 
				</td>
				<td valign="top">${register.description}</td>
				<td valign="top">${register.registerType.name}</td>
			</tr>
		</c:forEach>
		
		
		
	</table> </form>
<%@ include file="/WEB-INF/template/footer.jsp"%>

