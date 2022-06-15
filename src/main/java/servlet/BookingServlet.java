package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import impl.FlightDAOImpl;
import jakarta.servlet.annotation.WebServlet;
import impl.*;
import pojo.*;
import pojo.FlightInfo;

@WebServlet("/Booking")
public class BookingServlet extends HttpServlet {

	private List<Orders> orderList;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		try {
			Flightinit(request, response);
		} catch (ClassNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (ServletException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		String method=request.getParameter("method");
		System.out.println(method);
		switch(method) 
		{
		
		
	case "order":
			try {
				addOrder(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		
		}
	}
	public void addOrder(HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrdersDaoImpl flightImpl =new OrdersDaoImpl();
		System.out.println("here");
		String fno=req.getParameter("fno");
		String UserId=req.getParameter("UserID");
		String pName=req.getParameter("name");
		String IDNumber=req.getParameter("passport");
		String price=req.getParameter("price");
		Orders order=new Orders();
		 order.setFno(fno);
		 order.setUserID(UserId);
		 order.setPrice(price);
		 order.setpName(pName);
		 order.setpIDNumber(IDNumber);
		 flightImpl.add(order);
       req.getRequestDispatcher("Order.jsp").forward(req, res);
		
	}
	public void Flightinit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ClassNotFoundException {
		//获取当前页码
		String[] fIDList = req.getParameterValues("flightInfoID");
		 FlightInfoImpl  flightInfoImpl= new FlightInfoImpl();
		List<FlightInfo> flightInfoList = new ArrayList<FlightInfo>();
		 flightInfoList = flightInfoImpl.findFlight(fIDList[0]);
		req.setAttribute("flightInfoList", flightInfoList);		
		req.getRequestDispatcher("/booking.jsp").forward(req,res);
		
	}

}
