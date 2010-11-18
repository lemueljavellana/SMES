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
			<td align="left">
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
	<table border="1" width="100%">
		<tr>
			<td><input type="checkbox" id="selectAll" name="selectAll"/></td>
			<td>Date</td>
			<td>Reference Number</td>
			<td>Description</td>
			<td>Payment</td>
			<td>Account</td>
			<td>Interest</td>
			<td>Running Total</td>
		</tr>
		<c:forEach var="transaction" items="${accountTransactionMgr.accountTrasactionDtos}">
		<tr>
			<td><input type="checkBox" value="${transaction.transactionType},${transaction.referenceId}"
				id="cb" name="cb"/></td>
			<td><c:out value="${transaction.date}"/></td>
			<td><c:out value="${transaction.referenceNumber}"/></td>
			<td><c:out value="${transaction.description}"/></td>
			<td><c:out value="${transaction.payment}"/></td>
			<td><c:out value="${transaction.account}"/></td>
			<td><c:out value="${transaction.accountWithInterest}"/></td>
			<td><c:out value="${transaction.runningTotal}"/></td>
		</c:forEach>
	</table>
</form:form>
<table width="100%">
	<tr>
		<td align="right" >
			<input type="button" value="Account" onclick="updateFrame('<c:url value="addAccount"/>')">
			<input type="button" value="Payment" onclick="updateFrame('<c:url value="addPayment"/>')">
			<input type="button" value="Edit" onclick="editTransaction()">
			<input type="button" value="Delete" onclick="deleteTransaction ()">
		</td>
	</tr>
</table>

<iframe width="100%" height="100%" frameborder="0" id="addAccount" name="addAccount">
</iframe>

</body>
</html>