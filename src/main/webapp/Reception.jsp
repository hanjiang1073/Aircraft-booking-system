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
	$(function(){
		$('.menux ul a').click(function(){
			$('.manage_title').html($(this).attr("title"));
			$('iframe').attr("src",$(this).attr("url"));
		});
	})
</script>

<title>Manage</title>
</head>
<body>
	<div class="header">
		<div class="title">航空票务系统</div>
		<div class="user">
			用户
			<ul>
				<!-- 
				<li><a href="javascript:void(0);">修改信息</a></li>
				<li><a href="javascript:void(0);">退出登录</a></li>
				 -->
				
			</ul>
		</div>
	</div>
	<div class="left">
		<div class="title">操作</div>
		<div class="menux">
			<ul>
				<li>
					<%-- <a href="javascript:void(0);" url="search?method0=toSearch&userID=1655301876821" title="预定航班">--%>
					<a href="javascript:void(0);" url="search?method0=toSearch&userID=${userID}" title="预定航班">
					预定航班
					</a>
				</li>
				<li>
					<a href="javascript:void(0);" url="Orders?method=list&userID=${userID}" title="订单管理">
					订单管理
					</a>
				</li>
				
			
			</ul>
		</div>
	</div>
	<div class="main">
		<div class="location">
			<span class="manage_title"></span>
			<a class="log_out" href="/AirWeb/User.jsp">退出登录</a>
		</div>
		<iframe src="" width="95%" height="90%" frameborder="0"></iframe>
	</div>
</body>
</html>