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
		
		List<Flight> searchList = ssi.searchFlight(departureCity, departureDate, arrivalCity);
		req.setAttribute("searchList", searchList);
System.out.println(searchList.isEmpty());
		
		req.setAttribute("userID", userID);
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
