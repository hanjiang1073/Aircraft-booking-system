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
String method=request.getParameter("method");
		
		
		if("list".equals(method)) {
			list(request, response);
		}else if("details".equals(method)) {
			details(request, response);
		}else if("deleteOrder".equals(method)) {
			deleteOrder(request, response);
		}else if("addOrder".equals(method)) {
			addOrder(request, response);
		}
		
		

	}
	
	public void addOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		OrdersDaoImpl flightImpl =new OrdersDaoImpl();
		System.out.println("here");
		String[] pNames=req.getParameterValues("name");
		String[] IDNumbers=req.getParameterValues("pIDNumber");
		String fno=req.getParameter("fno");
		String UserId=req.getParameter("userID");
		String price=req.getParameter("price");
		
		for(int i=0;i<pNames.length-1;i++) {
			Orders order=new Orders();
			 order.setFno(fno);
			 order.setUserID(UserId);
			 order.setPrice(price);
			 order.setpName(pNames[i]);
			 order.setpIDNumber(IDNumbers[i]);
			 try {
				flightImpl.add(order);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID=request.getParameter("userID");
System.out.println("（list）userId:"+userID);
		String pageNostring=request.getParameter("pageNo");
		
		int pageNo=1;
		if (pageNostring!=null) {
			pageNo=Integer.parseInt(pageNostring);
		}
		
		System.out.println("pageNo:"+pageNo);
		int pageSize=5;
		
		//总记录数
		OrdersDaoImpl ordersDaoImpl = new OrdersDaoImpl();
		List<Orders> allorder=ordersDaoImpl.showOrders(userID);		
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
			orderList=ordersDaoImpl.listOrders(userID,pageNo, pageSize);	
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
		request.setAttribute("userID", userID);

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
		String orderID=request.getParameter("orderID");
		System.out.println("orderID:"+orderID);
		
		OrdersDaoImpl ordersDaoImpl = new OrdersDaoImpl();

		Orders order=null;
		try {
			order=ordersDaoImpl.findByID(orderID);	
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

	}
	

}
