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
									  String arrivalCity
									  ) {
		// TODO Auto-generated method stub
		
		return executeQuery("select * from \"t_flight\" where SUBSTR(\"departtime\",0,10) = ? and exists\r\n"
				+ "(select * from \"t_airport\" where (\"t_airport\".\"location\" = ? and \"t_airport\".\"apname\" = \"t_flight\".\"arriveapname\"))\r\n"
				+ "and exists (select * from \"t_airport\" where(\"t_airport\".\"location\" = ? and \"t_airport\".\"apname\" = \"t_flight\".\"departapname\"))",
				departureDate,arrivalCity,departureCity);
		//return executeQuery(sql);
	}
	
	
	public List<Flight> searchFlight( String departureCity, 
			  String departureDate, 
			  String arrivalCity,
			  int page,
			  int pageSize) {
// TODO Auto-generated method stub   ,\"price\"

return executeQuery("select \"fno\",\"pno\",\"departapname\",\"arriveapname\",\"departtime\",\"arrivetime\" ,\"price\" from (select rownum no, \"t_flight\".*   from \"t_flight\" where rownum<= ? ) temp where  SUBSTR(\"departtime\",0,10) = ? and exists\r\n"
+ "(select * from \"t_airport\" where (\"t_airport\".\"location\" = ? and \"t_airport\".\"apname\" = temp.\"arriveapname\"))\r\n"
+ "and exists (select * from \"t_airport\" where(\"t_airport\".\"location\" = ? and \"t_airport\".\"apname\" = temp.\"departapname\")) and temp.no>=? ",
page*pageSize, departureDate,arrivalCity,departureCity,(page-1)*pageSize+1);
//return executeQuery(sql);
//and temp.rownum >=?
//,(page-1)*pageSize+1
}

}
