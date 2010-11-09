<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Customer</title>
</head>
<body>
<form:form method="POST" commandName="customerAccountPreference">
	
	<table>
		<tr>
			<th colspan="2">
				Customer account preferences
			</th>
		</tr>
		<tr>
			<td>Interest</td>
			<td align="right"><form:input  path="interest" /></td>
		</tr>
		<tr>
			<td colspan="1">
				<font color="red">
				<form:errors path="interest" cssClass="error"/>
				</font>
			</td>
		</tr>
		<tr>
			<td>
				Maximum Amount 
			</td>
			<td><form:input path="maximumAmount"/></td>
		</tr>
		<tr>
			<td>
				<form:hidden path="customerId"/>
				<form:hidden path="customerAccountPreferenceId"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" align="left" value="save">
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>