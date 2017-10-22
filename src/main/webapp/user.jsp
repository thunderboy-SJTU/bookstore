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
	function newUser() {

		$('#dlg').dialog('open').dialog('setTitle', 'New User');
		$('#username').attr('readonly', false);
		$('#fm').form('clear');
		url = 'addUser.action';
	}
	function editUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
			$("#username").attr("readonly", true);
			$('#fm').form('load', row);
			url = 'editUser.action';
		}
	}
	function saveUser() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				} else {
					$.messager.show({
						title : 'Error',
						msg : result.msg
					});
				}
			}
		});
	}
	function removeUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm',
					'Are you sure you want to remove this user?', function(r) {
						if (r) {
							$.post('removeUser.action', {
								username : row.username
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the user data
								} else {
									$.messager.show({ // show error message
										title : 'Error',
										msg : result.msg
									});
								}
							}, 'json');
						}
					});
		}
	}

	function doSearch() {
		$('#dg').datagrid('load', {
			key : $('#key').val()
		});
	}
</script>
</head>
<body>
	<h2>User</h2>
	<%
		String superuser = (String) request.getSession().getAttribute("superuser");
	%>
	<c:choose>
	<c:when test="${superuser == null }">
	<p>You are not log in!</p>
	</c:when>
	<c:otherwise>
	
	<div class="user-info" style="margin-bottom: 10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>You can modify user information here.</div>
	</div>

	<table id="dg" title="My Users" class="easyui-datagrid"
		style="width: 700px; height: 250px" url="showUser.action"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="username" width="50">Username</th>
				<th field="password" width="50">Password</th>
				<th field="name" width="50">Name</th>
				<th field="address" width="50">Address</th>
				<th field="city" width=50">City</th>
				<th field="authority" width="50">Authority</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">New User</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editUser()">Edit User</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="removeUser()">Remove User</a> <span>Key:</span> <input
			id="key" class="easyui-validatebox"> <a href="#"
			class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
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
			</div>
			<div class="fitem">
				<script>
					$.extend($.fn.validatebox.defaults.rules, {
						authority : {
							validator : function(value) {
								return (value == 0 || value == 1);
							},
							message : 'The authority should be 0 or 1'
						}
					});
				</script>
				<label>Authority:</label> <input name="authority"
					class="easyui-validatebox" required="true" validtype="authority">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveUser()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	</c:otherwise>
	</c:choose>
</body>
</html>
