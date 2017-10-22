<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="http://www.jeasyui.com/easyui/datagrid-detailview.js"></script>

<script type="text/javascript">
	var url;
	function removeOrder() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm',
					'Are you sure you want to remove this order?', function(r) {
						if (r) {
							$.post('removeOrder.action', {
								orderid : row.orderid
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
	<h2>Order</h2>
	<%
		String superuser = (String) request.getSession().getAttribute("superuser");
	%>
	<c:choose>
		<c:when test="${superuser == null }">
			<p>You are not log in!</p>
		</c:when>
		<c:otherwise>
			<div class="order-info" style="margin-bottom: 10px">
				<div class="demo-tip icon-tip">&nbsp;</div>
				<div>You can check and remove orders here. You could click
					left grid to show detail information!</div>
			</div>

			<table id="dg" title="My Orders" class="easyui-datagrid"
				style="width: 700px; height: 250px" url="showOrder.action"
				toolbar="#toolbar" pagination="true" fitColumns="true"
				singleSelect="true">
				<thead>
					<tr>
						<th field="orderid" width="50">Orderid</th>
						<th field="username" width="50">Username</th>
						<th field="amount" width="50">Amount</th>
						<th field="all_quantity" width="50">Total</th>
						<th field="datetime" width="50">Date</th>
					</tr>
				</thead>
			</table>
			<div id="toolbar">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
					plain="true" onclick="removeOrder()">Remove Order</a> <span>Key:</span>
				<input id="key" class="easyui-validatebox"> <a href="#"
					class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
			</div>
			<script type="text/javascript">
				$('#dg')
						.datagrid(
								{
									view : detailview,
									detailFormatter : function(index, row) {
										return '<div style="padding:2px"><table class="ddv"></table></div>';
									},
									onExpandRow : function(index, row) {
										var ddv = $(this).datagrid(
												'getRowDetail', index).find(
												'table.ddv');
										ddv
												.datagrid({
													url : 'showOrderDetail.action?orderid='
															+ row.orderid,
													fitColumns : true,
													singleSelect : true,
													rownumbers : true,
													loadMsg : '',
													height : 'auto',
													columns : [ [ {
														field : 'isbn',
														title : 'ISBN',
														width : 100
													}, {
														field : 'title',
														title : 'Title',
														width : 100
													}, {
														field : 'author',
														title : 'Author',
														width : 100
													}, {
														field : 'category',
														title : 'Category',
														width : 100
													}, {
														field : 'price',
														title : 'Price',
														width : 100
													}, {
														field : 'quantity',
														title : 'Quantity',
														width : 100
													}, {
														field : 'amount',
														title : 'Amount'
													} ] ],
													onResize : function() {
														$('#dg')
																.datagrid(
																		'fixDetailRowHeight',
																		index);
													},
													onLoadSuccess : function() {
														setTimeout(
																function() {
																	$('#dg')
																			.datagrid(
																					'fixDetailRowHeight',
																					index);
																}, 0);
													}
												});
										$('#dg').datagrid('fixDetailRowHeight',
												index);
									}
								});
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>