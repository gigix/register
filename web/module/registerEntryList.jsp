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
	<spring:message code="register.location.list.title"/>
</b>
<form method="get" class="box">

<input type="hidden" id="registerId" value='<c:out value="${param.registerId}"/>'/>

		<div id="locationList">
			<table>
					<tr>
						<td>
							<spring:message code="register.location.list.title" />
							<select name="locationId" id="locationId">
								<option value="">
									<spring:message code="register.location.all" />
								</option>
								<c:forEach var="location" items="${ locations }">
									<option value="${ location.locationId }">${ location.name }</option>
								</c:forEach>
							</select>
							
						</td>
					</tr>
				</table>
				</div>
	</form>
	

<%@ include file="/WEB-INF/template/footer.jsp"%>
<openmrs:htmlInclude file="/dwr/engine.js" />
<openmrs:htmlInclude file="/dwr/util.js" />
<openmrs:htmlInclude file="/dwr/interface/DWRRegisterService.js" />

<openmrs:htmlInclude file="/scripts/dojoConfig.js"></openmrs:htmlInclude>
<openmrs:htmlInclude file="/scripts/dojo/dojo.js"></openmrs:htmlInclude>
<script type="text/javascript">
	var locationObj = document.getElementById('locationId');
	getRegisterEntriesByLocation = function(){ 
	DWRRegisterService.getRegisterEntriesByLocation(document.getElementById('registerId').value, locationObj.options[locationObj.selectedIndex].value);
}
	
dojo.addOnLoad(function() {
    dojo.event.connect(locationObj,'onchange', getRegisterEntriesByLocation);
 });

</script>