<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品列表</title>
</head>
<body>
<table>
	<tr>
		<td>商品id</td>
		<td>商品名称</td>
		<td>商品价格</td>
		<td>商品百分比</td>
	</tr>
	<c:forEach items="${list }" var="g">
		<tr>
			<td>${g.id }</td>
			<td>${g.name }</td>
			<td>${g.price }</td>
			<td>${g.baifen }</td>
		</tr>
	</c:forEach>
</table>
	<a href="${pageContext.request.contextPath }/findAll?page=${prePage}">上一页</a>
	<a href="${pageContext.request.contextPath }/findAll?page=${nextPage}">下一页</a>
</body>
</html>