<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<title>Log in</title>
<link rel="stylesheet" href="my.css" type="text/css" />
 <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
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
</head>

<body id="body">
<%
		String user = (String) request.getSession().getAttribute("user");
	%>
	<c:choose>
		<c:when test="${user!= null}">
			<%
				response.sendRedirect("index.jsp");
			%>
		</c:when>
		<c:otherwise>
			<form id="login" method=POST action="login.action">
				<h1>Log In</h1>
				<fieldset id="inputs">
					<input id="username" name="username" type="text"
						placeholder="Username"> <input id="password"
						name="password" type="password" placeholder="Password">
				</fieldset>
				<fieldset id="actions">
					<input type="submit" id="submit" value="Log in"> 
                    <a href="#" class="easyui-linkbutton"  plain="true" onclick="register()">Register</a>
				</fieldset>
				<a href="index.jsp" id="back">Back</a>
			</form>
	<script>
     var errori = '<%= request.getAttribute("message") %>';
     if(errori!= "null"){
	   alert(errori);
  }
  </script>
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">Register</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>Username:</label>
				<input id="username" name="username" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Password:</label>
				<input name="password" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Name:</label>
				<input name="name" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Address:</label>
				<input name="address" class="easyui-validatebox" required="true">
			</div>
                        <div class="fitem">
				<label>City:</label>
				<input name="city" class="easyui-validatebox" required="true">
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">Save</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	</c:otherwise>
	</c:choose>	
</body>
</html>
