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
		<div class="title">航空票务后台管理系统</div>
		<div class="user">
			管理员
			<ul>
				<!-- 
				<li><a href="javascript:void(0);">修改信息</a></li>
				<li><a href="javascript:void(0);">退出登录</a></li>
				 -->
				
			</ul>
		</div>
	</div>
	<div class="left">
		<div class="title">后台管理</div>
		<div class="menux">
			<ul>
				<li>
					<a href="javascript:void(0);" url="${basePath}Flight?method=listFlight" title="航班管理">
					航班管理
					</a>
				</li>
				<li>
					<a href="javascript:void(0);" url="ManageAirplane.jsp" title="飞机管理">
					飞机管理
					</a>
				</li>
				<li>
					<a href="javascript:void(0);" url="ManageAirport.jsp" title="机场管理">
					机场管理
					</a>
				</li>
			
			</ul>
		</div>
	</div>
	<div class="main">
		<div class="location">
			<span class="manage_title"></span>
		</div>
		<iframe src="" width="95%" height="90%" frameborder="0"></iframe>
	</div>
</body>
</html>