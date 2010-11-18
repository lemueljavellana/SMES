<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
<style type="text/css">
	headerTable {
		background-color: lightblue;
		color: blue;
	}
</style>
</head>
<body>
	<table width="100%" bgcolor="lightblue" border="0">
		<tr>
			<td width="60%">
				<b><font style="font-family: monospace">Hinstro</font> <i>alpha version</i></b>
			</td>
			<td width="40%" bgcolor="blue">
				<table width="100%">
					<tr>
						<td>
							<font color="lightblue"><b>HOME</b></font> | <font color="lightblue"><b>CUSTOMER ACCOUNT</b></font> | <font color="lightblue"><b>SETTING</b></font>
						</td>
						<td align="right">
							${credential.userName}
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>