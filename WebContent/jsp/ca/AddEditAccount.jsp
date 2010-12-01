<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Account</title>
</head>
<body>
Account
<form:form method="POST" commandName="account">
	<table>
		<tr>
			<form:hidden path="customerId"/>
			<td>Type</td>
			<td><form:select path="accountTypeId">
				<form:options items="${accountTypes}" itemLabel="name" itemValue="accountTypeId"/>
			</form:select>
			</td>
		</tr>
		<tr>
			
			<td>Date</td>
			<td><form:input path="accountDate"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<font color="red">
				<form:errors path="accountDate" cssClass="error"/>
				</font>
			</td>
		</tr>
		<tr>
			<td>Reference Number</td>
			<td><form:input path="referenceNumber"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<font color="red">
				<form:errors path="referenceNumber" cssClass="error"/>
				</font>
			</td>
		</tr>
		<tr>
			<td>Description</td>
			<td><form:input path="description"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<font color="red">
				<form:errors path="description" cssClass="error"/>
				</font>
			</td>
		</tr>
		<tr>
			<td>Amount</td>
			<td><form:input path="amount"/></td>
		</tr>
		<tr>
			<td>
				<form:hidden path="accountId"/>
			</td>
		</tr>
	</table>
	<input type="button" name="save" value="save" onclick="saveAccount (${account.customerId});">
</form:form>
</body>
</html>