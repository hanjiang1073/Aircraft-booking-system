<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${basePath}css/ManageStyles.css"/>
	<script src="${basePath}js/jquery-3.6.0.js" type="text/javascript"></script>
	<script type="text/javascript">
		
	</script>
	<title>订单操作</title>
</head>
<%--
	<div class="condition">
		<form action="${basePath}User?method=queryUser" method="post">
			用户ID:<input type="text" name="userID">
			用户名:<input type="text" name="userName">
			用户类型:<input type="text" name="role">
			<button class="submit" type="submit">查询</button>
		</form>
		
	</div>
--%>
	<table class="tablelist">
		<thread>
			<tr>
				<th>订单号</th>
				<th>用户ID</th>
				<th>航班编号</th>
				<th>价格</th>
				<th>乘机人</th>
				<th>乘机人信息</th>		
				<th>操作</th>
			</tr>
		</thread>
		<c:forEach items="${orderList}" var="order">
		<tr>
			<td>${order.orderID}</td>
			<td>${order.userID}</td>
			<td>${order.fno}</td>
			<td>${order.price}</td>
			<td>${order.pName}</td>
			<td>${order.pIDNumber}</td>
			<td>
				<button class="edit" type="button" onclick="window.location.href='${basePath}Orders?method=details&orderID=${order.orderID}'">
					详情
				</button>
				<button class="remove" type="button" onclick="alert('确认删除？');window.location.href='${basePath}Orders?method=deleteOrder&userID=${order.userID}&orderID=${order.orderID}&fno=${order.fno}'">
					删除
				</button>
			</td>
		</tr>
		</c:forEach>
	</table>
	<table class="page">
		<td>
			<button type="button" onclick="javascript:go(1,${userID});">首页</button>
			<button type="button" onclick="javascript:go(${prePage},${userID})">上一页</button>
			<button type="button" onclick="javascript:go(${nextPage},${userID})">下一页</button>
			<button type="button" onclick="javascript:go(${totalPage},${userID})">尾页</button>
			总记录条数${totalCount}条  当前${pageNo}/${totalPage}页 每${pageSize}条数据一页 
		</td>
	</table>
	<script type="text/javascript">
		function go(page,userID){
			window.location.href="${basePath}Orders?method=list&userID="+userID+"&pageNo="+page;

		}
	</script>
<body>
</body>
</html>