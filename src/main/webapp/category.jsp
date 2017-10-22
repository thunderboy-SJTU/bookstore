<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Bookstore</title>
	  <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
	 <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <script type="text/javascript"
	src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
	<style type="text/css">
		#fm{
			margin:0;
			padding:10px 30px;
		}
		.ftitle{
			font-size:14px;
			font-weight:bold;
			color:#666;
			padding:5px 0;
			margin-bottom:10px;
			border-bottom:1px solid #ccc;
		}
		.fitem{
			margin-bottom:5px;
		}
		.fitem label{
			display:inline-block;
			width:80px;
		}
	</style>
	<script type="text/javascript" src="jquery-2.1.0.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
        <script type="text/javascript">
		var url;
		function newCategory(){
                        
			$('#dlg').dialog('open').dialog('setTitle','New Category');                    
			$('#fm').form('clear'); 
			url = 'addCategory.action';
		}
		function saveCategory(){
			$('#fm').form('submit',{
				url: url,
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result){
					var result = eval('('+result+')');
					if (result.success){
						$('#dlg').dialog('close');		// close the dialog
						$('#dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({
							title: 'Error',
							msg: result.msg
						});
					}
				}
			});
		}
		function removeCategory(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$.messager.confirm('Confirm','Are you sure you want to remove this category?',function(r){
					if (r){
						$.post('removeCategory.action',{catname:row.catname},function(result){
							if (result.success){
								$('#dg').datagrid('reload');	// reload the user data
							} else {
								$.messager.show({	// show error message
									title: 'Error',
									msg: result.msg
								});
							}
						},'json');
					}
				});
			}
		}
                
                function doSearch()
                {
                    $('#dg').datagrid('load',{
                        
			key: $('#key').val(),
		});
                }
	</script>
        </head>
<body>
	<h2>Categories</h2>
	<%
		String superuser = (String) request.getSession().getAttribute("superuser");
	%>
	<c:choose>
	<c:when test="${superuser == null }">
	<p>You are not log in!</p>
	</c:when>
	<c:otherwise>
	<div class="category-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>You can modify category information here.</div>
	</div>
	
	<table id="dg" title="Categories" class="easyui-datagrid" style="width:700px;height:250px"
			url="showCategory.action"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="catid" width="50">Catid</th>
				<th field="catname" width="50">Catname</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCategory()">New Category</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeCategory()">Remove Category</a>
                <span>Key:</span>
		<input id="key" class="easyui-validatebox" >
		<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">Add Category</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>category:</label>
				<input id="catname" name="catname" class="easyui-validatebox" required="true">
			</div>
		</form>
              
        </div>
          <div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCategory()">Save</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	</c:otherwise>
	</c:choose>
</body>
</html>