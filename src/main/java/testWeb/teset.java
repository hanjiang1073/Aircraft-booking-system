package testWeb;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","C##hzl","011125");
		Statement stt=conn.createStatement();
		
		String sqlString="select * from \"t_ticket\" where \"userID\"=?";
		PreparedStatement psmt= conn.prepareStatement(sqlString);
//		stt=conn.createStatement();
		psmt.setObject(1, 1);
		
		ResultSet rs=psmt.executeQuery();
		while(rs.next()) {
			String sno =rs.getString(1);
			String sname =rs.getString(2);
			String ssex=rs.getString(3);
			System.out.println(sno+sname+ssex);
		}
	}
}