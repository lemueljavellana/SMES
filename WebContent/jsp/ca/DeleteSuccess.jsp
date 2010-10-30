<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	function reloadCustomerList (){
		var ifrm = window.parent.document.getElementById("customerList");
		ifrm.src="<c:url value="customerList"/>";
	}
</script>
<body onload="reloadCustomerList ();">
	<div style="color: red;">
		Successfully saved
	</div>
</body>	
</html>