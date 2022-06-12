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
	<div class="update">
		<div class="title">修改航班</div>
		<form action="${basePath}Flight?method=submitUpdate" method="post">
			<table class="table_update" style="width:50%;">
				
				<tr>
					<td>航班编号</td>
					<td><input type="text" name="fno" value="${flight.fno}" readonly="readonly" ></td>
					<td>飞机编号</td>
					<td><input type="text" name="pno" value="${flight.fno}"></td>
				</tr>
				<tr>
					<td>出发机场</td>
					<td><input type="text" name="departapname" value="${flight.departapname}"></td>
					<td>目的机场</td>
					<td><input type="text" name="arriveapname" value="${flight.arriveapname}"></td>
				</tr>
				<tr>
					<td>出发时间</td>
					<td><input type="text" name="departtime" value="${flight.departtime}"></td>
					<td>到达时间</td>
					<td><input type="text" name="arrivetime" value="${flight.arrivetime}"></td>
				</tr>
				<tr>
					<td>航班价格</td>
					<td><input type="text" name="price" value="${flight.price}"></td>
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