<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Search</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<base href="<%=basePath%>">
	<link rel="SHORTCUT ICON" href="images/airplane.icon"/>
	
	<link rel="stylesheet" href="css/cube.css">
	<link rel="SHORTCUT ICON" href="images/airplane.icon"/>
	<link rel="stylesheet" type="text/css" href="css/absBase.css">
	<link rel="stylesheet" type="text/css" href="css/search.css">
	<script type="text/javascript" src="js/search.js"></script>
>
</head>
<body>

	<div class="s_box">
		<form action="search" class="s_box_container">
			
			
			<div class="s_row">
				<span class="s_label">出发城市</span>
				<!-- <input class="s_text" type="text" name="departureCity"> -->
				<select class="s_text" name="departureCity">
				  <option></option>
				  <option value ="武汉">武汉</option>
				  <option value ="北京">北京</option>
				  <option value ="上海">上海</option>
				  <option value ="广州">广州</option>
				  <option value ="深圳">深圳</option>
				  <option value ="成都">成都</option>
				  <option value ="沈阳">沈阳</option>
				  <option value ="拉萨">拉萨</option>
				</select>
				
				<span class="s_label">出发日期</span>
				<input id="dDate" class="s_text " type="text" name="departureDate">
			</div>
			<div class="s_row">
				<span class="s_label">到达城市</span>
				<!-- <input class="s_text" type="text" name="arrivalCity"> -->
				<select class="s_text" name="arrivalCity">
				  <option></option>
				  <option value ="北京">北京</option>
				  <option value ="武汉">武汉</option>
				  <option value ="上海">上海</option>
				  <option value ="广州">广州</option>
				  <option value ="深圳">深圳</option>
				  <option value ="成都">成都</option>
				  <option value ="沈阳">沈阳</option>
				  <option value ="拉萨">拉萨</option>
				</select>
				
				<span class="s_label">返回日期</span>
				<input id="aDate" class="s_text" type="text" name="returnDate" id="s_returnDate" disabled="disabled">
			</div>

			<div class="s_row">
				<input  class="s_submit" type="submit" value="搜索机票" >
			</div>
			<div class="s_row">
				<input name="userID" type="hidden" value=${userID}>
				<input name="method0" type="hidden" value="toSearchFlight">
			</div>
		</form>
	</div>
	
</body>
</html>