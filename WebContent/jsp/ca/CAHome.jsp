<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="../js/dojo/dojo.js" type="text/javascript"></script>
<link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
<script src="../js/AccountTransaction.js" type="text/javascript"></script>
<body onload="onLoad ('${pageContext.request.contextPath}');">
<table width="100%" height="100%" >
	<tr>
		<td align="left" width="20%" valign="top">
			<div id="ajaxCustomerList">
			</div>
		</td>
		<td width="80%" align="left" valign="top">
			<div id="ajaxBody">
			</div>
		</td>
	</tr>
</table>
</body>
</html>