<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include.jsp" %>
<table width="100%" bordercolor="black" border="1" id="unPaidAccounts">
	<thead>
		<tr>
			<th width="1%">#</th>
			<th width="1%"><input type="checkbox" id="selectAll" name="selectAll"/></th>
			<th width="7%">Date</th>
			<th width="7%">Due Date</th>
			<th width="8%">Reference Number</th>
			<th width="28%%">Description</th>
			<th width="12%">Amount</th>
			<th width="12%">Interest Earned</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="account" items="${accounts.data}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td align="center"><input type="checkBox"
						value="${account.accountId}" id="unPaidAccountCB" name="unPaidAccountCB" /></td>
					<td> <c:out value="${account.formattedAccountDate}"/></td>
					<td> <c:out value="${account.formattedDueDate}"/></td>
					<td> <c:out value="${account.referenceNumber}"/></td>
					<td align="right"><c:out value="${account.description}"/></td>
					<td align="right"><c:out value="${account.formattedAmount}"/></td>
					<td align="right"><c:out value="${account.formattedEarnedInterest}"/></td>
				</tr>
		</c:forEach>
		<tr>
			<td colspan="4">Sub Total</td>
			<td align="right"></td>
			<td align="right"></td>
			<td align="right"><b>${totalAmount}</b></td>
			<td align="right"><b>${totalInterestEarned}</b></td>
		</tr>
		<tr>
			<td colspan="4">Total</td>
			<td align="right"></td>
			<td align="right"></td>
			<td align="right"><b></b></td>
			<td align="right"><b>${total}</b></td>
		</tr>
	</tbody>
</table>
