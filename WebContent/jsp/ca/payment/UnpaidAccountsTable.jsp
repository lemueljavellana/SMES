<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include.jsp" %>
<table width="100%" bordercolor="black" border="1" id="customerAccount">
	<thead>
		<tr>
			<th width="1%">#</th>
			<th width="1%"><input type="checkbox" id="selectAll" name="selectAll"/></th>
			<th width="7%">Date</th>
			<th width="7%">Due Date</th>
			<th width="8%">Reference Number</th>
			<th width="28%%">Description</th>
			<th width="12%">Amount</th>
			<th width="12%">Interest Earned</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="account" items="${accounts.data}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td align="center"><input type="checkBox"
						value="${account.accountId}" id="cb" name="cb" /></td>
					<td> <c:out value="${account.formattedAccountDate}"/></td>
					<td> <c:out value="${account.formattedDueDate}"/></td>
					<td> <c:out value="${account.referenceNumber}"/></td>
					<td align="right"><c:out value="${account.description}"/></td>
					<td align="right"><c:out value="${account.formattedAmount}"/></td>
					<td align="right"><c:out value="${account.formattedEarnedInterest}"/></td>
				</tr>
			</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td>${accounts.dataSize + ((accounts.currentPage - 1) * accounts.pageSetting.maxResult)}/${accounts.totalRecords}</td>
			<td colspan="8" align="right">
				<c:if test="${(accounts.currentPage == accounts.lastPage 
								|| accounts.currentPage == accounts.lastPage - 1) 
								&& (accounts.currentPage != 1 && accounts.currentPage != 2)}">
					<a href="#" onclick="goToPage (1)" class="pageNumber">1</a>
					<a href="#" onclick="goToPage (2)" class="pageNumber">2</a>
					..
				</c:if>
				<c:if test="${accounts.prevPage >= 1 }">
					<a href="#" onclick="goToPage (${accounts.prevPage})" class="pageNumber"><c:out value="${accounts.prevPage}"/></a>
				</c:if>
				<a class="currentPage"><c:out value="${accounts.currentPage}"/></a>
				<c:if test="${accounts.nextPage <= accounts.lastPage}">
					<a href="#" onclick="goToPage (${accounts.nextPage})" class="pageNumber"><c:out value="${accounts.nextPage}"/></a>
				</c:if>
				<c:if test="${accounts.nextPage < accounts.lastPage}">
					...
					<c:if test="${accounts.lastPage - 1 != accounts.nextPage}">
						<a href="#" class="pageNumber" onclick="goToPage (${accounts.lastPage - 1})"><c:out value="${accounts.lastPage - 1}"></c:out></a>
					</c:if>
					<a href="#" class="pageNumber" onclick="goToPage (${accounts.lastPage})"><c:out value="${accounts.lastPage}"></c:out></a>
				</c:if>
			</td>
		</tr>
	</tfoot>
</table>
