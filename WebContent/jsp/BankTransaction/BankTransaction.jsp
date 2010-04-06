<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 
	author : Lemuel M. Javellana
	Description: View for bank transactions, this will handle the listing
	of deposit and withdrawal transactions.
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td>Bank Name</td>
		<td><input type="text"></input></td>
		<td>Date From</td>
		<td><input type="text"></input></td>
		<td>Date To</td>
		<td><input type="text"></input></td>
	</tr>
	<tr>
		<td colspan="3">
			<table>
				<tr>
					<th>
						Date
					</th>
					<th>
						Withdrawal
					</th>
					<th>
						Deposit
					</th>
					<th>
						Amount
					</th>
					<th>
						Total Amount
					</th>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>