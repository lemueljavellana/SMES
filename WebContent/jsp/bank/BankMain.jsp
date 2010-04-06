<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css"><!--
	
	.select {
		background-color: yellow;	
		}
	.tableData {
		width: 1em;
		height: 1em;
		padding: 1em;
		text-align: left;	
	}
</style>
<script type="text/javascript">
	var td1 = null;
	var td2 = null;
	var td3 = null;
	var currentSelectedId = null;
	function highlight(obj) {
		if (td1 || td2) {
			td1.className = null;
			td2.className = null;
			td3.className = null;
		}
		currentSelectedId = obj.id;
		obj.cells[0].className = "select";
		obj.cells[1].className = "select";
		obj.cells[2].className = "select";
		
		td1 = obj.cells[0];
		td2 = obj.cells[1];
		td3 = obj.cells[2];
	}
	function updateBankBottomFrame (url) {
		document.getElementById("bankBottomFrame").src=url;
	}

	function editBank (url){
		document.getElementById("bankBottomFrame").src=url + "?bankId="+currentSelectedId ;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank Main</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<table border="1">
					<tr bgcolor="lightgray" width="50">
						<th>Bank Name</th>
						<th>Bank Address</th>
						<th>Contact Number</th>
					</tr>
					<c:forEach var="bank" items="${bankfrm.banks}">
						<tr onclick="highlight(this);" class="tableData" id="${bank.bankId}">
							<td><c:out value="${bank.bankName}"/></td>
							<td><c:out value="${bank.address}"/></td>
							<td><c:out value="${bank.contactNumber}"/></td>			
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				<input type="button" value="Add"
					onclick="updateBankBottomFrame('<c:url value='/addBank.htm'/>');">
				<input type="button" value="Edit" 
					onclick="editBank('<c:url value='/addBank.htm'/>');">
				<input type="button" value="Delete">
			</td>
		</tr>
		
		<tr>
			<td colspan="3" align="right">
				<iframe id="bankBottomFrame" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
	
</body>
</html>