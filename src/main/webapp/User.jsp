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
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <!-- <link rel="stylesheet" href="css/LoginCss.css"> -->
    <style>
    :root {
      /* COLORS */
      --white: #e9e9e9;
      --gray: #333;
      --blue: #0367a6;
      --lightblue: #008997;

      /* RADII */
      --button-radius: 0.7rem;

      /* SIZES */
      --max-width: 758px;
      --max-height: 420px;

      font-size: 16px;
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
        Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
    }

    body {
      align-items: center;
      background-color: var(--white);
      background: url("https://res.cloudinary.com/dbhnlktrv/image/upload/v1599997626/background_oeuhe7.jpg");
      /* 决定背景图像的位置是在视口内固定，或者随着包含它的区块滚动。 */
      /* https://developer.mozilla.org/zh-CN/docs/Web/CSS/background-attachment */
      background-attachment: fixed;
      background-position: center;
      background-repeat: no-repeat;
      background-size: cover;
      display: grid;
      height: 100vh;
      place-items: center;
    }

    .form__title {
      font-weight: 300;
      margin: 0;
      margin-bottom: 1.25rem;
    }

    .link {
      color: var(--gray);
      font-size: 0.9rem;
      margin: 1.5rem 0;
      text-decoration: none;
    }

    .container {
      background-color: var(--white);
      border-radius: var(--button-radius);
      box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25),
        0 0.7rem 0.7rem rgba(0, 0, 0, 0.22);
      height: var(--max-height);
      max-width: var(--max-width);
      overflow: hidden;
      position: relative;
      width: 100%;
    }

    .container__form {
      height: 100%;
      position: absolute;
      top: 0;
      transition: all 0.6s ease-in-out;
    }

    .container--signin {
      left: 0;
      width: 50%;
      z-index: 2;
    }

    .container.right-panel-active .container--signin {
      transform: translateX(100%);
    }

    .container--signup {
      left: 0;
      opacity: 0;
      width: 50%;
      z-index: 1;
    }

    .container.right-panel-active .container--signup {
      animation: show 0.6s;
      opacity: 1;
      transform: translateX(100%);
      z-index: 5;
    }

    .container__overlay {
      height: 100%;
      left: 50%;
      overflow: hidden;
      position: absolute;
      top: 0;
      transition: transform 0.6s ease-in-out;
      width: 50%;
      z-index: 100;
    }

    .container.right-panel-active .container__overlay {
      transform: translateX(-100%);
    }

    .overlay {
      background-color: var(--lightblue);
      background: url("https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547_1280.jpg");
      background-attachment: fixed;
      background-position: center;
      background-repeat: no-repeat;
      background-size: cover;
      height: 100%;
      left: -100%;
      position: relative;
      transform: translateX(0);
      transition: transform 0.6s ease-in-out;
      width: 200%;
    }

    .container.right-panel-active .overlay {
      transform: translateX(50%);
    }

    .overlay__panel {
      align-items: center;
      display: flex;
      flex-direction: column;
      height: 100%;
      justify-content: center;
      position: absolute;
      text-align: center;
      top: 0;
      transform: translateX(0);
      transition: transform 0.6s ease-in-out;
      width: 50%;
    }

    .overlay--left {
      transform: translateX(-20%);
    }

    .container.right-panel-active .overlay--left {
      transform: translateX(0);
    }

    .overlay--right {
      right: 0;
      transform: translateX(0);
    }

    .container.right-panel-active .overlay--right {
      transform: translateX(20%);
    }

    .btn {
      background-color: var(--blue);
      background-image: linear-gradient(90deg, var(--blue) 0%, var(--lightblue) 74%);
      border-radius: 20px;
      border: 1px solid var(--blue);
      color: var(--white);
      cursor: pointer;
      font-size: 0.8rem;
      font-weight: bold;
      letter-spacing: 0.1rem;
      padding: 0.9rem 4rem;
      text-transform: uppercase;
      transition: transform 80ms ease-in;
    }

    .form>.btn {
      margin-top: 1.5rem;
    }

    .btn:active {
      transform: scale(0.95);
    }

    .btn:focus {
      outline: none;
    }

    .form {
      background-color: var(--white);
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      padding: 0 3rem;
      height: 100%;
      text-align: center;
    }

    .input {
      background-color: #fff;
      border: none;
      padding: 0.9rem 0.9rem;
      margin: 0.5rem 0;
      width: 100%;
    }

    @keyframes show {

      0%,
      49.99% {
        opacity: 0;
        z-index: 1;
      }

      50%,
      100% {
        opacity: 1;
        z-index: 5;
      }
    }
  </style>
  </head>

