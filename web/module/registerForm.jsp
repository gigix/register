<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage Registers" otherwise="/login.htm"
	redirect="/module/register/creatRegister.form" />

<spring:message var="pageTitle" code="register.page.title" scope="page" />

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="localHeader.jsp"%>

<script type="text/javascript">
	function retiredClicked(input) {
		var reason = document.getElementById("retiredReason");
		var retiredBy = document.getElementById("retiredBy");
		if (input) {
		if (input.checked) {
			reason.style.display = "";
			if (retiredBy)
				retiredBy.style.display = "";
		}
		else {
			reason.style.display = "none";
			if (retiredBy)
				retiredBy.style.display = "none";
		}
		}
	}
</script>
<p>
	<h2>
		<spring:message code="register.new.heading.title" />
	</h2>
</p>

<b class="boxHeader"><spring:message code="register.summary" /> </b>
<div class="box">
		<form method="post" id="createForm" name="createForm">
			<table cellpadding="3" cellspacing="0">
				<tr>
					<th>
						<spring:message code="register.name" />
					</th>
					<td>
						<spring:bind path="commandMap.map['register'].name">
							<input type="text" name="${status.expression}"
								id="${status.expression}" value="${status.value}" />
							<c:if test="${status.errorMessage != ''}">
								<span class="error">${status.errorMessage}</span>
							</c:if>
						</spring:bind>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="register.description" />
					</th>
					<td>
						<spring:bind path="commandMap.map['register'].description">
							<input type="text" name="${status.expression}"
								id="${status.expression}" value="${status.value}" />
							<c:if test="${status.errorMessage != ''}">
								<span class="error">${status.errorMessage}</span>
							</c:if>
						</spring:bind>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="register.type" />
					</th>
					<td>
						<spring:bind path="commandMap.map['register'].registerType">
							<select name="${status.expression}" id="${status.expression}">
								<option value="">
									Select Register Type...
								</option>
								<c:forEach var="regType"
									items="${commandMap.map['registerTypes'] }">
									<option value="${regType.registerTypeId}"
										<c:if test="${regType.registerTypeId == status.value}"> selected</c:if>>
										${regType.name }
									</option>
								</c:forEach>
							</select>
							<c:if test="${status.errorMessage != ''}">
								<span class="error">${status.errorMessage}</span>
							</c:if>
						</spring:bind>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="register.htmlform" />
					</th>
					<td>
						<spring:bind path="commandMap.map['register'].htmlForm">
							<select name="${status.expression}" id="${status.expression}">
								<option value="">
									Select Html Form...
								</option>
								<c:forEach var="hf" items="${commandMap.map['htmlForms'] }">
									<option value="${hf.id}"
										<c:if test="${hf.id == status.value}"> selected</c:if>>
										${ hf.name}
									</option>
								</c:forEach>
							</select>
							<c:if test="${status.errorMessage != ''}">
								<span class="error">${status.errorMessage}</span>
							</c:if>
						</spring:bind>
					</td>
				</tr>
				<c:if test="${commandMap.map['register'].registerId != null}">
					<tr>
						<th>
							<spring:message code="general.createdBy" />
						</th>
						<td>
							<a href="#View User"
								onclick="return gotoUser(null, '${commandMap.map['register'].creator.userId}')">${commandMap.map['register'].creator.personName}</a>
							-
							<openmrs:formatDate	date="${commandMap.map['register'].dateCreated}" type="medium" />
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="general.retired" />
						</th>
						<td>
							<spring:bind path="commandMap.map['register'].retired">
								<input type="hidden" name="_${status.expression}" />
								<input type="checkbox" name="${status.expression}" id="retired"	onClick=retiredClicked(this); <c:if test="${commandMap.map['register'].retired}">checked</c:if> />
								<c:if test="${status.errorMessage != ''}">
									<span class="error">${status.errorMessage}</span>
								</c:if>
							</spring:bind>
						</td>
					</tr>
					<tr id="retiredReason">
						<th>
							<spring:message code="general.retiredReason" />
						</th>
						<td>
							<spring:bind path="commandMap.map['register'].retireReason">
								<input type="text" value="${status.value}" name="${status.expression}" size="40" />
								<c:if test="${status.errorMessage != ''}">
									<span class="error">${status.errorMessage}</span>
								</c:if>
							</spring:bind>
						</td>
					</tr>
					<c:if test="${commandMap.map['register'].retiredBy != null}">
						<tr id="retiredBy">
							<th>
								<spring:message code="general.retiredBy" />
							</th>
							<td>
								<a href="#View User"
									onclick="return gotoUser(null, '${commandMap.map['register'].retiredBy.userId}')">${commandMap.map['register'].retiredBy.personName}</a>
								-
								<openmrs:formatDate date="${commandMap.map['register'].dateRetired}" type="medium" />
							</td>
						</tr>
					</c:if>
				</c:if>
			</table>
			<br />
			<input type="submit"
				value='<spring:message code="register.save.button"/>' />
			&nbsp;
			<input type="button" value='<spring:message code="general.cancel"/>'
				onclick="document.location='${pageContext.request.contextPath}/module/register/manageRegister.list'">

		</form>	
</div>

<script type="text/javascript">
	document.getElementById("map['register'].name").focus();
	retiredClicked(document.getElementById("retired"));
</script>

<%@ include file="/WEB-INF/template/footer.jsp"%>
