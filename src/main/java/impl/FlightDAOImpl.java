package impl;

import java.util.List;

import dao.BaseDAO;
import pojo.Flight;

public class FlightDAOImpl extends BaseDAO<Flight>{
	
	//列出所有航班信息
	public List<Flight> showFlight(){
		return super.executeQuery("select * from \"t_flight\"", null);
	}
	
	//按页列出航班信息
	public List<Flight> listFlight(int pageNo,int pageSize){
		//eg select * from (select rownum no,testtab.* from testtab where rownum<=10) where no>=4; 检索记录4~10行
		return super.executeQuery("select \"fno\",\"pno\",\"departapname\",\"arriveapname\",\"departtime\",\"arrivetime\",\"price\" from (select rownum no,\"t_flight\".* from \"t_flight\" where rownum<=?) where no>=?", pageNo*pageSize,(pageNo-1)*pageSize+1);
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
		super.executeUpdate("update \"t_flight\" set \"pno\"=? , \"departapname\"=? , \"arriveapname\"=? , \"departtime\"=? , \"arrivetime\"=? , \"price\"=? where \"fno\"=?",info );
	}
	
	//条件查询
	public List<Flight> queryFlight(String sql) {
		System.out.println(sql);
		return super.executeQuery(sql, null);
	}
	
//	//按页出条件查询结果
//	public List<Flight> queryListFlight(String sql,int pageNo,int pageSize){
//		return super.executeQuery("select \"fno\",\"pno\",\"departapname\",\"arriveapname\",\"departtime\",\"arrivetime\",\"price\" from (select rownum no,\"t_query\".* from (?)\"t_query\" where rownum<=?) where no>=?", sql,pageNo*pageSize,(pageNo-1)*pageSize+1);
//	}
}
