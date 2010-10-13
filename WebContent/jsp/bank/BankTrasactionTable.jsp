<%@ include file="../include.jsp"%>

<table border="1">
	<thead>
		<tr>
			<th><input type="checkbox"/></th>
			<th>Date</th>
			<th>Remarks</th>
			<th>Deposit</th>
			<th>Withdrawal</th>
			<th>Balance</th>
		</tr>
	</thead>
	<c:forEach var="bankTransaction"
		items="${bankTransactionFrom.bankTransactions}">
		<tr>
			<td><input type="checkbox" /></td>
			<td><c:out value="${bankTransaction.date}" /></td>
			<td><c:out value="${bankTransaction.remarks}" /></td>

			<td><c:if test="${bankTransaction.depositAmount!=0}">
				<c:out value="${bankTransaction.depositAmount}" />
			</c:if></td>
			<td><c:if test="${bankTransaction.withdrawalAmount!=0}">
				<c:out value="${bankTransaction.withdrawalAmount}" />
			</c:if></td>
			<td><c:out value="${bankTransaction.availableAmount}" /></td>
		</tr>
	</c:forEach>
</table>