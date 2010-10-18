<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="View Registers" otherwise="/login.htm"	redirect="/module/register/manageRegister.list" />

<spring:message var="pageTitle" code="register.manage.page.title" scope="page"/>

<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="displayAddRegisterEntryPopup">
	<iframe id="displayAddRegisterEntryPopupIframe" width="100%" height="100%" marginWidth="0" marginHeight="0" frameBorder="0" scrolling="auto"></iframe>
</div>

<script type="text/javascript">
	$j(document).ready(function() {
		$j('#displayAddRegisterEntryPopup').dialog({
				title: 'dynamic',
				autoOpen: false,
				draggable: false,
				resizable: false,
				width: '95%',
				modal: true,
				open: function(a, b) {}
		});
	});

	function loadUrlIntoAddRegisterEntryPopup(title, urlToLoad) {
		$j("#displayAddRegisterEntryPopupIframe").attr("src", urlToLoad);
		$j('#displayAddRegisterEntryPopup')
			.dialog('option', 'title', title)
			.dialog('option', 'height', '350')
			.dialog('open');
	}
</script>

<p>
	<h2>
		<spring:message code="register.management" />
	</h2>
</p>

<br />
<br />
<c:url var="viewRegisterEntryUrl" value="/module/register/registerHtmlForm.form">
	<c:param name="registerId" value="${commandMap.map['registerId']}"/>
	<c:param name="mode" value="Enter"/>
	<c:param name="inPopup" value="true"/>
</c:url>

<a href="" onClick="loadUrlIntoAddRegisterEntryPopup('<spring:message code="register.addPatientToRegister" />','${viewRegisterEntryUrl}');return false;"><spring:message code="register.addPatient" /></a>
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

