package servlet;

import java.io.IOException;
import java.util.List;

import impl.SearchServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.Flight;

@WebServlet("/search")
public class SearchServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String method=req.getParameter("method0");
System.out.println("method:"+method);
		if("toSearch".equals(method)) {
			toSearch(req,res);
		}else if("toSearchFlight".equals(method)){
			toSearchFlight(req,res);
		}else if("toBooking".equals(method)) {
			toBooking(req,res);
		}
		
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void toSearchFlight(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
	
		SearchServiceImpl ssi = new SearchServiceImpl();

		String departureCity =req.getParameter("departureCity");

		String departureDate  =req.getParameter("departureDate");

		String arrivalCity  =req.getParameter("arrivalCity");
		
		String userID=req.getParameter("userID");

System.out.println(userID);
		
		//String returnDate  =req.getParameter("returnDate");
		String pageNostring=req.getParameter("pageNo");
		int pageNo=1;
		if (pageNostring!=null) {
			pageNo=Integer.parseInt(pageNostring);
		}
System.out.println(pageNo);
		int pageSize=5;
		
		List<Flight> searchList = ssi.searchFlight(departureCity, departureDate, arrivalCity);
System.out.println(searchList.isEmpty());	
		int totalCount=searchList.size();
		int totalPage=totalCount/pageSize;
		if(totalCount%pageSize!=0||totalPage==0) {
			totalPage++;
		}
		
		
		int prePage=pageNo;
		if(prePage>1) {
			prePage=pageNo-1;
		}
		
		int nextPage=pageNo;
		if(nextPage<totalPage) {
			nextPage=pageNo+1;
		}
		
		searchList = ssi.searchFlight(departureCity, departureDate, arrivalCity , pageNo, pageSize);
		req.setAttribute("searchList", searchList);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("prePage", prePage);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("userID", userID);
		req.setAttribute("departureCity", departureCity);
		req.setAttribute("departureDate", departureDate);
		req.setAttribute("arrivalCity", arrivalCity);
		req.getRequestDispatcher("SearchFlight.jsp").forward(req, res);
	}
	
	public void toSearch(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		String userID=req.getParameter("userID");
		req.setAttribute("userID", userID);
		req.getRequestDispatcher("Search.jsp").forward(req, res);
	}
	
	public void toBooking(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		String userID=req.getParameter("userID");
System.out.println("userID"+userID);
		String fno=req.getParameter("fno");
		req.setAttribute("userID", userID);
		req.setAttribute("fno", fno);
		req.getRequestDispatcher("Booking").forward(req, res);
	}
	
}
