package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import impl.UserDAOImpl;
import impl.UserDAOImpl;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.User;
import pojo.User;

//用于后台用户管理及登录注册
@WebServlet("/User")
public class UserServlet extends HttpServlet{
	private UserDAOImpl userimpl;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String method=req.getParameter("method");
		System.out.println(method);
		switch(method) {
			case "listUser":
				listUser(req, res);
				break;
			case "addUser":
				addUser(req, res);
				break;
			case "queryUser":
				queryUser(req, res);
				break;
			case "updateUser":
				updateUser(req, res);
				break;
			case "submitUpdate":
				submitUpdate(req, res);
				break;
			case "deleteUser":
				deleteUser(req, res);
				break;
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
	
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		// TODO 自动生成的方法存根
//		//设置编码
//		req.setCharacterEncoding("UTF-8");
//		String operate =req.getParameter("operate");
//		System.out.println(operate);
//		switch(operate) {
//		case "login":
//			login(req, res);
//			break;
//		case "regist":
//			regist(req, res);
//			break;
//		default:
//			break;
//		
//		}
//	}
	
	//登入功能
	public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		String name=req.getParameter("loginUser");
		String pwd=req.getParameter("loginPwd");
		System.out.println(name);
		userimpl=new UserDAOImpl();
		User user =userimpl.getUser(name, pwd);
		System.out.println(user.userName);
		if (user.getRole().equals("1")) {
			req.setAttribute("userID", user.getIDNumber());
//			res.sendRedirect("Reception.jsp");
			req.getRequestDispatcher("Reception.jsp").forward(req, res);
		}else {
			res.sendRedirect("Manage.jsp");
		}
		
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
		res.sendRedirect("Reception.jsp");
	}
	
	
		
	//添加用户
	public void addUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		UserDAOImpl userImpl =new UserDAOImpl();
		System.out.println("here");
		long userID=new Date().getTime();//根据时间戳生成用户id
		String userName=req.getParameter("userName");
		String phoneNumber=req.getParameter("phoneNumber");
		String IDNumber=req.getParameter("IDNumber");
		String role=req.getParameter("role");
		String password=req.getParameter("password");
		String[]info=new String[6];
		info[0]=""+userID;
		info[1]=userName;
		info[2]=phoneNumber;
		info[3]=IDNumber;
		info[4]=role;
		info[5]=password;
		userImpl.addUser(info);
//		req.getRequestDispatcher("ManageUser.jsp").forward(req, res);
		req.setAttribute("pageNo", 1);
		listUser(req, res);
	}
	
	//删除用户
	public void deleteUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userID=req.getParameter("userID");
		System.out.println(userID);
		UserDAOImpl userImpl =new UserDAOImpl();
		userImpl.deleteUser(userID);
		req.setAttribute("pageNo", 1);
		listUser(req, res);
	}
	
	//更新用户
	public void submitUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		UserDAOImpl userImpl =new UserDAOImpl();
		System.out.println("here");
		String userID=req.getParameter("userID");
		String role=req.getParameter("role");
		String[]info=new String[6];
		System.out.println(userID);
		info[0]=role;
		info[1]=userID;
		userImpl.updateUser(role,userID);
		req.setAttribute("pageNo", 1);
		listUser(req, res);
	}
	
	//通过id找用户并初始化到update界面
	public void updateUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userID=req.getParameter("userID");
		UserDAOImpl userImpl =new UserDAOImpl();
		String sql="select * from \"t_user\" where \"userID\"="+"'"+userID+"'";
		List<User> userList=userImpl.queryUser(sql);
		req.setAttribute("user", userList.get(0));
		System.out.println(userList.get(0).getUserID());
		req.getRequestDispatcher("UpdateUser.jsp").forward(req, res);
	}
	
	//展示用户
	public void listUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取当前页码
		String pageNostring=req.getParameter("pageNo");
		int pageNo=1;
		if (pageNostring!=null) {
			pageNo=Integer.parseInt(pageNostring);
		}
		
		System.out.println(pageNo);
		int pageSize=5;
		UserDAOImpl userImpl =new UserDAOImpl();
		List<User> alluser=userImpl.showUser();
		//总记录数
		int totalCount=alluser.size();
		//总页数
		int totalPage=totalCount/pageSize;
		if(totalCount%pageSize!=0||totalPage==0) {
			totalPage++;
		}
		//下一页
		int nextPage=pageNo;
		if(nextPage<totalPage) {
			nextPage=pageNo+1;
		}
		//上一页
		int prePage=pageNo;
		if(prePage>1) {
			prePage=pageNo-1;
		}
		List<User> userList=userImpl.listUser(pageNo, pageSize);
		req.setAttribute("userList", userList);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("prePage", prePage);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageSize", pageSize);
		req.getRequestDispatcher("ManageUser.jsp").forward(req, res);
	}
	
	//条件查询用户
	public void queryUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		UserDAOImpl userImpl =new UserDAOImpl();
		String userID=req.getParameter("userID");
		String userName=req.getParameter("userName");
		String role=req.getParameter("role");
		String sql="select * from \"t_user\"";
		if(!userID.equals("")||!userName.equals("")||!role.equals("")) {
			sql+=" where";
			int flag=0;
			if(!userID.equals("")) {
				if(flag==0) {
					flag++;
					sql+=" \"userID\"="+"'"+userID+"'";
				}else {
					sql+=" and \"userID\"="+"'"+userID+"'";
				}
				
			}
			if(!userName.equals("")) {
				if(flag==0) {
					flag++;
					sql+=" \"userName\"="+"'"+userName+"'";
				}else {
					sql+=" and \"userName\"="+"'"+userName+"'";
				}
			}
			if(!role.equals("")) {
				if(flag==0) {
					flag++;
					sql+=" \"role\"="+"'"+role+"'";
				}else {
					sql+=" and \"role\"="+"'"+role+"'";
				}
			}
			
		}
		List<User> userList=userImpl.queryUser(sql);
		req.setAttribute("userList", userList);
		req.getRequestDispatcher("ManageUser.jsp").forward(req, res);
	}
}