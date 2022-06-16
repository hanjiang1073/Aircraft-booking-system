package servlet;

import java.io.IOException;
import java.util.List;

import impl.FlightDAOImpl;
import impl.UserDAOImpl;
//import jakarta.websocket.Session;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import pojo.Flight;

//用于后台航班管理
@WebServlet("/Flight")
public class FlightServlet extends HttpServlet{
	
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
			case "listFlight":
				listFlight(req, res);
				break;
			case "addFlight":
				addFlight(req, res);
				break;
			case "queryFlight":
				queryFlight(req, res);
				break;
			case "updateFlight":
				updateFlight(req, res);
				break;
			case "submitUpdate":
				submitUpdate(req, res);
				break;
			case "deleteFlight":
				deleteFlight(req, res);
				break;
			default:
				break;
		}
	}
		
	//添加航班
	public void addFlight(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		FlightDAOImpl flightImpl =new FlightDAOImpl();
		System.out.println("here");
		String fno=req.getParameter("fno");
		String pno=req.getParameter("pno");
		String departapname=req.getParameter("departapname");
		String arriveapname=req.getParameter("arriveapname");
		String departtime=req.getParameter("departtime");
		String arrivetime=req.getParameter("arrivetime");
		String price=req.getParameter("price");
		String[]info=new String[7];
		info[0]=fno;
		info[1]=pno;
		info[2]=departapname;
		info[3]=arriveapname;
		info[4]=departtime;
		info[5]=arrivetime;
		info[6]=price;
		flightImpl.addFlight(info);
//		req.getRequestDispatcher("ManageFlight.jsp").forward(req, res);
		req.setAttribute("pageNo", 1);
		listFlight(req, res);
	}
	
	//删除航班
	public void deleteFlight(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fno=req.getParameter("fno");
		System.out.println(fno);
		FlightDAOImpl flightImpl =new FlightDAOImpl();
		flightImpl.deleteFlight(fno);
		req.setAttribute("pageNo", 1);
		listFlight(req, res);
	}
	
	//更新航班
	public void submitUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		FlightDAOImpl flightImpl =new FlightDAOImpl();
		System.out.println("here");
		String fno=req.getParameter("fno");
		String pno=req.getParameter("pno");
		String departapname=req.getParameter("departapname");
		String arriveapname=req.getParameter("arriveapname");
		String departtime=req.getParameter("departtime");
		String arrivetime=req.getParameter("arrivetime");
		String price=req.getParameter("price");
		String[]info=new String[7];
		info[0]=pno;
		info[1]=departapname;
		info[2]=arriveapname;
		info[3]=departtime;
		info[4]=arrivetime;
		info[5]=price;
		info[6]=fno;
		flightImpl.updateFlight(info);
		req.setAttribute("pageNo", 1);
		listFlight(req, res);
	}
	
	//通过id找航班并初始化到update界面
	public void updateFlight(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fno=req.getParameter("fno");
		FlightDAOImpl flightImpl =new FlightDAOImpl();
		String sql="select * from \"t_flight\" where \"fno\"="+"'"+fno+"'";
		List<Flight> flightList=flightImpl.queryFlight(sql);
		req.setAttribute("flight", flightList.get(0));
		System.out.println(flightList.get(0).getFno());
		req.getRequestDispatcher("UpdateFlight.jsp").forward(req, res);
	}
	
	//展示航班
	public void listFlight(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取当前页码
		String pageNostring=req.getParameter("pageNo");
		int pageNo=1;
		if (pageNostring!=null) {
			pageNo=Integer.parseInt(pageNostring);
		}
		
		System.out.println(pageNo);
		int pageSize=5;
		FlightDAOImpl flightImpl =new FlightDAOImpl();
		List<Flight> allflight=flightImpl.showFlight();
		//总记录数
		int totalCount=allflight.size();
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
		List<Flight> flightList=flightImpl.listFlight(pageNo, pageSize);
		req.setAttribute("flightList", flightList);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("prePage", prePage);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageSize", pageSize);
		req.getRequestDispatcher("ManageFlight.jsp").forward(req, res);
	}
	
	//条件查询航班
	public void queryFlight(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		FlightDAOImpl flightImpl =new FlightDAOImpl();
		String fno=req.getParameter("fno");
		String departapname=req.getParameter("departapname");
		String arriveapname=req.getParameter("arriveapname");
		String departtime=req.getParameter("departtime");
		String sql="select * from \"t_flight\"";
		if(!fno.equals("")||!departapname.equals("")||!arriveapname.equals("")||!departtime.equals("")) {
			sql+=" where";
			int flag=0;
			if(!fno.equals("")) {
				if(flag==0) {
					flag++;
					sql+=" \"fno\"="+"'"+fno+"'";
				}else {
					sql+=" and \"fno\"="+"'"+fno+"'";
				}
				
			}
			if(!departapname.equals("")) {
				if(flag==0) {
					flag++;
					sql+=" \"departapname\"="+"'"+departapname+"'";
				}else {
					sql+=" and \"departapname\"="+"'"+departapname+"'";
				}
			}
			if(!arriveapname.equals("")) {
				if(flag==0) {
					flag++;
					sql+=" \"arriveapname\"="+"'"+arriveapname+"'";
				}else {
					sql+=" and \"arriveapname\"="+"'"+arriveapname+"'";
				}
			}
			if(!departtime.equals("")) {
				if(flag==0) {
					flag++;
					sql+=" SUBSTR(\"departtime\",0,10)="+"'"+departtime+"'";
//					substr("departtime",0,10)=?
				}else {
					sql+=" and SUBSTR(\"departtime\",0,10)="+"'"+departtime+"'";
				}
			}
			
		}
		List<Flight> flightList=flightImpl.queryFlight(sql);
		req.setAttribute("flightList", flightList);
		req.getRequestDispatcher("ManageFlight.jsp").forward(req, res);
	}
}
