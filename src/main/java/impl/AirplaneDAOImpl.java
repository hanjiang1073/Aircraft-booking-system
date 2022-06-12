package impl;

import java.util.List;

import dao.BaseDAO;
import pojo.Airplane;

public class AirplaneDAOImpl extends BaseDAO<Airplane>{
	//列出所有信息
	public List<Airplane> showAirplane(){
		return super.executeQuery("select * from \"t_airplane\"", null);
	}
	
	//按页列出所有飞机信息
	public List<Airplane> listAirplane(int pageNo,int pageSize){
		//eg select * from (select rownum no,testtab.* from testtab where rownum<=10) where no>=4; 检索记录4~10行
		return super.executeQuery("select \"pno\",\"cname\",\"ptype\",\"capcity\" from (select rownum no,\"t_airplane\".* from \"t_airplane\" where rownum<=?) where no>=?", pageNo*pageSize,(pageNo-1)*pageSize+1);
	}
	//创建飞机
	public void addAirplane(String[]info) {
		System.out.println(info);
		super.executeUpdate("insert into \"t_airplane\" values (?,?,?,?)",info );
	}
	
	//删除飞机
	public void deleteAirplane(String pno) {
		super.executeUpdate("delete from \"t_airplane\" where \"pno\"=?", pno);
	}
	
	//更新飞机
	public void updateAirplane(String[] info) {
		super.executeUpdate("update \"t_airplane\" set \"cname\"=? , \"ptype\"=? , \"capcity\"=? where \"pno\"=?",info );
	}
	
	//条件查询
	public List<Airplane> queryAirplane(String sql) {
		System.out.println(sql);
		return super.executeQuery(sql, null);
	}
}
