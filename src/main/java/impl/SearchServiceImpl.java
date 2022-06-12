package impl;

import java.util.List;

import dao.BaseDAO;
import dao.FlightDAO;

import pojo.Flight;
import service.SearchService;

public class SearchServiceImpl extends BaseDAO<Flight> implements SearchService{
     
	private FlightDAO flightDao ;
	@Override
	public List<Flight> searchFlight( String departureCity, 
									  String departureDate, 
									  String arrivalCity) {
		// TODO Auto-generated method stub
		String sql = "select * from \"t_flight\" where \"departtime\" = '2022.7.1' and exists\r\n"
				+ "(select * from \"t_airport\" where (\"t_airport\".\"location\" = '����' and \"t_airport\".\"apname\" = \"t_flight\".\"arriveapname\"))\r\n"
				+ "and exists (select * from \"t_airport\" where(\"t_airport\".\"location\" = '�人' and \"t_airport\".\"apname\" = \"t_flight\".\"departapname\"))";
		return executeQuery("select * from \"t_flight\" where \"departtime\" = ? and exists\r\n"
				+ "(select * from \"t_airport\" where (\"t_airport\".\"location\" = ? and \"t_airport\".\"apname\" = \"t_flight\".\"arriveapname\"))\r\n"
				+ "and exists (select * from \"t_airport\" where(\"t_airport\".\"location\" = ? and \"t_airport\".\"apname\" = \"t_flight\".\"departapname\"))",
				departureDate,arrivalCity,departureCity);
		//return executeQuery(sql);
	}

}
