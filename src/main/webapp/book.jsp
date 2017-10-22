<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	src="http://code.jquery.com/jquery-1.6.min.js"></script>
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
<script type="text/javascript" src="jquery-2.1.0.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var url;
	function newBook() {

		$('#dlg').dialog('open').dialog('setTitle', 'New Book');
		$('#isbn').attr('readonly', false);
		$('#fm').form('clear');
		url = 'addBook.action';
	}
	function editBook() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit Book');
			$("#isbn").attr("readonly", true);
			$('#fm').form('load', row);
			url = 'editBook.action';
		}
	}
	function saveBook() {
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
	function removeBook() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm',
					'Are you sure you want to remove this book?', function(r) {
						if (r) {
							$.post('removeBook', {
								isbn : row.isbn
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
	<h2>Books</h2>
	<%
		String superuser = (String) request.getSession().getAttribute("superuser");
	%>
	<c:choose>
	<c:when test="${superuser == null }">
	<p>You are not log in!</p>
	</c:when>
	<c:otherwise>
			<div class="book-info" style="margin-bottom: 10px">
				<div class="demo-tip icon-tip">&nbsp;</div>
				<div>You can modify book information here.</div>
			</div>

			<table id="dg" title="My Books" class="easyui-datagrid"
				style="width: 700px; height: 250px" url="showBook.action" toolbar="#toolbar"
				pagination="true" rownumbers="true" fitColumns="true"
				singleSelect="true">
				<thead>
					<tr>
						<th field="isbn" width="50">ISBN</th>
				<th field="title" width="50">Title</th>
                                <th field="author" width="50">Author</th>
				<th field="price" width="50">Price</th>				
                                <th field="catname" width=50">Category</th>
                                <th field="description" width="50">Description</th>
					</tr>
				</thead>
			</table>
			<div id="toolbar">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="newBook()">New Book</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-edit" plain="true"
					onclick="editBook()">Edit Book</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-remove" plain="true"
					onclick="removeBook()">Remove Book</a> <span>Key:</span> <input
					id="key" class="easyui-validatebox"> <a href="#"
					class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
			</div>

			<div id="dlg" class="easyui-dialog"
				style="width: 400px; height: 280px; padding: 10px 20px"
				closed="true" buttons="#dlg-buttons">
				<div class="ftitle">Book Information</div>
				<form id="fm" method="post" novalidate>
					<div class="fitem">
						<label>ISBN:</label> <input id="isbn" name="isbn"
							class="easyui-validatebox" required="true">
					</div>
					<div class="fitem">
						<label>Title:</label> <input name="title"
							class="easyui-validatebox" required="true">
					</div>
					<div class="fitem">
						<label>Author:</label> <input name="author"
							class="easyui-validatebox" required="true">
					</div>
					<div class="fitem">
						<label>Price:</label> <input name="price"
							class="easyui-validatebox" required="true">
					</div>
					<div class="fitem">
						<label>Category:</label> <input name="catname"
							class="easyui-validatebox" required="true">
					</div>
					<div class="fitem">
						<label>Description:</label> <input name="description"
							class="easyui-validatebox">
					</div>
				</form>
			</div>
			<div id="dlg-buttons">
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
					onclick="saveBook()">Save</a> <a href="#" class="easyui-linkbutton"
					iconCls="icon-cancel"
					onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
			</div>

			</c:otherwise>
			</c:choose>
</body>
</html>