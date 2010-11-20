<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body></body>
<table width="100%">
	<tr>
		<td align="left" height="100%">
			<iframe id="customerList" frameborder="0" src="<c:url value="customerList"/>" height="550"></iframe>
		</td>
		<td width="100%">
			<iframe id="body" frameborder="0" height="550" width="100%" name="body" /></iframe>
		</td>
	</tr>
</table>
</html>