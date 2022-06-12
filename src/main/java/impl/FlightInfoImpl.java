package impl;

import java.util.List;

import dao.BaseDAO;
import pojo.FlightInfo;

public class FlightInfoImpl extends BaseDAO<FlightInfo>{
	
	//查询航班信息
	public List<FlightInfo> findFlight(String idString) throws ClassNotFoundException{
		return super.executeQuery("select * from \"t_flight\"  where \"fno\" =? ", idString);
	}
	
}