<body>
  <div class="container right-panel-active">
    
	<!-- 注册 -->
    <div class="container__form container--signup">
      <form action="${basePath}User?method=regist" class="form" id="form1" method="post">
        <h2 class="form__title">ATS注册界面</h2>
        <!-- 隐藏域 -->
      	<input type="hidden" id="hidden-name" name="operate" value="regist"/>
        <input type="text" placeholder="用户名" class="input" id="registUser" name="registUser"/>
        <input type="text" placeholder="手机号" class="input" id="phoneNumber" name="phoneNumber"/>
        <input type="text" placeholder="身份证号" class="input" id="registIDcard" name="registIDcard"/>
        <input type="password" placeholder="密码" class="input" id="registPwd" name="registPwd"/>
        <span id="registMsg" style="font-size:12px;color:red"></span>
        <button class="btn" id="registButton">注册</button>
      </form>
    </div>
    
    <!-- 登录 -->
    <div class="container__form container--signin">
      <form action="${basePath}User?method=login" class="form" id="form2" method="post">
      	<!-- 隐藏域 -->
      	<input type="hidden" id="hidden-name" name="operate" value="login"/>
        <h2 class="form__title">ATS登录界面</h2>
        <input type="text" placeholder="用户名/手机号" class="input" id="loginUser" name ="loginUser"/>
        <input type="password" placeholder="密码" class="input" id="loginPwd" name ="loginPwd"/>
        <a href="#" class="link">忘记密码？</a>
        <span id="loginMsg" style="font-size:12px;color:red"></span>
        <button class="btn" id="loginButton">登录</button>
      </form>
    </div>
	
	
    
    <!-- Overlay -->
    <div class="container__overlay">
      <div class="overlay">
        <div class="overlay__panel overlay--left">
          <button class="btn" id="signIn">切换到登录</button>
        </div>
        <div class="overlay__panel overlay--right">
          <button class="btn" id="signUp">切换到注册</button>
        </div>
      </div>
    </div>
  </div>

  <script>
  const signInBtn = document.getElementById("signIn");
  const signUpBtn = document.getElementById("signUp");
  const fistForm = document.getElementById("form1");
  const secondForm = document.getElementById("form2");
  const container = document.querySelector(".container");

  signInBtn.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
  });

  signUpBtn.addEventListener("click", () => {
    container.classList.add("right-panel-active");
  });

  fistForm.addEventListener("submit", (e) => e.preventDefault());
  secondForm.addEventListener("submit", (e) => e.preventDefault());

  </script>
</body>
<!-- 引入Jquery -->
	<script type="text/javascript" src="js/jquery-3.6.0.js"></script>
	<script type="text/javascript">
		//判断字符串是否为空
		function isEmpty(str){
			if(str==null||str.trim()==""){
				return true;
			}
			return false;
		}
		$("#registButton").click(function(){
			var username=$("#registUser").val();
			var IDcard=$("#registIDcard").val();
			var pwd=$("#registPwd").val();
			if(isEmpty(username)){
				$("#registMsg").html("用户名不可为空");
				return;
			}
			if(isEmpty(IDcard)){
				$("#registMsg").html("身份证号不可为空");
				return;
			}
			if(isEmpty(pwd)){
				$("#registMsg").html("密码不可为空");
				return;
			}
			$("#form1").submit();
		});
		$("#loginButton").click(function(){
			var username=$("#loginUser").val();
			var pwd=$("#loginPwd").val();
			if(isEmpty(username)){
				$("#loginMsg").html("用户名不可为空");
				return;
			}
			if(isEmpty(pwd)){
				$("#loginMsg").html("密码不可为空");
				return;
			}
			$("#form2").submit();
		});
		
		
		
	</script>
</html>
