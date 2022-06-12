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
	<title>航班管理</title>
</head>
	<div class="condition">
		<form action="${basePath}Flight?method=queryFlight" method="post">
			航班号:<input type="text" name="fno">
			出发机场:<input type="text" name="departapname">
			目的机场:<input type="text" name="arriveapname">
			出发时间:<input type="text" name="departtime">
			<button class="submit" type="submit">查询</button>
			<button type="button" onclick="window.location.href='AddFlight.jsp'">添加</button>
		</form>
		
	</div>
	<table class="tablelist">
		<thread>
			<tr>
				<th>航班编号</th>
				<th>飞机编号</th>
				<th>出发机场</th>
				<th>目的机场</th>
				<th>出发时间</th>
				<th>到达时间</th>
				<th>价格</th>
				<th>操作</th>
			</tr>
		</thread>
		<c:forEach items="${flightList}" var="flight">
		<tr>
			<td>${flight.fno}</td>
			<td>${flight.pno}</td>
			<td>${flight.departapname}</td>
			<td>${flight.arriveapname}</td>
			<td>${flight.departtime}</td>
			<td>${flight.arrivetime}</td>
			<td>${flight.price}</td>
			<td>
				<button class="edit" type="button" onclick="window.location.href='${basePath}Flight?method=updateFlight&fno=${flight.fno}'">
					修改
				</button>
				<button class="remove" type="button" onclick="alert('确认删除？');window.location.href='${basePath}Flight?method=deleteFlight&fno=${flight.fno}'">
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
			window.location.href="Flight?method=listFlight&pageNo="+page;
		}
	</script>
<body>
</body>
</html>