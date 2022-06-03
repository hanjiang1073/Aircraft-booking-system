package testWeb;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class teset{
	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","C##TKOTKCh","Ch1992");
		Statement stt=conn.createStatement();
		String sqlString="select * from \"usertable\"";
		stt=conn.createStatement();
		ResultSet rs=stt.executeQuery(sqlString);
		while(rs.next()) {
			String sno =rs.getString(1);
			String sname =rs.getString(2);
			String ssex=rs.getString(3);
			System.out.println(sno+sname+ssex);
		}
	}
}