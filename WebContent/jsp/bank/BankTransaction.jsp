<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
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
		<td>
			<form:select path="bankTransactionFrom">
				<form:options items="${bankTransactionFrom.banks}" itemValue="bankId" itemLabel="bankName"/>
			</form:select>
		</td>
		<td>Date From</td>
		<td><input type="text"></input></td>
		<td>Date To</td>
		<td><input type="text"></input></td>
		<td><input type="button" value="search"></input></td>
	</tr>
	<tr>
		<td colspan="7" align="center">
			<table border="1">
				<thead>
					<tr>
						<th>
							<input type="checkbox"/>
						</th>
						<th>
							Date
						</th>
						<th>
							Remarks
						</th>
						<th>
							Deposit
						</th>
						<th>
							Withdrawal
						</th>
						<th>
							Balance
						</th>
					</tr>
				</thead>
				<c:forEach var="bankTransaction" items="${bankTransactionFrom.bankTransactions}">
					<tr>
						<td><input type="checkbox"/></td>
						<td><c:out value="${bankTransaction.date}"/></td>
						<td><c:out value="${bankTransaction.remarks}"/></td>
						
						<td> 
							<c:if test="${bankTransaction.depositAmount!=0}">
								<c:out value="${bankTransaction.depositAmount}"/>
							</c:if>
						</td>
						<td>
							<c:if test="${bankTransaction.withdrawalAmount!=0}">
								<c:out value="${bankTransaction.withdrawalAmount}"/>
							</c:if>
						</td>
						<td><c:out value="${bankTransaction.availableAmount}"/></td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>
</body>
</html>