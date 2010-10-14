<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	function popup (link, windowName){
		if (!window.focus)
			return true;
		window.open(link, windowName, 'width=275,height=300,scrollbars=yes');
		return false;
	}
</script>
<body>
	<table>
		<tr>
			<th colspan="3" align="left">Customer's List</th>
		</tr>
		<tr >
			<td>
				<input name="customer Name">
			</td>
			<td colspan="2">
				<input type="button" name="search" value="search">
			</td>
		</tr>
		<tr>
			<td colspan="3">
			 	<input type="button" value="Add"
			 		onclick="return popup ('addCustomer.htm','Add User')">
			 	<input type="button" value="Edit">
			 	<input type="button" value="Delete">
			</td>
		</tr>
	</table>
</body>
</html>