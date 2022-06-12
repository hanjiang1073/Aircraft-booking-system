package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import jakarta.servlet.ServletException;

import impl.OrdersDaoImpl;
import impl.FlightInfoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pojo.*;

@WebServlet("/Orders")
public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		//?????获取当前用户的userid
		System.out.println("!!!!进来了");
		String method=request.getParameter("method");
		if("list".equals(method)) {
			list(request, response);
		}else if("details".equals(method)) {
			details(request, response);
		}else if("deleteOrder".equals(method)) {
			deleteOrder(request, response);
		}
		

	}
	
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userID");
		System.out.println("（list）userId:"+userId);
		String pageNostring=request.getParameter("pageNo");
		
		int pageNo=1;
		if (pageNostring!=null) {
			pageNo=Integer.parseInt(pageNostring);
		}
		
		System.out.println(pageNo);
		int pageSize=5;
		
		//总记录数
		OrdersDaoImpl ordersDaoImpl = new OrdersDaoImpl();
		List<Orders> allorder=ordersDaoImpl.showOrders();		
		int totalCount=allorder.size();
		
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


		List<Orders> orderList=null;
		try {
			orderList=ordersDaoImpl.listOrders(userId,pageNo, pageSize);	
		} catch (Exception e) {
			System.out.println("这有问题");
			e.printStackTrace();
		}

		request.setAttribute("orderList", orderList);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("prePage", prePage);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);

		request.getRequestDispatcher("/ManageOrders.jsp").forward(request, response);
		
	}
	
	//删除订单
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID=request.getParameter("userID");
		String orderID=request.getParameter("orderID");
		String fno=request.getParameter("fno");
		System.out.println(userID);
		OrdersDaoImpl ordersDaoImpl = new OrdersDaoImpl();
		ordersDaoImpl.deleteOrder(userID,orderID,fno);
		request.setAttribute("pageNo", 1);
		list(request, response);
	}
	
	
	
	//跳转详情页面
	public void details(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userID");
		System.out.println("userId:"+userId);
		
		OrdersDaoImpl ordersDaoImpl = new OrdersDaoImpl();

		Orders order=null;
		try {
			order=ordersDaoImpl.findByID(userId);	
		} catch (Exception e) {
			System.out.println("这有问题");
			e.printStackTrace();
		}

		
		
		List<FlightInfo> flightInfoList = new ArrayList<FlightInfo>();
		//找FilghInfo
		FlightInfoImpl flightInfoImpl = new FlightInfoImpl();
		FlightInfo flightInfo=flightInfoImpl.findFlightInfo(order.getFno());

		System.out.println(flightInfo.toString());
		
		//将FilghInfo放入flightInfoList
		flightInfoList.add(flightInfo);
		
		
		//设置返回的参数
		request.setAttribute("flightInfoList", flightInfoList);	
		request.setAttribute("order", order);	
		request.getRequestDispatcher("/Order.jsp").forward(request, response);
		
////		for(int i = 0; i < seatTypeList.length; i++){
////		seatTypeList[i] = request.getParameter("seatType" + (i + 1)); System.out.println(seatTypeList[i]);
////	}
////	String contactName = request.getParameter("contact");
////	String contactPhone	= request.getParameter("phone");
////	
	
	
//	Orders order;
//	try {
//		for (String idString : fIDList) { 
//			FlightInfo flightInfo = DaoFactory.getFlightInfoDaoInstance(DBName.ABS).findByID(Integer.parseInt(idString));
//			if(null != flightInfo){
//				flightInfoList.add(flightInfo);
//			}
//		}
//		//	discount of same airline
//		if(flightInfoList.size() == 2){
//			FlightInfo f1 = flightInfoList.get(0);
//			FlightInfo f2 = flightInfoList.get(1);
//			
//			if(f1.getAirlineCode().equals(f2.getAirlineCode())){
//				double discount = DaoFactory.getAirlineDaoInstance(DBName.ABS).findByCode(f1.getAirlineCode()).getDiscount();
//				f2.setFare(f2.getFare() * discount); 
//			}
//		}
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	List<Passenger> passengers = new ArrayList<Passenger>();
//	for(int i = 0; i < nameList.length; i++){
//		Passenger passenger = new Passenger();				System.out.println(i + " : " + nameList[i] + " " + passportList[i]);
//		passenger.setName(nameList[i]);
//		passenger.setPassport(passportList[i]);
//		passengers.add(passenger);
//	}
//	
//	Orders order = null;
//	try {
//		order = OrderAction.createOrder(flightInfoList, passengers, seatTypeList, contactName, contactPhone);
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	
//	request.setAttribute("flightInfoList", flightInfoList);	
//	request.setAttribute("passengers", passengers);	
//	request.setAttribute("order", order);	
//	
//	request.getRequestDispatcher("/generateOrder.jsp").forward(request,response);
	}
	

}
