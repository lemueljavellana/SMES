<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Customer</title>
</head>
<body>
<form:form method="POST" commandName="customer" id="customer">
	<table>
		<tr>
			<td>First Name</td>
			<td><form:input path="firstName"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<font color="red">
				<form:errors path="firstName" cssClass="error"/>
				</font>
			</td>
		</tr>
		<tr>
			<td>Middle Name</td>
			<td><form:input path="middleName"/></td>
		</tr>
		<tr>
			<td>Last Name</td>
			<td><form:input path="lastName"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<font color="red">
				<form:errors path="lastName" cssClass="error"/>
				</font>
			</td>
		</tr>
		<tr>
			<td>Contact Number</td>
			<td><form:input path="contactNumber"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<font color="red">
				<form:errors path="contactNumber" cssClass="error"/>
				</font>
			</td>
		</tr>
		<tr>
			<td>Address</td>
			<td><form:input path="address"/></td>
		</tr>
		<tr>
			<td>
				<form:hidden path="customerId"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" align="left" value="save" onclick="postCustomer ();">
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>