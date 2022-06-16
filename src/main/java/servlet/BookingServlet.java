package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import impl.*;
import pojo.*;
import pojo.FlightInfo;

@WebServlet("/Booking")
public class BookingServlet extends HttpServlet {

	private List<Orders> orderList;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("!!!!进来了");
		String fno=req.getParameter("fno");
		String userID=req.getParameter("userID");
		System.out.println("userID"+userID);
		FlightDAOImpl flightImpl=new FlightDAOImpl();
		String sql="select * from \"t_flight\" where \"fno\"="+"'"+fno+"'";
		List<Flight> flightInfoList=flightImpl.queryFlight(sql);
		System.out.println(flightInfoList.get(0));
		req.setAttribute("flightInfoList", flightInfoList);
		req.setAttribute("userID", userID);
		req.getRequestDispatcher("/booking.jsp").forward(req,res);
	
	}}
	