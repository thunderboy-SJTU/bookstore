<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List"%>
<%@ page import="bookstore3.Entity.Book"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<sql:query var="ct" dataSource="jdbc/bookstore">
select isbn, title, price from books natural join categories order by isbn
</sql:query>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="my.css" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/color.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/demo/demo.css">
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	color: #666;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		var url;
		function register(){                        
			$('#dlg').dialog('open').dialog('setTitle','Register');
			url = 'register.action';
		}
		
		function save(){
			$('#fm').form('submit',{
				url: url,
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result){
					var result = eval('('+result+')');
					if (result.success){
						$('#dlg').dialog('close');		// close the dialog
						window.location.href="index.jsp";
					} else {
						$.messager.show({
							title: 'Error',
							msg: result.msg
						});
					}
				}
			});
		}
		</script>
<script> 
</script>
<title>Bookstore</title>
</head>
<body>
	<script type="text/javascript">
		var xmlhttp;
		function getDetail() {
			
			if (window.XMLHttpRequest) {
				xmlhttp = new XMLHttpRequest();
			} else {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
				var book = document.getElementsByName("books");
				var isbn;
				for(var i = 0; i < book.length; i++)
				{
				     if(book[i].checked)
				     isbn = book[i].value;
				}
				if (isbn == "") {
					document.getElementById("detail").innerHTML = "";
					return;
				}
				xmlhttp.open("GET", "showDetail.action?isbn="+isbn, true);
				xmlhttp.onreadystatechange = ajaxCall;
				xmlhttp.send();
			
		}
		
		function ajaxCall() {
			if(xmlhttp.readyState == 4 ) {
			if(xmlhttp.status == 200) {
			var text = xmlhttp.responseText;
			var obj=eval("("+text+")");
			document.getElementById("detail").innerHTML =
			"<h2>Book Detail</h2>" + "<table border='0' cellspacing='0' class='gridtable'><tr><th>ISBN</th><th>Title</th><th>Author</th><th>Price</th><th>Category</th><th>Description</th>"+"<tr><td>" + obj.isbn + "</td><td>" + obj.title +"</td><td>"+obj.author+"</td><td>"+obj.price+"</td><td>"+obj.catname+"</td><td>"+obj.description+"</td></tr></table>";
			}
			}
			} 
	</script>
	<div id="head">
	<h1>Bookstore</h1>
	<%
		String user = (String) request.getSession().getAttribute("user");
	%>
	<c:if test="${user == null}">
		<table>
			<tr>
				<td width=500px></td>
				<td width=500px></td>
				<td><a href="#" class="easyui-linkbutton" plain="true"
					onclick="window.location.href='login.jsp'">Sign in</a></td>
				<td><a href="#" class="easyui-linkbutton" plain="true"
					onclick="register()">Sign up</a></td>
		</table>
	</c:if>
	<c:if test="${user!=null}">
		<table>
			<tr>
				<td width=500px></td>
				<td width=500px></td>
				<td><a href="#" class="easyui-linkbutton" plain="true"
					onclick="window.location.href='profile.jsp'">My Profile</a></td>
				<td><a href="#" class="easyui-linkbutton" plain="true"
					onclick="window.location.href='logout'">Sign out</a></td>
			</tr>
		</table>
		</div>
		<div id="search">
	</c:if>
	<form method=POST action="search.action">
		<table border="0">
			<tr>
				<td>Search:</td>
				<td><input type='text' name='key' id='text' /></td>
				<td width=50px>&nbsp;</td>
				<td><input type='submit' name='submit' value='Submit'
					id='submit' /></td>
			</tr>
		</table>
	</form>
    </div>
    <div id="detail"></div>
    <div id= "table">
	<table border='0' cellspacing='0' class="gridtable">
		<form method=POST action=addToCart.action id="detailform">
			<tr>&nbsp;
			</tr>
			<tr>&nbsp;
			</tr>
			<tr>&nbsp;
			</tr>
			<tr>
				<th></th>
				<th>ISBN</th>
				<th>Title</th>
				<th>Price</th>
			</tr>
			<%
				List<Book> list = (List<Book>) request.getAttribute("list");
			%>
			<c:if test="${list == null}">
				<c:forEach var="row" items="${ct.rows}">
					<tr>
						<td><input type="radio" name="books" value="${row.isbn}"></td>
						<td>${row.isbn}</td>
						<td>${row.title}</td>
						<td><fmt:formatNumber pattern="#0.00" value="${row.price}" /></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${list!= null}">
				<tr>
					<c:forEach var="row2" items="${list}">
						<tr>
							<td><input type="radio" name="books" value="${row2.isbn}"
								></td>
							<td>${row2.isbn}</td>
							<td>${row2.title}</td>
							<td><fmt:formatNumber pattern="#0.00" value="${row2.price}" /></td>
						</tr>
					</c:forEach>
				</tr>
			</c:if>
	</table>
	<table>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>&nbsp;
		</tr>
		<tr>
			<td width="600px" align=center><input type="submit" name="add"
				value="Add to Cart" id="submit"></td>
			<td width="600px" align=center><input type="button"
				name="detail" value="Show details" onclick="getDetail()" id="submit"></td>
			</form>
			<form method=POST action="cart.jsp">
				<td width=600px align=center><input type="submit" name="show"
					value="Show Cart" id="submit"></td>
		</tr>
		</form>
	</table>
	</div>
	<script>
  var message ='<%=request.getAttribute("message")%>';
		if (message != 'null') {
			alert(message);
		}
	</script>


	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">Register</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>Username:</label> <input id="username" name="username"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Password:</label> <input name="password"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Name:</label> <input name="name" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>Address:</label> <input name="address"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>City:</label> <input name="city" class="easyui-validatebox"
					required="true">
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="save()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>

</body>
</html>



