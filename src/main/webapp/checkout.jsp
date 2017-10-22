<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="bookstore3.Entity.Order"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="my.css" type="text/css" />
<title>CheckOut</title>
</head>
<body>
<h1>Order</h1>
<% String user =(String)request.getSession().getAttribute("user"); %>
<c:if test="${user == null}">
<p>You are not log in!</p>
<form method = POST action="login.jsp">
<input type="submit" name="login" value="Log in" id="submit">
</form>
</c:if>
<c:if test="${user!=null}">
<%Order order = (Order)request.getSession().getAttribute("order"); %>
<c:choose>
<c:when test="${order == null || order.isEmpty()}">
<p>There are no orders!</p>
<form method = POST action="index.jsp">
<input type="submit" name="return" value="Return" id="submit">
</form>
</c:when>
<c:otherwise>
<table>
<tr><td width=200px>User:<c:out value="${order.user.username}"/></td><td  width=200px>Date:<c:out value="${order.date}"/></td></tr>
</table>
<table class='gridtable'>
<tr>
<th>Isbn</th><th>Title</th><th>Author</th><th>Category</th><th>Price</th><th>Quantity</th><th>Total</th>
</tr>
<c:forEach var="ct" items="${order.items}">
<tr>
<td>${ct.book.isbn}</td>
<td>${ct.book.title}</td>
<td>${ct.book.author}</td>
<td>${ct.book.category.catname}</td>
<td><fmt:formatNumber pattern="#0.00" value="${ct.book.price}"/></td>
<td><input type="text" name="${ct.book.isbn}" value="${ct.quantity}" style='text-align:right' id="text2"></td>
<td><fmt:formatNumber pattern="#0.00" value="${ct.book.price*ct.quantity}"/></td>
</tr>
</c:forEach>
<tr><th></th><th></th><th></th><th></th><th></th><th><c:out value="${order.quantity}"/></th><th><fmt:formatNumber pattern="#0.00" value="${order.amount}"/></th>
</table>
<table>
<tr>&nbsp;</tr>
<tr>&nbsp;</tr>
<tr>&nbsp;</tr>
<% request.getSession().removeAttribute("order"); %>
<form method = POST action="index.jsp">
<tr>
<td width=2000px align=center><input type="submit" name="return" value="Return" id="submit"></td>
</tr>
</form>
</table>
</c:otherwise>
</c:choose>
</c:if>
</body>
</html>