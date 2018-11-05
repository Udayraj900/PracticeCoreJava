<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="title.SearchPhonePage" /></title>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/style.css">
<script>
	$(document).ready(function() {
		$("#button_id").click(function() {
				var id = $('#brandList_id').val();
				var min = $('#minValue').val();
				var max = $('#maxValue').val();
				var data1 = 'id='+ id + '&min='+ min + '&max='+ max;
				$.ajax({
					url : 'searchMobileOnCriteria.view',
					method : 'get',
					contentType : 'json',
					data : data1,
					success : function(results) {
					    var html = '<h2><spring:message code="header.SearchResults" /></h2><table><tr><th>Brand</th><th>Model</th><th>Rating</th><th>Price</th></tr>';
						if (results != null) {
								$(results).each(function(index,value) {
									html = html 
									+ '<tr><td>'
									+ value.brandName 
									+ '</td><td>'
									+ value.modelName
									+ '</td><td>'
									+ value.rating
									+ '</td><td>'
									+ value.price
									+ '</td></tr>';
							});
						html += '<table>';
						$("#div3").html(html);
					}
				   }
				});
			 });
		 });
</script>
</head>
<body>
	<div id="div1">
		<h2>
			<spring:message code="header.TopRatedMobilePhone" />
		</h2>
		<table>
			<tr>
				<th>Brand</th>
				<th>Model</th>
				<th>Avg Rating</th>

			</tr>
			<c:forEach items="${mobileList}" var="mobile">
				<tr>
					<td><c:out value="${mobile.brandName}" /></td>
					<td><c:out value="${mobile.modelName}" /></td>
					<td><c:out
							value="${mobile.rating} (${mobile.noOfReviews} Reviews)" /></td>

				</tr>
			</c:forEach>

		</table>
	</div>

	<div id="div2">
		<h2>
			<spring:message code="header.SearchMobilePhone" />
		</h2>
		<form:form method="GET" action="searchMobileOnCriteria.view">
		<table>
			<tr>
				<td>Brand</td>
				<td><select multiple="multiple" id="brandList_id" size="">
						<c:forEach items="${brandList}" var="brands">
							<option value="${brands.brandId}">
								<c:out value="${brands.brandName}" />
							</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Price Range</td>
				<td><b>Min:</b><input type="text" id=minValue>&nbsp;&nbsp;&nbsp;<b>Max:</b><input
					type="text" id="maxValue"></td>
			</tr>
			<tr>
				<td><button type="button" onclick="location = 'index.jsp'">Cancel</button></td>
				<td><button type="button" id="button_id">Search Mobile
						Phone</button></td>
			</tr>
		</table>
</form:form>
	</div>

	<div id="div3">
		
	</div>
</body>
</html>