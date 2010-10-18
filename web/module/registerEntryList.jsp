<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="View Registers" otherwise="/login.htm" redirect="/module/register/manageRegister.list" />

<spring:message var="pageTitle" code="register.manage.page.title" scope="page"/>

<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="displayAddRegisterEntryPopup">
	<iframe id="displayAddRegisterEntryPopupIframe" width="100%"
		height="100%" marginWidth="0" marginHeight="0" frameBorder="0"
		scrolling="auto"></iframe>
</div>

<script type="text/javascript">
	$j(document).ready(function() {
		$j('#displayAddRegisterEntryPopup').dialog({
				title: 'dynamic',
				autoOpen: false,
				draggable: false,
				resizable: true,
				width: '95%',
				modal: true,
				open: function(a, b) {}
		});
	});

	function loadUrlIntoAddRegisterEntryPopup(title, urlToLoad) {
		$j("#displayAddRegisterEntryPopupIframe").attr("src", urlToLoad);
		$j('#displayAddRegisterEntryPopup')
			.dialog('option', 'title', title)
			.dialog('option', 'height', '450')
			.dialog('open');
	}
</script>

<c:url var="viewRegisterEntryUrl"
	value="/module/register/registerHtmlForm.form">
	<c:param name="registerId" value="${commandMap.map['register'].id}" />
	<c:param name="mode" value="Enter" />
	<c:param name="inPopup" value="true" />
</c:url>

<p>
	<h2>
		${commandMap.map['register'].name}
	</h2>
</p>
<br/>

<openmrs:hasPrivilege privilege="Manage Register Patients">

<openmrs:htmlInclude file="/scripts/dojoConfig.js"></openmrs:htmlInclude>
<openmrs:htmlInclude file="/scripts/dojo/dojo.js"></openmrs:htmlInclude>

<script type="text/javascript">
	dojo.require("dojo.widget.openmrs.PatientSearch");
	
	dojo.addOnLoad( function() {
		
		searchWidget = dojo.widget.manager.getWidgetById("pSearch");
		dojo.event.topic.subscribe("pSearch/select", 
			function(msg) {
				if (msg.objs[0].patientId){
					var newPatientURL = "${viewRegisterEntryUrl}" + "&patientId="+msg.objs[0].patientId;
					loadUrlIntoAddRegisterEntryPopup('<spring:message code="register.addPatientToRegister" />',newPatientURL);
				}
				else if (msg.objs[0].href)
					document.location = msg.objs[0].href;
			}
		);
		
		<c:if test="${empty hideAddNewPatient}">
			searchWidget.addPatientLink ='<a href="" onClick="loadUrlIntoAddRegisterEntryPopup(\'<spring:message code="register.addPatientToRegister" />\',\'${viewRegisterEntryUrl}\');return false;"><spring:message code="register.addPatient" /></a>';
		</c:if>
		searchWidget.inputNode.select();
		changeClassProperty("description", "display", "none");
	});
	
</script>

<div id="findPatient">
	<b class="boxHeader"><spring:message code="register.addPatientToRegister" />
	</b>
	<div class="box">
		<div dojoType="PatientSearch" widgetId="pSearch"
			showIncludeVoided="true"
			searchLabel="<spring:message code="Patient.searchBox" htmlEscape="true"/>"
			showVerboseListing="true"
			patientId='<request:parameter name="patientId"/>'
			searchPhrase='<request:parameter name="phrase"/>'
			<c:if test="${not empty hideAddNewPatient}">showAddPatientLink='false'</c:if>>
		</div>
	</div>
</div>
<br />
<br />
</openmrs:hasPrivilege>

<b class="boxHeader">
	<spring:message code="register.location.list.title"/>
</b>
<form method="get" class="box">
	<div id="locationList">
		<table>
			<tr>
				<td>
					<spring:message code="register.location.list.title" />
					<select name="locationId" id="locationId">
						<option value="">
							<spring:message code="register.location.all" />
						</option>
						<c:forEach var="location" items="${commandMap.map['locations'] }">
							<option value="${ location.locationId }">${ location.name }</option>
						</c:forEach>
					</select>

				</td>
			</tr>
		</table>
	</div>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>

