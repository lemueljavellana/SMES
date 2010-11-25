<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body >
	<div>
		<input type="text" name="textSearch" id="textSearch">
		<input type="button" name="search" value="search" onclick="searchCustomer ();">
		<input type="button" value="Add"
		 		onclick="addCustomer ();">
	</div>
	<table border="1" bordercolor="black" width="100%" id="customerList">
		<thead>
			<tr>
				<th>#</th>
				<th><input type="checkbox"/></th>
				<th>
					Customer's Name
				</th>
			</tr>
		</thead>
		<tbody >
			<c:forEach var="customer" items="${customerDto.page.data}" varStatus="status">
				<tr class="${status.index % 2 == 0 ? 'trEven' : 'trOdd'}">
					<td>${status.index + 1}</td>
					<td align="center" width="5%">
						<input type="checkbox" id="cb" name="cb" value="${customer.customerId}"/>
					</td>
					<td width="95%" onclick="showCustomerAccount(${customer.customerId})">
						<c:out value="${customer.firstName}"/> <c:out value="${customer.lastName}"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">${customerDto.page.dataSize + ((customerDto.page.currentPage - 1) * customerDto.page.pageSetting.maxResult)}/${customerDto.page.totalRecords}</td>
				<td colspan="3" align="right">
					<c:if test="${(customerDto.page.currentPage == customerDto.page.lastPage 
									|| customerDto.page.currentPage == customerDto.page.lastPage - 1) 
									&& (customerDto.page.currentPage != 1 && customerDto.page.currentPage != 2)}">
						<a href="#" onclick="goToPage (1)" class="pageNumber">1</a>
						<a href="#" onclick="goToPage (2)" class="pageNumber">2</a>
						..
					</c:if>
					<c:if test="${customerDto.page.prevPage >= 1 }">
						<a href="#" onclick="goToPage (${customerDto.page.prevPage})" class="pageNumber"><c:out value="${customerDto.page.prevPage}"/></a>
					</c:if>
					<a class="currentPage"><c:out value="${customerDto.page.currentPage}"/></a>
					<c:if test="${customerDto.page.nextPage <= customerDto.page.lastPage}">
						<a href="#" onclick="goToPage (${customerDto.page.nextPage})" class="pageNumber"><c:out value="${customerDto.page.nextPage}"/></a>
					</c:if>
					<c:if test="${customerDto.page.nextPage < customerDto.page.lastPage}">
						...
						<c:if test="${customerDto.page.lastPage - 1 != customerDto.page.nextPage}">
							<a href="#" class="pageNumber" onclick="goToPage (${customerDto.page.lastPage - 1})"><c:out value="${customerDto.page.lastPage - 1}"></c:out></a>
						</c:if>
						<a href="#" class="pageNumber" onclick="goToPage (${customerDto.page.lastPage})"><c:out value="${customerDto.page.lastPage}"></c:out></a>
					</c:if>
				</td>
			</tr>
		</tfoot>
	</table>
</body>

</html>