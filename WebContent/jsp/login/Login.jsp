<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>smoove</title>
</head>
<body>
<form:form method="POST" commandName="credential">
<br/>
<br/>
<br/>
	
	<table width="60%" align="center">
		<tr height="60">
			<td bgcolor="lightblue"><b>SMOOVE v1.0</b></td>
		</tr>
		<tr height="400">
			<td>
				<table align="center">
					<tr>
						<td colspan="2" align="right"><i><b>Credential</b></i><hr></hr></td>
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
								<form:errors path="message" cssClass="error"/>
							</font>
			           </td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" name="enter" value="Login">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td bgcolor="lightblue"></td>
		</tr>
	</table>
	
</form:form>	
</body>
</html>