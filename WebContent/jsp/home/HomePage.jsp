<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function updateLeftFrame (url){
		document.getElementById("rightTopFrame").src=url;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank</title>
</head>
<body>
	<table >
		<tr >
			<td width="20%">
				<table>
					<tr>
						<td><a href="#" onclick="updateLeftFrame('<c:url value='/bank.htm'/>')">Bank</a></td>
					</tr>
					<tr>
						<td><u>Deposit/Withdrawal</u></td>
					</tr>
					<tr>
						<td><u>Logout</u></td>
					</tr>
				</table>
			</td>
			<td align="left" width="80%" height="100%" >
				<iframe id="rightTopFrame" frameborder="0" height="500" width="500"></iframe>
			</td>
			<td>
				<iframe id="rightBottomFrame" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
	
</body>
</html>