package pojo;

import java.io.Serializable;
import java.sql.*;

public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7603388547114215304L;
	
	private static int idCounter = 100001;
	
	private String  orderID;
	private	String  userID;			//	id相同的为同一个订单
	private	String	fno;			//flightNo
	private String	price;
	private String	pName;
	private String 	pIDNumber;
	
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	public Orders() {
		// TODO Auto-generated constructor stub
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpIDNumber() {
		return pIDNumber;
	}

	public void setpIDNumber(String pIDNumber) {
		this.pIDNumber = pIDNumber;
	}

	
}
