<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
        
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>ABS_Booking</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<base href="<%=basePath%>">
	<link rel="SHORTCUT ICON" href="images/airplane.icon"/>
	
	<link rel="stylesheet" href="css/cube.css">
	<link rel="SHORTCUT ICON" href="images/airplane.icon"/>
	<link rel="stylesheet" type="text/css" href="css/absBase.css">
	<link rel="stylesheet" type="text/css" href="css/booking.css">
	<script type="text/javascript" src="js/booking.js"></script>
	
	
</head>
<body>
	<div class="abs_container clearfix b_container">

		<form action="OrdersServlet" method="post" class="b_form">
			<input type="hidden" name="userID" value=${userID}>
			<span class="b_form_title">乘客</span>

			<div id="b_formItem_container">
				<div class="b_form_item clearfix">
					<div class="bf_item_idArea">
						<span class="bf_item_id">1</span>
					</div>
						
					<div class="bf_item_textArea">
						<div class="insert_name">
							<span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
							
							<input type="text" name="name" onfocus="textOnFocus(this)" onblur="textOnBlur(this)">
							
						</div>
						<div class="insert_id">
							<span>身份证：</span>
							
							<input type="text" name="pIDNumber" onfocus="textOnFocus(this)" onblur="textOnBlur(this)">
							
						</div>
						<div class="bf_item_seatType">
							<span>座位偏好：</span>

							<label class="user_unSelect">
								<input class="bf_item_radioBut" type="radio" checked="checked" name="seatType1" value="default"/>默认
							</label>
							
							<label class="user_unSelect">
								<input class="bf_item_radioBut" type="radio" name="seatType1" value="windowSeat"/>靠窗
							</label>
							
							<label class="user_unSelect">
								<input class="bf_item_radioBut" type="radio" name="seatType1" value="middleSeat"/>中间
							</label>
							
							<label class="user_unSelect">
								<input class="bf_item_radioBut" type="radio" name="seatType1" value="aisleSeat"/>靠过道
							</label>
						</div>
					</div>
					<div class="bf_item_subtotalArea">
						<div class="bf_item_delete clearfix">
							<span onclick="deleteItem(this)">删除</span>
							<img src="images/close_icon.png" onclick="deleteItem(this)">
						</div>
						<div class="bf_item_subtotal">
							<c:forEach items="${flightInfoList}" var="flightInfo">
								<span>￥
									<fmt:formatNumber type="number" groupingUsed="false" value="${flightInfo.price}" />
								</span>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<!-- other form_item -->
			

			<div>
				<button class="b_form_addItem" type="button" onclick="addItem()">+ 添加乘客</button>
			</div>

			
			<c:forEach items="${flightInfoList}" var="flightInfo">
				<input type="hidden" name="flightInfoID" value="${flightInfo.fno}">
			</c:forEach>
				
			<div>
			  <c:forEach items="${flightInfoList}" var="flightInfo">
				<span>航班号：${flightInfo.fno}</span>
				<span>单价：${flightInfo.price}</span>
			    <input type="hidden" name="fno" value="${flightInfo.fno}">
			    <input type="hidden" name="price" value="${flightInfo.price}">
			  
			  </c:forEach>
				<input class="b_form_submit" type="submit" name="submitButton" value="提交订单">
			</div>
		

		<!-- form_item template; display none -->
	<div id="b_formItem">
		<div class="b_form_item clearfix">
			<div class="bf_item_idArea">
				<span class="bf_item_id">${num}</span>
			</div>
				
			<div class="bf_item_textArea">
				<div class="insert_name">
					<span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
					
						<input type="text" name="name" onfocus="textOnFocus(this)" onblur="textOnBlur(this)">
					
				</div>
				<div class="insert_id">
					<span>身份证：</span>
					
					<input type="text" name="pIDNumber" onfocus="textOnFocus(this)" onblur="textOnBlur(this)">
					
				</div>
				<div class="bf_item_seatType">
					<span>座位偏好：</span>
	
					<label>
						<input class="bf_item_radioBut" type="radio" checked="checked" name="seatType1" value="default" id="b_seat_default" />默认
					</label>
					
					<label>
						<input class="bf_item_radioBut" type="radio" name="seatType1" value="windowSeat" id="b_seat_widnow" />靠窗
					</label>
					
					<label>
						<input class="bf_item_radioBut" type="radio" name="seatType1" value="middleSeat" id="b_seat_middle" />中间
					</label>
					
					<label>
						<input class="bf_item_radioBut" type="radio" name="seatType1" value="aisleSeat" id="b_seat_aisle" />靠过道
					</label>
				</div>
			</div>
			<div class="bf_item_subtotalArea">
				<div class="bf_item_delete clearfix">
					<span onclick="deleteItem(this)">删除</span>
					<img src="images/close_icon.png" onclick="deleteItem(this)">
				</div>
				<div class="bf_item_subtotal">
					<c:forEach items="${flightInfoList}" var="flightInfo">
						<span>￥
							<fmt:formatNumber type="number" groupingUsed="false" value="${flightInfo.price}" />
						</span>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>
