package impl;

import java.util.List;

import dao.BaseDAO;
import dao.FlightDAO;

import pojo.Flight;
import service.SearchService;

public class SearchServiceImpl extends BaseDAO<Flight> implements SearchService{

	private FlightDAO flightDao ;
	@Override
	public List<Flight> searchFlight( String departureCity, String departureDate, String arrivalCity) {
		// TODO Auto-generated method stub
		if(departureCity.isEmpty()&&departureDate.isEmpty()&&arrivalCity.isEmpty()) {
			System.out.println("3");
			//全空的话返回所有记录
			return executeQuery("select * from \"t_flight\"");
		}
		else {
			return executeQuery("select * from \"t_flight\" where SUBSTR(\"departtime\",0,10) = ? and exists\r\n"
					+ "(select * from \"t_airport\" where (\"t_airport\".\"location\" = ? and \"t_airport\".\"apname\" = \"t_flight\".\"arriveapname\"))\r\n"
					+ "and exists (select * from \"t_airport\" where(\"t_airport\".\"location\" = ? and \"t_airport\".\"apname\" = \"t_flight\".\"departapname\"))",
					departureDate,arrivalCity,departureCity);
			   }
	}


	public List<Flight> searchFlight( String departureCity, String departureDate, String arrivalCity,int page,int pageSize) {
// TODO Auto-generated method stub   ,\"price\"
//全空返回所有记录
		if(departureCity.isEmpty()&&departureDate.isEmpty()&&arrivalCity.isEmpty()) {
			System.out.println("4");
			return executeQuery("select \"fno\",\"pno\",\"departapname\",\"arriveapname\",\"departtime\",\"arrivetime\",\"price\" from (select rownum no, \"t_flight\".*   from \"t_flight\" where rownum<= ? ) temp where temp.no>=?",page*pageSize,(page-1)*pageSize+1);
		}else {
			return executeQuery("select \"fno\",\"pno\",\"departapname\",\"arriveapname\",\"departtime\",\"arrivetime\" ,\"price\" from (select rownum no, \"t_flight\".* from \"t_flight\" where  SUBSTR(\"departtime\",0,10) =? and exists(select * from \"t_airport\" where (\"t_airport\".\"location\" = ? and \"t_airport\".\"apname\" = \"t_flight\".\"arriveapname\"))and exists (select * from \"t_airport\" where(\"t_airport\".\"location\" = ? and \"t_airport\".\"apname\" = \"t_flight\".\"departapname\"))) temp where rownum<= ?and temp.no>=?",
					departureDate,arrivalCity,departureCity,page*pageSize,(page-1)*pageSize+1);
		}
	}
}
