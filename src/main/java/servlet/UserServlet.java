package servlet;

import java.io.IOException;

import impl.UserDAOImpl;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet{
	private UserDAOImpl userimpl;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		//设置编码
		req.setCharacterEncoding("UTF-8");
		String operate =req.getParameter("operate");
		System.out.println(operate);
		switch(operate) {
		case "login":
			login(req, res);
			break;
		case "regist":
			regist(req, res);
			break;
		default:
			break;
		
		}
	}
	
	//登入功能
	public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		String name=req.getParameter("loginUser");
		String pwd=req.getParameter("loginPwd");
		userimpl=new UserDAOImpl();
		User user =userimpl.getUser(name, pwd);
		System.out.println(user.userName);
		res.sendRedirect("Search.html");
	}

	//注册功能
	public void regist(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("registUser");
		String pwd=req.getParameter("registPwd");
		String idcard=req.getParameter("registIDcard");
		String phone=req.getParameter("phoneNumber");
		System.out.println(name+pwd+idcard+phone);
		userimpl=new UserDAOImpl();
		userimpl.registUser(name, phone, pwd, idcard);
		res.sendRedirect("Search.html");
	}
}
