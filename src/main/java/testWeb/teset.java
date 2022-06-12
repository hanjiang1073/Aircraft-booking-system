package testWeb;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.FlightInfo;

public class teset{
	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","C##hzl","011125");
		Statement stt=conn.createStatement();
		
		String sqlString="SELECT \"departtime\" \"depatureDate\",\"departtime\",\"arrivetime\",\"departapname\",\"arriveapname\",a1.\"location\" \"depatureAirportCity\",a2.\"location\" \"arrivalAirportCity\",c.\"cname\" \"airlineName\",p.\"ptype\" \"airplaneName\"from \"t_flight\" f,\"t_airport\" a1,\"t_airport\" a2,\"t_airplane\" p,\"t_company\" c where f.\"departapname\"=a1.\"apname\" and f.\"arriveapname\"=a2.\"apname\" and f.\"pno\"=p.\"pno\" and p.\"cno\"=c.\"cno\" and f.\"fno\"=?";

		PreparedStatement psmt= conn.prepareStatement(sqlString);
//		stt=conn.createStatement();
		psmt.setObject(1,543534);
		FlightInfo f=null;

		ResultSet rs=psmt.executeQuery();
		ResultSetMetaData rsmd=rs.getMetaData();
		System.out.println("1");
		while(rs.next()) {
			String sno =rs.getString(1);
			String name1=rsmd.getColumnName(1);
			String sname =rs.getString(2);
			String ssex=rs.getString(3);
			System.out.println(sno+" "+name1+" "+ssex+" ");
		}
	}
}