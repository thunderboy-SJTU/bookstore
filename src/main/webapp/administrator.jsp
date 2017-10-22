<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>Bookstore</title>
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/color.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/demo/demo.css">
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
	function addTab(title, url) {
		if ($('#tt').tabs('exists', title)) {
			$('#tt').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0" src="'
					+ url + '"  style="width:100%;height:100%; "></iframe>';
			$('#tt').tabs('add', {
				title : title,
				content : content,
				closable : true
			});
		}
	}

	function submit() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg').dialog('close');// close the dialog
					window.location.reload();
				} else {
					$.messager.show({
						title : 'Error',
						msg : result.msg
					});
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="easyui-layout" style="width: 1500px; height: 800px;">
		<div region="west" split="true" title="Items" style="width: 200px;">
			<p style="padding: 5px; margin: 0;">Select:</p>
			<ul>
				<li><a href="javascript:void(0)"
					onclick="addTab('user', 'user.jsp')">User</a></li>
				<li><a href="javascript:void(0)"
					onclick="addTab('order', 'order.jsp')">Order</a></li>
				<li><a href="javascript:void(0)"
					onclick="addTab('book', 'book.jsp')">Book</a></li>
				<li><a href="javascript:void(0)"
					onclick="addTab('category', 'category.jsp')">Category</a></li>
				<li>Statistics
					<ul>
						<li><a href="javascript:void(0)"
					onclick="addTab('userStatistics', 'userStatistics.jsp')">By User</a></li>
						<li><a href="javascript:void(0)"
					onclick="addTab('dateStatistics', 'dateStatistics.jsp')">By Date</a></li>
					<li><a href="javascript:void(0)"
					onclick="addTab('bookStatistics', 'bookStatistics.jsp')">By Book</a></li>
					<li><a href="javascript:void(0)"
					onclick="addTab('categoryStatistics', 'categoryStatistics.jsp')">By Category</a></li>
					</ul>
				</li>
				<li><a href="javascript:void(0)"
					onclick="addTab('user mode', 'index.jsp')">User mode</a></li>
			</ul>
		</div>
		<div id="tt" region="center" class="easyui-tabs" title="Operation"
			style="width: 400px; height: 250px;">
			<div title="Home">
				<p style="padding: 5px; margin: 0; font-size: 18px;">Welcome to
					bookstore management system!</p>
				<%
					String superuser = (String) request.getSession().getAttribute("superuser");
				%>
				<c:if test="${superuser == null}">
					<a href="#" class="easyui-linkbutton" plain="true"
						onclick="window.location.href='login.jsp'">Log in</a>
				</c:if>
				<c:if test="${superuser != null}">
					<a href="#" class="easyui-linkbutton" plain="true"
						onclick="window.location.href='logout.action'">Log out</a>
				</c:if>
			</div>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">User Information</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>Username:</label> <input id="username" name="username"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Password:</label> <input name="password"
					class="easyui-validatebox" required="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="submit()">submit</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
</body>
</html>
