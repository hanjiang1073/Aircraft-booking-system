package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDAO;
import pojo.Orders;
import pojo.User;

public class OrdersDaoImpl extends BaseDAO<Orders> {
	
	
	public void add(Orders orders) throws Exception {
		// TODO Auto-generated method stub
		
		super.executeUpdate("insert into \"t_ticket\" values (?,?,?,?,?)",orders.getUserID(),orders.getFno(),orders.getPrice(),orders.getpName(),orders.getpIDNumber() );
		
	}

	
	public Orders findByID(String userID) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from \"t_ticket\" where \"userID\"=?";
		Orders o=super.executeOneQuery(sql, userID);
		return o;
	}
	
	// 按页列出所有用户信息
	public List<Orders> listOrders(int pageNo, int pageSize) {
		// eg select * from (select rownum no,testtab.* from testtab where rownum<=10)
		// where no>=4; 检索记录4~10行
		return super.executeQuery(
				"select \"orderID\",\"userID\",\"fno\",\"price\",\"pName\",\"pIDNumber\" from (select rownum no,\"t_ticket\".* from \"t_ticket\" where rownum<=?) where no>=?",
				pageNo * pageSize, (pageNo - 1) * pageSize + 1);
	}

	// 列出所有信息
	public List<Orders> showUser() {
		return super.executeQuery("select * from \"t_ticket\"", null);
	}

	//删除
	public void deleteOrder(String userID,String orderID,String fno) {
		int j=0;
		j=super.executeUpdate("delete from \"t_ticket\" where \"userID\"=? and \"orderID\"=? and \"fno\"=?", userID,orderID,fno);
		if(j>0)System.out.println("删除了一个order");
	}
	/*
	@Override
	public List<Orders> findByContactName(String contactName) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from orders where contactName=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, contactName);
		ResultSet resultSet = this.pstmt.executeQuery();
		
		List<Orders> list = new ArrayList<>();
		Orders orders = null;
		while(resultSet.next()){
			orders = new Orders();
			orders.setId(resultSet.getInt(1));
			orders.setTripID(resultSet.getInt(2));
			orders.setCreateDate(resultSet.getDate(3));
			orders.setCreateTime(resultSet.getTime(4));
			orders.setTotalFare(resultSet.getDouble(5));
			orders.setContactName(resultSet.getString(6));
			orders.setContactPhone(resultSet.getString(7));
			list.add(orders);
		}
		this.pstmt.close();
		return list;
	}

	@Override
	public List<Orders> findAll() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from orders";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet resultSet = this.pstmt.executeQuery();
		
		List<Orders> list = new ArrayList<>();
		Orders orders = null;
		while(resultSet.next()){
			orders = new Orders();
			orders.setId(resultSet.getInt(1));
			orders.setTripID(resultSet.getInt(2));
			orders.setCreateDate(resultSet.getDate(3));
			orders.setCreateTime(resultSet.getTime(4));
			orders.setTotalFare(resultSet.getDouble(5));
			orders.setContactName(resultSet.getString(6));
			orders.setContactPhone(resultSet.getString(7));
			list.add(orders);
		}
		this.pstmt.close();
		return list;
	}
*/
}
