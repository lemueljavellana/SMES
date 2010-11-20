<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
#header {
	width: 50%;
	height: 40px;
	margin: 0 auto;
	background: #608F30;
	border-bottom: 1px solid #2D4516;
}

#footer {
	width: 50%;
	margin: 0 auto;
	padding: 0;
	background: #333333;
}

#footer p {
	margin: 0;
	padding: 3px 0;
	text-align: left;
	line-height: normal;
	color: #B5ADA5;
}

#footer a {
	color: #B5ADA5;
}
</style>
<title>smoove</title>
</head>
<body>
<form:form method="POST" commandName="credential">
<br></br>
<br></br>
	<div id="header">
		<div id="menu">
			<ul>
			</ul>
		</div>
	</div>
	<table width="60%" align="center">
		<tr height="400">
			<td>
				<table align="center">
					<tr>
						<td colspan="2" align="right"><i><b>Hinstro Alpha Version</b></i><hr></hr></td>
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
	</table>
	<div id="footer">
		<p></p>
	</div>
</form:form>	
</body>
</html>