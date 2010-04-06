<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<c:forEach var="row" items="addEditFrmHandler.rows">
			<tr>
				<td>
					<c:out value="${row.label}"></c:out>
				</td>
				<td>
					<c:if test="${row.type=='INPUT'}">
						<form:input path="${row.data}"/>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td>
				<input type="submit" name="save" value="save">
			</td>
		</tr>
	</table>
</body>
</html>