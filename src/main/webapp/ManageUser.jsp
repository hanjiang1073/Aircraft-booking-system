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
	<title>用户管理</title>
</head>
	<div class="condition">
		<form action="${basePath}User?method=queryUser" method="post">
			用户ID:<input type="text" name="userID">
			用户名:<input type="text" name="userName">
			用户类型:<input type="text" name="role">
			<button class="submit" type="submit">查询</button>
			<button type="button" onclick="window.location.href='AddUser.jsp'">添加</button>
		</form>
		
	</div>
	<table class="tablelist">
		<thread>
			<tr>
				<th>用户ID</th>
				<th>用户名称</th>
				<th>手机号</th>
				<th>身份证号</th>
				<th>用户类型</th>
				<th>密码</th>
				<th>操作</th>
			</tr>
		</thread>
		<c:forEach items="${userList}" var="user">
		<tr>
			<td>${user.userID}</td>
			<td>${user.userName}</td>
			<td>${user.phoneNumber}</td>
			<td>${user.IDNumber}</td>
			<td>${user.role}</td>
			<td>${user.password}</td>
			<td>
				<button class="edit" type="button" onclick="window.location.href='${basePath}User?method=updateUser&userID=${user.userID}'">
					修改
				</button>
				<button class="remove" type="button" onclick="alert('确认删除？');window.location.href='${basePath}User?method=deleteUser&userID=${user.userID}'">
					删除
				</button>
			</td>
		</tr>
		</c:forEach>
	</table>
	<table class="page">
		<td>
			<button type="button" onclick="javascript:go(1);">首页</button>
			<button type="button" onclick="javascript:go(${prePage})">上一页</button>
			<button type="button" onclick="javascript:go(${nextPage})">下一页</button>
			<button type="button" onclick="javascript:go(${totalPage})">尾页</button>
			总记录条数${totalCount}条 当前${pageNo}/${totalPage}页 每${pageSize}条数据一页 
		</td>
	</table>
	<script type="text/javascript">
		function go(page){
			window.location.href="User?method=listUser&pageNo="+page;
		}
	</script>
<body>
</body>
</html>