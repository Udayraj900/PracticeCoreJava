<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="title.MobileReviewForm" /></title>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/style.css">
<script>
	$(document).ready(function() {
		$("#brand_id").change(function() {
			$.ajax({
				url : 'loadModelList.view',
				method : 'get',
				ContentType : 'json',
				 data : {
			         brandId : $('#brand_id').val()
			       },
				success : function(results) {

					var html = '<option value="" label="--select--" />';
					if (results != null) {
						$(results).each(function(index, value) {

							html = html + '<option value="'+ value.modelId+'">' + value.modelName + '</option>';
						});
						$("#model").html(html);
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<h2>
		<spring:message code="header.MobileReviewForm" />
	</h2>

	<form:form commandName="mobileReview" method="POST" action="saveReview.view">
		<table>
			<tr>
				<td><spring:message code="label.brand"></spring:message></td>

				<td><form:select path="model.brand.brandId" id="brand_id">
						<form:option value="" label="--select--" />
						<c:forEach items="${brandList}" var="brand">
							<form:option value="${brand.brandId}">
								<c:out value="${brand.brandName}" />
							</form:option>
						</c:forEach>
					</form:select></td>
				<td><form:errors path="model.brand.brandId"></form:errors></td>
			</tr>

			<tr>
				<td><spring:message code="label.model"></spring:message></td>
				<td><form:select path="model.modelId" id="model">
						<form:option value="" label="--select--" />

					</form:select></td>
				<td><form:errors path="model.modelId"></form:errors></td>
			</tr>
			<tr>
				<td><spring:message code="label.designRating"></spring:message></td>
				<td><form:select path="designRating">
						<form:option value="" label="--select--" />
						<form:options items="${rating}" />
					</form:select></td>
				<td><form:errors path="designRating"></form:errors></td>
			</tr>
			<tr>
				<td><spring:message code="label.featuresRating"></spring:message></td>
				<td><form:select path="featuresRating">
						<form:option value="" label="--select--" />
						<form:options items="${rating}" />
					</form:select></td>
				<td><form:errors path="featuresRating"></form:errors></td>
			</tr>
			<tr>
				<td><spring:message code="label.performanceRating"></spring:message></td>
				<td><form:select path="performanceRating">
						<form:option value="" label="--select--" />
						<form:options items="${rating}" />
					</form:select></td>
				<td><form:errors path="performanceRating"></form:errors></td>
			</tr>
			<tr>
				<td><spring:message code="label.comment"></spring:message></td>
				<td><form:textarea path="comment" cols="8" rows="2"></form:textarea></td>
				<td><form:errors path="comment"></form:errors></td>
			</tr>
			<tr><td></td>
				<td><button type="button" onclick="location = 'index.jsp'">Cancel</button></td>
				<td><button type="submit">Submit Ratings</button></td>
			</tr>

		</table>

	</form:form>
	
	
</body>
</html>