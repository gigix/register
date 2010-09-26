<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="localHeader.jsp"%>

<p>
	<h2>
		<spring:message code="register.heading.title" />
	</h2>
</p>

<b class="boxHeader"><spring:message code="register.summary" />
</b>
<div class="box">
	<b>
		<form method="post" id="createForm" name="createForm">
			<table cellpadding="3" cellspacing="0">
				<tr>
					<td>
						<spring:message code="register.name" />
					</td>
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
					<td>
						<spring:message code="register.description" />
					</td>
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
					<td>
						<spring:message code="register.types" />
					</td>
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
					<td>
						<spring:message code="register.htmlform.name" />
					</td>
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
			</table>
			<br />
			<input type="submit"
				value='<spring:message code="register.create.button"/>' />
			&nbsp;
			<input type="button" value='<spring:message code="general.cancel"/>'
				onclick="document.location='${pageContext.request.contextPath}/admin'">

		</form>
</div>
</b>
<script type="text/javascript">
	document.getElementById("map['register'].name").focus();
</script>

<%@ include file="/WEB-INF/template/footer.jsp"%>
