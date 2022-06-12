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
	<link rel="stylesheet" href="${basePath}css/reception.css"/>
	<script src="${basePath}js/jquery-3.6.0.js" type="text/javascript"></script>
	<title>航班搜索</title>
</head>
   <form action="search" method="post" class="s_box_container">
	<div    class="condition" >
		出发城市:<input type="text" name="departureCity">
		到达城市:<input type="text" name="arrivalCity">
		出发时间:<input type="text" name="departureDate">
		<button>查询</button>
		
	</div>
	</form>
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
		<c:forEach items="${searchList}" var="flight">
		<tr>
			<td>${flight.fno}</td>
			<td>${flight.pno}</td>
			<td>${flight.departapname}</td>
			<td>${flight.arriveapname}</td>
			<td>${flight.departtime}</td>
			<td>${flight.arrivetime}</td>
			<td>${flight.price}</td>
			<td>
				<button class="reserve">
					预定
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