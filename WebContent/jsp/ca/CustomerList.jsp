<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

	function searchCustomers () {
		var frm = document.getElementById("customerForm");
		frm.action = "customerlist/search";
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
</script>
<body>
	<table>
		<tr>
			<th colspan="3" align="left">Customer's List</th>
		</tr>
		<tr >
			<form:form method="POST" commandName="customerDto" id="customerForm">
			<td>
			
				<form:input path="customerName"/>
				
			</td>
			<td colspan="2">
				<input type="submit" name="search" value="search">
			</td>
			</form:form>
			<td>
				<input type="button" value="Add"
			 		onclick="addCustomer (this);">
			</td>
		</tr>
	</table>

	<table border="1" bordercolor="black">
		<tr>
			<th>
				Customer's Name
			</th>
			<th>
				Edit
			</th>
			<th>
				Delete
			</th>
			<th>
				Account Settings
			</th>
		</tr>
		<c:forEach var="customer" items="${customerDto.customers}" varStatus="status">
		<tr>
			<td><c:out value="${customer.firstName}"/> <c:out value="${customer.lastName}"/>
			</td>
			<td>
				<a onclick="editCustomer(${customer.customerId})">edit</a><br>
			</td>
			<td>
				<a href="customerList/${customer.customerId}">icon</a>
			</td>
			<td>
				<a href=".">icon</a>
			</td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>