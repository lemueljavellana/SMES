<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">

	function addAccountTransaction (){
		var ifrm = document.getElementById("addAccount");
		ifrm.src="${accountTransactionMgr.customer.customerId}/<c:url value="addAccount"/>";
	}

	function updateFrame (url){
		var customerId = "${accountTransactionMgr.customer.customerId}";
		var ifrm = document.getElementById("addAccount");
		ifrm.src = customerId + "/"+url;
	}
	
	function editTransaction (){
		var cb = document.getElementsByName("cb");
		for (var i =0; i < cb.length; i++) {
			if (cb[i].checked){
				var checked = cb[i].value;
				var type2Id = checked.split(",");
				var type = type2Id[0];
				var referenceId = type2Id [1];
				var ifrm = document.getElementById("addAccount");
				if (type == "ACCOUNT"){
					ifrm.src="${accountTransactionMgr.customer.customerId}/<c:url value="editAccount"/>/"+referenceId;
				} else if (type == "PAYMENT") {
					ifrm.src="${accountTransactionMgr.customer.customerId}/<c:url value="editPayment"/>/"+referenceId;
				}
			}
		}
	}

	function deleteTransaction () {
		var cb = document.getElementsByName("cb");
		var tag = "";
		for (var i =0; i < cb.length; i++) {
			if (cb[i].checked){
				var checked = cb[i].value;
				var type2Id = checked.split(",");
				var type = type2Id[0];
				var referenceId = type2Id [1];
				
				if (type == "ACCOUNT"){
					tag = tag + "A" + "," + referenceId + ":";
				} else if (type == "PAYMENT") {
					tag = tag + "P" + "," + referenceId + ":";
				}
			}
		}
		alert (tag);
		var ifrm = document.getElementById("addAccount");
		ifrm.src="${accountTransactionMgr.customer.customerId}/<c:url value="deleteTransaction"/>/"+tag;
	}
</script>
<body>
<form:form method="POST" commandName="accountTransactionMgr" id="accountTransactionMgr">
	
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

	<!-- TODO: Add the searching here. -->
	<table width="100%" bordercolor="black" border="1" id="customerAccount">
		<thead>
			<tr>
				<th width="1%">#</th>
				<th width="1%"><input type="checkbox" id="selectAll" name="selectAll"/></th>
				<th width="6%">Date</th>
				<th width="8%">Reference Number</th>
				<th width="28%">Description</th>
				<th width="7%">Payment</th>
				<th width="7%">Account</th>
				<th width="7%">Interest</th>
				<th width="7%">Running Total</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="transaction" items="${accountTransactionMgr.page.data}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td align="center"><input type="checkBox" value="${transaction.transactionType},${transaction.referenceId}"
						id="cb" name="cb"/></td>
					<td> <c:out value="${transaction.date}"/></td>
					<td> <c:out value="${transaction.referenceNumber}"/></td>
					<td> <c:out value="${transaction.description}"/></td>
					<td align="right"><c:out value="${transaction.payment}"/></td>
					<td align="right"><c:out value="${transaction.account}"/></td>
					<td align="right"><c:out value="${transaction.accountWithInterest}"/></td>
					<td align="right"><c:out value="${transaction.runningTotal}"/></td>
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
</form:form>
<table width="100%">
	<tr>
		<td align="right" >
			<input type="button" value="Account" onclick="addAccount (${accountTransactionMgr.customer.customerId});">
			<input type="button" value="Payment" onclick="updateFrame('<c:url value="addPayment"/>')">
			<input type="button" value="Edit" onclick="editTransaction()">
			<input type="button" value="Delete" >
		</td>
	</tr>
</table>
<div id="AddEdittransaction">
	
</div>

</body>
</html>