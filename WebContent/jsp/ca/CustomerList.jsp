<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="../jsp/css/style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript">
	function popup (link, windowName){
		alert (link);
		if (!window.focus)
			return true;
		window.open(link, windowName, 'width=275,height=300,scrollbars=yes');
		return false;
	}
	
	function editCustomer (link, windowName){
		if (!window.focus)
			return true;
		var checkBoxes = document.getElementById("customerId");
		alert (checkBoxes);
		var id = "";
		for (var i = 0; i < checkBoxes.length; i++){
			var checkBox = checkBoxes[i];
			alert (checkBox);
			if (checkBox.checked){
				id = checkBox.value;
				break;
			}
		}
		popup (link+"/"+id, windowName);
		return false;
	}
	
	function deleteCustomers (){
		var frm = document.getElementById("customerForm");
		frm.action = "customerlist/delete";
		frm.submit ();
	}

	function addCustomer () {
		var ifrm = window.parent.document.getElementById("body");
		ifrm.src="<c:url value="addCustomer"/>";
	}

	function editCustomer (customerId){
		var ifrm = window.parent.document.getElementById("body");
		ifrm.src="<c:url value="addCustomer"/>/" + customerId;
	}

	function accountPreferences (customerId) {
		var ifrm = window.parent.document.getElementById("body");
		ifrm.src="<c:url value="customerAcountPreferences"/>/" + customerId;
	}

	function accountTransaction (customerId){
		var ifrm = window.parent.document.getElementById("body");
		ifrm.src="<c:url value="accountTransaction"/>/" + customerId;
	}
</script>
<body >
	<h3>Customer's List</h3>
	<div>
		<form:form method="POST" commandName="customerDto" id="customerForm">
			<form:input path="customerName"/>
			<input type="submit" name="search" value="search">
			<input type="button" value="Add"
			 		onclick="addCustomer (this);">
		</form:form>
	</div>
	<table border="1" bordercolor="black" width="100%">
		<thead>
			<tr>
				<th><input type="checkbox"/></th>
				<th >
					Customer's Name
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="customer" items="${customerDto.customers}" varStatus="status">
				<tr class="${status.index % 2 == 0 ? 'trEven' : 'trOdd'}">
					<td align="center" width="5%">
						<input type="checkbox" id="cb" name="cb" value="${customer.customerId}"/>
					</td>
					<td width="95%" onclick="accountTransaction(${customer.customerId})">
						<c:out value="${customer.firstName}"/> <c:out value="${customer.lastName}"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>