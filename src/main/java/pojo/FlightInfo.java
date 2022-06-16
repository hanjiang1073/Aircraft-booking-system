package pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private String airlineName;//航空公司名
	private String airplaneName;//飞机名
	private Date   depatureDate; //出发的日期
	
	
	public Date getDepatureDate() {
		return depatureDate;
	}
	public void setDepatureDate(String strdepatureDate){
		//将string转成data
		String[] strlist=strdepatureDate.split("\\.");
		String str="";
System.out.println(strlist[0]);
		for(int i=0;i<strlist.length;i++) {
			if(i==strlist.length-1) {
				str=str+strlist[i];
			}else {
				str=str+strlist[i]+"-";
			}
		}
System.out.println(str);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = formatter.parse(str);
			this.depatureDate = date;
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getAirplaneName() {
		return airplaneName;
	}
	public void setAirplaneName(String airplaneName) {
		this.airplaneName = airplaneName;
	}
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
	
	
	public void setFno(String fno) {
		this.fno = fno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public void setDepartapname(String departapname) {
		this.departapname = departapname;
	}
	public void setArriveapname(String arriveapname) {
		this.arriveapname = arriveapname;
	}
	public void setDeparttime(String departtime) {
		this.departtime = departtime;
	}
	public void setArrivetime(String arrivetime) {
		this.arrivetime = arrivetime;
	}
	public void setDepatureAirportCity(String depatureAirportCity) {
		this.depatureAirportCity = depatureAirportCity;
	}
	public void setArrivalAirportCity(String arrivalAirportCity) {
		this.arrivalAirportCity = arrivalAirportCity;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "FlightInfo [fno=" + fno + ", pno=" + pno + ", departapname=" + departapname + ", arriveapname="
				+ arriveapname + ", departtime=" + departtime + ", arrivetime=" + arrivetime + ", depatureAirportCity="
				+ depatureAirportCity + ", arrivalAirportCity=" + arrivalAirportCity + ", price=" + price
				+ ", airlineName=" + airlineName + ", airplaneName=" + airplaneName + ", depatureDate=" + depatureDate
				+ "]";
	}
	
	
}
