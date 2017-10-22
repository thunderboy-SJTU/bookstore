<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.mongodb.*"%>
<%@ page import="com.mongodb.gridfs.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="my.css" type="text/css" />
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
<title>Profile</title>
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
<style>
.file-box {
	position: relative;
	width: 340px
}

.txt {
	height: 22px;
	border: 1px solid #cdcdcd;
	width: 180px;
}

.btn {
	background-color: #FFF;
	border: 1px solid #CDCDCD;
	height: 24px;
	width: 70px;
}

.file {
	position: absolute;
	top: 0;
	right: 80px;
	height: 24px;
	filter: alpha(opacity : 0);
	opacity: 0;
	width: 260px
}
</style>
<script type="text/javascript">
	var url;
	function rename(id, filename) {
		$('#dlg').dialog('open').dialog('setTitle', 'Rename');
		$('#fm').form('load', {
			filename : filename
		});
		url = 'renameProfile.action?id=' + id;
	}

	function save() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg').dialog('close'); // close the dialog
					window.location.href = "profile.jsp";
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
	<h1>Your profile</h1>
	<%
		String user = (String) request.getSession().getAttribute("user");
	%>
	<c:if test="${user == null}">
		<p>You are not log in!</p>
		<form method=POST action="login.jsp">
			<input type="submit" name="login" value="Log in" id="submit">
		</form>
	</c:if>
	<c:if test="${user!=null }">
		<div>
			<table>
				<tr>
					<td width=500px></td>
					<td width=500px></td>
					<td><a href="#" class="easyui-linkbutton" plain="true"
						onclick="window.location.href='index.jsp'">Return</a></td>
				</tr>
			</table>
		</div>
		<div class="file-box">

			<s:form action="addProfile" enctype="multipart/form-data"
				method="post">
				<input type='text' name='textfield' id='textfield' class='txt' />
				<input type='button' class='btn' value='Find...' />
				<s:file name="upload" class="file"
					onchange="document.getElementById('textfield').value=this.value" />
				<s:submit value="Upload" class="btn"></s:submit>
			</s:form>
		</div>
		<div>
			<p>Download Path:F:/profile</p>
		</div>
		<div id="Table">
			<%
				List<GridFSDBFile> list = (List<GridFSDBFile>) request.getAttribute("list");
					if (list == null) {
						Mongo mongo = new Mongo();
						DB db = mongo.getDB("test");
						String username = (String) request.getSession().getAttribute("user");
						GridFS gridFS = new GridFS(db, "profile");
						list = new ArrayList<GridFSDBFile>();
						DBCursor cur = gridFS.getFileList();
						while (cur.hasNext()) {
							GridFSDBFile dbFile = (GridFSDBFile) cur.next();
							if (dbFile.get("username").equals(username)) {
								list.add(dbFile);
							}
						}
					}
					out.println(
							"<table border='0' cellspacing='0' class='gridtable'><tr><th>File Name</th><th>Size</th><th>Operation</th>");
					for (int i = 0; i < list.size(); i++) {
						String filename = (String) list.get(i).get("filename");
						String id = list.get(i).get("_id").toString();
						out.println("<tr>");
						out.println("<td>" + filename + list.get(i).get("contentType") + "</td>" + "<td>"
								+ list.get(i).get("length") + "</td>"
								+ "<td><a href='#' class='easyui-linkbutton' plain='true' onclick=\"window.location.href='download.action?id="
								+ id
								+ "'\">Download</a> <a href='#' class='easyui-linkbutton' plain='true' onclick=\"window.location.href='removeProfile.action?id="
								+ id + "'\">Remove</a>"
								+ "<a href='#' class='easyui-linkbutton' plain='true' onclick=\"rename('" + id + "','"
								+ filename + "')\">Rename</a>");
						out.println("</tr>");
					}
					out.println("</table>");
			%>
		</div>
		<div id="search">
			<form method=POST action="showProfile.action">
				<table border="0">
					<tr>
						<td>Search:</td>
						<td><input type='text' name='key' id='text' /></td>
						<td><input type='submit' name='submit' value='Submit'
							class='btn' /></td>
					</tr>
				</table>
			</form>
		</div>

	</c:if>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">Rename</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>FileName:</label> <input id="filename" name="filename"
					class="easyui-validatebox" required="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="save()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>

</body>
</html>