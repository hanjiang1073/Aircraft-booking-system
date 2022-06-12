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
	<title>飞机管理</title>
</head>
	<div class="condition">
		<form action="${basePath}Airplane?method=queryAirplane" method="post">
			飞机编号:<input type="text" name="pno">
			所属公司:<input type="text" name="cname">
			飞机型号:<input type="text" name="ptype">
			<button class="submit" type="submit">查询</button>
			<button type="button" onclick="window.location.href='AddAirplane.jsp'">添加</button>
		</form>
		
	</div>
	<table class="tablelist">
		<thread>
			<tr>
				<th>飞机编号</th>
				<th>所属公司</th>
				<th>飞机型号</th>
				<th>飞机容量</th>
				<th>操作</th>
			</tr>
		</thread>
		<c:forEach items="${airplaneList}" var="airplane">
		<tr>
			<td>${airplane.pno}</td>
			<td>${airplane.cname}</td>
			<td>${airplane.ptype}</td>
			<td>${airplane.capcity}</td>
			<td>
				<button class="edit" type="button" onclick="window.location.href='${basePath}Airplane?method=updateAirplane&pno=${airplane.pno}'">
					修改
				</button>
				<button class="remove" type="button" onclick="alert('确认删除？');window.location.href='${basePath}Airplane?method=deleteAirplane&pno=${airplane.pno}'">
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
			window.location.href="Airplane?method=listAirplane&pageNo="+page;
		}
	</script>
<body>
</body>
</html>