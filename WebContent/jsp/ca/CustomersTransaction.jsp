<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<table >
		<tr align="left">
			<td align="left" width="10%">
				Customer's Name:
			</td>
			<th>
				<c:out value="${accountTransactionMgr.customer.firstName}"/>
					<c:out value="${accountTransactionMgr.customer.lastName}"/>
			</th>
		</tr>
		<tr>
			<td>
				Address:
			</td>
			<th align="left">
				<c:out value="${accountTransactionMgr.customer.address}"></c:out>
			</th>
		</tr>
		<tr align="left">
			<td>
				Contact Number:
			</td>
			<th align="left">
				<c:out value="${accountTransactionMgr.customer.contactNumber}"></c:out>
			</th>
		</tr>
	</table>
	
	<div id="tableTab">
		<div id="menu">
			<ul>
				<!-- Accounts Receivable -->
				<li id="arTabHeader" class="current_page_item"><a href="#">Accounts Receivable</a></li>
				<li id="paymentTabHeader" onclick="showPaymentTab (${accountTransactionMgr.customer.customerId})"><a href="#">Payments</a></li>
			</ul>
		</div>
	</div>
	<div id="tabContent">
		<form:form method="POST" commandName="accountTransactionMgr" id="accountTransactionMgr">
	<!-- TODO: Add the searching here. -->
	<table width="100%" bordercolor="black" border="1" id="customerAccount">
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
				<Th width="12%">Total</Th>
				<th width="12%">Running Total</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="account" items="${accountTransactionMgr.page.data}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td align="center"><input type="checkBox"
						value="${account.accountId}" id="cb" name="cb" onclick="onClckAccountCheckbock()"/></td>
					<td> <c:out value="${account.formattedAccountDate}"/></td>
					<td> <c:out value="${account.formattedDueDate}"/></td>
					<td> <c:out value="${account.referenceNumber}"/></td>
					<td align="right"><c:out value="${account.description}"/></td>
					<td align="right"><c:out value="${account.formattedAmount}"/></td>
					<td align="right"><c:out value="${account.formattedEarnedInterest}"/></td>
					<td align="right"><c:out value="${account.formattedTotalAmount}"/></td>
					<td align="right"><c:out value="${account.formattedRunningTotal}"/></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td>${accountTransactionMgr.page.dataSize + ((accountTransactionMgr.page.currentPage - 1) * accountTransactionMgr.page.pageSetting.maxResult)}/${accountTransactionMgr.page.totalRecords}</td>
				<td colspan="8" align="right">
					<c:if test="${(accountTransactionMgr.page.currentPage == accountTransactionMgr.page.lastPage 
									|| accountTransactionMgr.page.currentPage == accountTransactionMgr.page.lastPage - 1) 
									&& (accountTransactionMgr.page.currentPage != 1 && accountTransactionMgr.page.currentPage != 2)}">
						<a href="#" onclick="goToPage (1)" class="pageNumber">1</a>
						<a href="#" onclick="goToPage (2)" class="pageNumber">2</a>
						..
					</c:if>
					<c:if test="${accountTransactionMgr.page.prevPage >= 1 }">
						<a href="#" onclick="goToPage (${accountTransactionMgr.page.prevPage})" class="pageNumber"><c:out value="${accountTransactionMgr.page.prevPage}"/></a>
					</c:if>
					<a class="currentPage"><c:out value="${accountTransactionMgr.page.currentPage}"/></a>
					<c:if test="${accountTransactionMgr.page.nextPage <= accountTransactionMgr.page.lastPage}">
						<a href="#" onclick="goToPage (${accountTransactionMgr.page.nextPage})" class="pageNumber"><c:out value="${accountTransactionMgr.page.nextPage}"/></a>
					</c:if>
					<c:if test="${accountTransactionMgr.page.nextPage < accountTransactionMgr.page.lastPage}">
						...
						<c:if test="${accountTransactionMgr.page.lastPage - 1 != accountTransactionMgr.page.nextPage}">
							<a href="#" class="pageNumber" onclick="goToPage (${accountTransactionMgr.page.lastPage - 1})"><c:out value="${accountTransactionMgr.page.lastPage - 1}"></c:out></a>
						</c:if>
						<a href="#" class="pageNumber" onclick="goToPage (${accountTransactionMgr.page.lastPage})"><c:out value="${accountTransactionMgr.page.lastPage}"></c:out></a>
					</c:if>
				</td>
			</tr>
		</tfoot>
	</table>
<table width="100%">
	<tr>
		<td align="right" >
			<input type="button" value="Add" onclick="addAccount (${accountTransactionMgr.customer.customerId});">
			<input type="button" value="Edit" id="editButton" name="editButton"
					onclick="editAcccount(${accountTransactionMgr.customer.customerId})" disabled="disabled">
			<input type="button" value="Delete" id="deleteButton" name="deleteButton"
					disabled="disabled" onclick="deleteAccount(${accountTransactionMgr.customer.customerId})">
		</td>
	</tr>
</table>
	
</form:form>
</div>

<div id="AddEdittransaction">
	
</div>

</body>
</html>