<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Javellana Enterprise</title>
</head>
<body>
<form:form method="POST" commandName="credential">
	<table>
		<tr>
			<td colspan="2" align="left" bgcolor="lightblue"><b>Log in</b></td>
		</tr>
		<tr>
			<td>User name</td>
			 <td><form:input path="userName"/></td>
		</tr>
		<tr>
			<td>Password</td>
			<td>
				<form:password path="password"/>
			</td>
		</tr>
		<tr>
			<td>Company Name</td>
			<td>
				<form:input path="companyName"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<font color="red">
				<form:errors path="userName" cssClass="error"/>
				</font>
				<!-- 
                <spring:hasBindErrors name="credential">
                <font color="red">
                	Incorrect user name or Password
                </font>
                </spring:hasBindErrors>
                 -->
           </td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" name="login" value="Login">
			</td>
		</tr>
	</table>
</form:form>	
</body>
</html>