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
	}
	
	//删除航班
	public void deleteFlight(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
	//修改航班
	public void updateFlight(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

	}
	
	//展示航班
	public void listFlight(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		FlightDAOImpl flightImpl =new FlightDAOImpl();
		List<Flight> flightList=flightImpl.showFlight();
		System.out.println(flightList.get(0).getFno());
		req.setAttribute("flightList", flightList);
		req.getRequestDispatcher("ManageFlight.jsp").forward(req, res);
	}
}
