package pojo;

import java.io.Serializable;
import java.sql.*;

public class FlightInfo implements Serializable {

	private String fno;//航班编号
	private String pno;//飞机编号
	private String departapname;//出发机场
	private String arriveapname;//目的机场
	private String departtime;//出发时间
	private String arrivetime;//到达时间
	private String depatureAirportCity;//出发城市
	private String arrivalAirportCity;//到达城市
	private String price;//价格
	
	public String getFno() {
		return fno;
	}
	public String getPno() {
		return pno;
	}
	public String getDepartapname() {
		return departapname;
	}
	public String getArriveapname() {
		return arriveapname;
	}
	public String getDeparttime() {
		return departtime;
	}
	public String getArrivetime() {
		return arrivetime;
	}
	public String getPrice() {
		return price;
	}
	public String getDepatureAirportCity() {
		return depatureAirportCity;
	}
	
	public String getArrivalAirportCity() {
		return arrivalAirportCity;
	}
	
}
