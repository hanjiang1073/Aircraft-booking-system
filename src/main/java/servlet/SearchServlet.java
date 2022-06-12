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
		SearchServiceImpl ssi = new SearchServiceImpl();

		String departureCity =req.getParameter("departureCity");

		String departureDate  =req.getParameter("departureDate");

		String arrivalCity  =req.getParameter("arrivalCity");

		
		//String returnDate  =req.getParameter("returnDate");
		System.out.println(departureCity);
		System.out.println(departureDate);
		System.out.println(arrivalCity);
		
		List<Flight> searchList = ssi.searchFlight(departureCity, departureDate, arrivalCity);
		req.setAttribute("searchList", searchList);
		System.out.println(searchList.isEmpty());
		
		req.getRequestDispatcher("SearchFlight.jsp").forward(req, res);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
