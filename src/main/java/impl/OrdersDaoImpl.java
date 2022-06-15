package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDAO;
import pojo.FlightInfo;
import pojo.Orders;
import pojo.User;

public class OrdersDaoImpl extends BaseDAO<Orders> {
	
	
	public void add(Orders orders) throws Exception {
		// TODO Auto-generated method stub
		
		super.executeUpdate("insert into \"t_ticket\" values (?,?,?,?,?,?)",orders.getUserID(),orders.getFno(),orders.getPrice(),orders.getpName(),orders.getpIDNumber(),orders.getOrderID() );
		
	}

	
	public Orders findByID(String userID) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from \"t_ticket\" where \"userID\"=?";
		Orders o=super.executeOneQuery(sql, userID);
		return o;
	}
	
	// 按页列出所有用户信息
	public List<Orders> listOrders(String userID,int pageNo, int pageSize) {
		// eg select * from (select rownum no,testtab.* from testtab where rownum<=10)
		// where no>=4; 检索记录4~10行
		return super.executeQuery(
				"select \"orderID\",\"userID\",\"fno\",\"price\",\"pName\",\"pIDNumber\" from (select rownum no,\"t_ticket\".* from \"t_ticket\" where \"userID\"=? and rownum<=?) where no>=?",
				userID,pageNo * pageSize, (pageNo - 1) * pageSize + 1);
	}

	// 列出所有信息
	public List<Orders> showOrders() {
		return super.executeQuery("select * from \"t_ticket\"", null);
	}

	//删除
	public void deleteOrder(String userID,String orderID,String fno) {
		int j=0;
		j=super.executeUpdate("delete from \"t_ticket\" where \"userID\"=? and \"orderID\"=? and \"fno\"=?", userID,orderID,fno);
		if(j>0)System.out.println("删除了一个order");
	}
	
	

	
	
	
	
}
