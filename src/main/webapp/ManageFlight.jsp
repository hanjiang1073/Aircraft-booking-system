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
	<title>航班管理</title>
</head>
	<div class="condition">
		航班号:<input type="text">
		<button>查询</button>
		<button onclick="window.location.href='AddFlight.jsp'">添加</button>
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
				<button class="edit">
					修改
				</button>
				<button class="remove">
					删除
				</button>
			</td>
		</tr>
		</c:forEach>
	</table>
	<table class="page">
		<td>
			<button>首页</button>
			<button>上一页</button>
			<button>下一页</button>
			<button>尾页</button>
		</td>
	</table>
<body>
</body>
</html>