<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${basePath}css/ManageStyles.css"/>
<script src="${basePath}js/jquery-3.6.0.js" type="text/javascript"></script>
<script type="text/javascript">
	
</script>
<title>AddUser</title>
</head>
<body>
	<div class="add">
		<div class="title">添加用户</div>
		<form action="${basePath}User?method=addUser" method="post">
			<table class="table_add" style="width:50%;">
				
				<tr>
					<td>用户ID</td>
					<td><input type="text" name="userID" readonly="readonly"></td>
					<td>用户名</td>
					<td><input type="text" name="userName"></td>
				</tr>
				<tr>
					<td>手机号</td>
					<td><input type="text" name="phoneNumber"></td>
					<td>身份证号</td>
					<td><input type="text" name="IDNumber"></td>
				</tr>
				<tr>
					<td>用户类型</td>
					<td><input type="text" name="role"></td>
					<td>密码</td>
					<td><input type="text" name="password"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<button class="submit" type="submit">
							确定
						</button>
						<button class="exit" type="button" onclick="window.history.go(-1);">
							返回
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>