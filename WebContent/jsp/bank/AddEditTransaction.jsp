<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add/Edit transaction</title>
</head>
<body>
	<table>
		<tr>
			<td>Date</td>
			<td>
				<form:input 
					path="bankTransactionFrom.bankTransaction.date"/>
			</td>			
		</tr>
		<tr>
			<td>Remarks</td>
			<td>
				<form:input
					path="bankTransactionFrom.bankTransaction.remarks"/>
			</td>
		</tr>
		<tr>
			<td>Amount</td>
			<td>
				<!-- TODO: condition that 
				will tell if deposit or withdrawal -->
				<form:input
					path="bankTransactionFrom.bankTransaction.depositAmount"/>
			</td>
		</tr>
		<tr>
			<td><input type="button" value="Save"/></td>
			<td><input type="button" value="Cancel"/></td>
		</tr>
	</table>
</body>
</html>