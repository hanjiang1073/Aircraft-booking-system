package impl;

import java.util.List;

import dao.BaseDAO;
import pojo.Flight;

public class FlightDAOImpl extends BaseDAO<Flight>{
	
	//列出所有航班信息
	public List<Flight> showFlight(){
		return super.executeQuery("select * from \"t_flight\"", null);
	}
	
	//创建航班
	public void addFlight(String[]info) {
		System.out.println(info);
		super.executeUpdate("insert into \"t_flight\" values (?,?,?,?,?,?,?)",info );
	}
	
	//删除航班
	public void deleteFlight(String fno) {
		super.executeUpdate("delete from \"t_flight\" where \"fno\"=?", fno);
	}
	
	//更新航班
	public void updateFlight(String[] info) {
		super.executeUpdate("update \"t_flight\" set pno=? and departapname=? and arriveapname=? and departtime=? and arrivetime=? and price=? where fno=?",info );
	}
}
