<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All Bank Account Holder Management Screen</title>
</head>
<body>
	<div align="center">
		<h1 >Your Registration is successful. Plz check your account details in below</h1>
		<h3>
			<a href="accountRegistrationForm">New User Account Registration</a>
		</h3>
		<table border="1">
			<th>Id</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>UserAddress</th>
			<th> AccountNumber</th>
			<th> AccountType</th>
			<th>AccountBalance</th>
			<th>Email</th>
			<th>Mobile Number</th>
			<th>Password</th>
			<th>AccountEnable</th>
			<th>Action</th>

			<c:forEach var="user" items="${listRegistrationBeans}">
				<tr>
					<td>${user.userId}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.userAddress}</td>
					<td>
					<c:forEach var="accountNumber" items="${user.accountNumbers}">
					
					${accountNumber}  </c:forEach>
					</td>
					<td>
					<c:forEach var="accountType_1" items="${user.accountType}">
					
					${accountType_1}  </c:forEach>
					</td>
					<td>${user.accountBalance}</td>
					<td>${user.email}</td>
					<td>${user.mobileNumber}</td>
					<td>${user.password}</td>
					<td>${user.accountEnabled}</td>
					<td><a href="editUserAccount?userId=${user.userId}">Edit</a> 
					<a	href="deleteUserAccount?userId=${user.userId}">Delete</a></td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>
