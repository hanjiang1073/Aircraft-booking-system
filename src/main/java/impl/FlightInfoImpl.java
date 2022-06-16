package impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import dao.BaseDAO;
import pojo.FlightInfo;

public class FlightInfoImpl extends BaseDAO<FlightInfo>{
	
	//查询航班信息
	public List<FlightInfo> findFlight(String idString) throws ClassNotFoundException{
		return super.executeQuery("select * from \"t_flight\"  where \"fno\" =? ", idString);
	}
	
	//查询航班的所有信息
	public FlightInfo findFlightInfo(String fno) {
		FlightInfo f=null;
		String sql="SELECT \"departtime\" \"depatureDate\",\"departtime\",\"arrivetime\",\"departapname\",\"arriveapname\",a1.\"location\" \"depatureAirportCity\",a2.\"location\" \"arrivalAirportCity\",c.\"cname\" \"airlineName\",p.\"ptype\" \"airplaneName\"from \"t_flight\" f,\"t_airport\" a1,\"t_airport\" a2,\"t_airplane\" p,\"t_company\" c where f.\"departapname\"=a1.\"apname\" and f.\"arriveapname\"=a2.\"apname\" and f.\"pno\"=p.\"pno\" and p.\"cname\"=c.\"cname\" and f.\"fno\"=?";
		java.sql.Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;

        try {
            conn = getConn() ;
            psmt = conn.prepareStatement(sql);
            psmt.setObject(1,fno);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            if(rs.next()){
System.out.println("得到结果集");
                f = new FlightInfo();
                String str=rs.getString(1).substring(0,10);
System.out.println(str);               
                f.setDepatureDate(str);
                str=rs.getString(2).substring(11, 16);
System.out.println(str);
                f.setDeparttime(str);
                str=rs.getString(3).substring(11, 16);
System.out.println(str);   
                f.setArrivetime(str);
                f.setDepartapname(rs.getString(4));
                f.setArriveapname(rs.getString(5));
                f.setDepatureAirportCity(rs.getString(6));
                f.setArrivalAirportCity(rs.getString(7));
                f.setAirlineName(rs.getString(8));
                f.setAirplaneName(rs.getString(9));
                
                return f;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs,psmt,conn);
        }
        return f;
	}
	
}