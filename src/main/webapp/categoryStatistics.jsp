<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>Bookstore</title>
 <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
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
<script type="text/javascript">
	var url;

	function doSearch() {
		$('#dg').datagrid('load', {
			key : $('#key').val()
		});
	}
</script>
</head>
<body>
	<h2>Category Sales Statistics</h2>
	<%
		String superuser = (String) request.getSession().getAttribute("superuser");
	%>
	<c:choose>
	<c:when test="${superuser == null }">
	<p>You are not log in!</p>
	</c:when>
	<c:otherwise>
	<div class="categoryStatistic-info" style="margin-bottom: 10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>You can see category sales statistics here.</div>
	</div>

	<table id="dg" title="Category Sale Statistics" class="easyui-datagrid"
		style="width: 700px; height: 250px" url="showCategoryStatistic.action"
		toolbar="#toolbar" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="key" width="50">Category</th>
				<th field="amount" width="50">Amount</th>
				<th field="quantity" width="50">Quantity</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		 <span>&nbsp;&nbsp;Key:</span> <input
			id="key" class="easyui-validatebox"> <a href="#"
			class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
	</div>
    </c:otherwise>
    </c:choose>
</body>
</html>