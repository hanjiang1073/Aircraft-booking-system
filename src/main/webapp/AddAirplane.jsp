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
<title>Insert title here</title>
</head>
<body>
	<div class="add">
		<div class="title">添加飞机</div>
		<form action="${basePath}Airplane?method=addAirplane" method="post">
			<table class="table_add" style="width:50%;">
				
				<tr>
					<td>飞机编号</td>
					<td><input type="text" name="pno"></td>
					<td>所属公司</td>
					<td><input type="text" name="cname"></td>
				</tr>
				<tr>
					<td>飞机型号</td>
					<td><input type="text" name="ptype"></td>
					<td>飞机容量</td>
					<td><input type="text" name="capcity"></td>
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