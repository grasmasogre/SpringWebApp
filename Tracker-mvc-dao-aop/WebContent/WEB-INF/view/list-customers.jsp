<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>List Customers jsp</title>

	<!--  reference our style sheet -->
	<link 	type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>
<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<input type="button" value="Add New Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button"
			/>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<!--  add our html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				 
				<c:forEach var="tempCustomer" items="${customers}">
				
					<!--  Construct an update link that each row can apply -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					<!--  Construct an delete link that each row can apply -->
					<c:url var="deleteLink" value="/customer/deleteCustomer">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					<tr>
						<td> ${tempCustomer.firstName}</td>
						<td> ${tempCustomer.lastName}</td>
						<td> ${tempCustomer.email}</td>
						
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							
							<!-- display the delete link, onclick is javascript code-->
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete?'))) return false">Delete</a>
						</td>
						
					</tr>
				</c:forEach>
				
				<tr>
					<th>-----</th>
					<th>-----</th>							
					<th>-----</th>							
					<th>-----</th>							
				</tr>
			</table>		
		</div>
	</div>
</body>
</html>