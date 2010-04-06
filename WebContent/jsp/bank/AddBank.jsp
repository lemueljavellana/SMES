<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
</script>
<title>Add bank</title>
</head>
<body>
<form method="post">
	<table>
		<tr>
			<td>Bank Name</td>
			<td><form:hidden path="bank.bankId" /><form:input path="bank.bankName"/></td>
			<td>
				<spring:hasBindErrors name="bank">
					<font color="red"> Invalid bank name
						<c:out value="${status.errorMessage}"></c:out>
					</font>				
				</spring:hasBindErrors>
			</td>
		</tr>
		<tr>
			<td>Address</td>
			<td><form:input path="bank.address"/></td>
			<td>
				<spring:hasBindErrors name="bank">
					<font color="red"> Invalid address
						<c:out value="${status.errorMessage}"></c:out>
					</font>
				</spring:hasBindErrors>
			</td>
		</tr>
		<tr>
			<td>Contact Number</td>
			<td><form:input path="bank.contactNumber"/></td>
			<td>
				<spring:hasBindErrors name="bank">
					<font color="red">invalid contact number
						<c:out value="${status.errorMessage}"></c:out>
					</font>				
				</spring:hasBindErrors>
			</td>
		</tr>
		<tr align="right">
			<td colspan="3" align="right">
				<input type="submit" name="Save" value="Save"/>
				<input type="button" name="Cancel" value="Cancel" onclick=""></input>
			</td>
		</tr>
	</table>
</form>
</body>
</html>