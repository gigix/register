<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="View Registers" otherwise="/login.htm" redirect="/module/register/manageRegister.list" />

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
				draggable: true,
				resizable: true,
				closeOnEscape: true,
				width: '95%',
				modal: true,
				open: function(a, b) {},
				close: function() { reloadView(); }
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
<c:if test="${not commandMap.map['register'].retired}">
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
					searchWidget.addPatientLink ='<a href="" onClick="loadUrlIntoAddRegisterEntryPopup(\'<spring:message code="register.addPatientToRegister" />\',\'${viewRegisterEntryUrl}\');return false;"><spring:message code="register.addPatientToRegister" /></a>';
				</c:if>
				searchWidget.inputNode.select();
				changeClassProperty("description", "display", "none");
				reloadView();
			});
			
		</script>
		
		<div id="findPatient">
			<b class="boxHeader"><spring:message code="register.findPatient" />
			</b>
			<div class="box">
				<div dojoType="PatientSearch" widgetId="pSearch"
					showIncludeVoided="true"
					searchLabel="<spring:message code="Patient.searchBox" htmlEscape="true"/>"
					showVerboseListing="true"
					patientId='<request:parameter name="patientId"/>'
					searchPhrase='<request:parameter name="phrase"/>'
					showAddPatientLink='false'
				</div>
			</div>
		</div>
		<br />
		<a href="" onClick="loadUrlIntoAddRegisterEntryPopup('<spring:message code="register.addPatientToRegister" />','${viewRegisterEntryUrl}');return false;"><spring:message code="register.addPatientToRegister" /></a>
		<br />
		<br/>
	</openmrs:hasPrivilege>
</c:if>
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
						<option value="-1">
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
	
	<input type="hidden" id="registerId" value='<c:out value="${param.registerId}"/>'/>
	<br style="clear:both;" />
	<table width="100%">
		<tbody id="searchInfoBar" >			
			<tr>
				<td align="left">
			        	Show <select id="noOfItems"   onChange="loadDataForPagination();"><option value="2">20</option><option value="5">50</option><option value="7">100</option></select> entries
			        </td>
			        <td align="right">
			        	<div class="locationBoxNav"></div>
			        </td>
		        </tr>
	        </tbody>
       </table>	
       
        <h3></h3>
        	
        	<table cellspacing="0" cellpadding="2" style="width: 100%;" class="openmrsSearchTable">
        	<thead>
        	
        		<tr>
        		<th>Encounter Id</th>
        		<th>Encounter Type</th>
        		<th>Form Name</th>
        		<th>Person Name</th>
        		<th>Provider Name</th>
        		<th>Encounter Date</th>
        		</tr></thead>
        		<tbody id="Searchresult">
        		</tbody>
        	</table>
	<div id="searchNav" align="center" style="padding: 9px;"></div>
	
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
<openmrs:htmlInclude file="/dwr/engine.js" />
<openmrs:htmlInclude file="/dwr/util.js" />
<openmrs:htmlInclude file="/dwr/interface/DWRRegisterService.js" />

<openmrs:htmlInclude file="/moduleResources/register/jquery.pagination.js" />

<script type="text/javascript">
var registerEntries = {};

$j('#locationId').change(function() {
		reloadView();
});

function reloadView(){
	DWRRegisterService.getRegisterEntriesByLocation($j('#registerId').val(), $j('#locationId').val(),fillDataInTable);
}
fillDataInTable = function(data){
	registerEntries = data;
	loadDataForPagination();
}

	function pageSelectCallback(page_index, jq){
	
				var items_per_page = $j('#noOfItems').val();
		       // Get number of elements per pagionation page from form
                var requiredDataFromJson = ["encounterId","encounterType","formName","personName","providerName","encounterDateString"];
                var max_elem = Math.min((page_index+1) * items_per_page, registerEntries.length);
                var newcontent = '';
                
                // Iterate through a selection of the content and build an HTML string
                var rowStyle = "oddRow" ;
                var startingIndex=(page_index*items_per_page);
                if(max_elem > 0){
	                for(var i = startingIndex; i<max_elem; i++)
	                {
	                	newcontent += '<tr class="'+rowStyle+'">' ;
						$j.each(requiredDataFromJson, function(key,value){
						    newcontent += '<td>' + registerEntries[i][value] + '</td>';
						    
						})                	
	                	newcontent += '</tr>' ;
	                	if(rowStyle == 'oddRow'){
	                    	rowStyle = 'evenRow';
	                	}else{
	                		rowStyle = 'oddRow';
	                	}
	                }
                }
                else{
                	newcontent += '<tr class="'+rowStyle+'">' + '<td> No records found. </td> </tr>';;
                }
                
                // Replace old content with new content
                $j('#Searchresult').html(newcontent);
                if(registerEntries && registerEntries.length >0){
                	$j('.locationBoxNav').html("Viewing <b>" + (startingIndex+1)+ "-" + max_elem + "</b> of <b>" + registerEntries.length + "</b>");
                }else{
                	$j('.locationBoxNav').html("");
                }
                // Prevent click event propagation
                return false;
            }
            
            var loadDataForPagination = function(){
				// Create pagination element with options from form
				var optInit =  {callback: pageSelectCallback, num_display_entries:0,items_per_page:$j('#noOfItems').val(),prev_text:'Previous Results',next_text:'Next Results',prev_show_always:true,next_show_always:true};				
                $j("#searchNav").pagination(registerEntries.length, optInit);
                
            }
            //When document has loaded, initialize pagination and form 
            $j(document).ready(loadDataForPagination);            
            

</script>
