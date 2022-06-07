package impl;

import java.util.Date;

import dao.BaseDAO;
import pojo.User;

public class UserDAOImpl extends BaseDAO<User>{
	long userID=new Date().getTime();//根据时间戳生成用户id

	public User getUser(String name, String pwd) {
		// TODO 自动生成的方法存根
		return load("select * from \"t_user\" where (\"userName\" = ? or \"phoneNumber\" = ?) and \"password\"=?",name,name,pwd);
	}
	
	//注册
	public int registUser(String name,String phoneNumber,String pwd,String idNumber) {
		//注册用户，默认类型为普通用户 role=1
		return executeUpdate("insert into \"t_user\" values (?,?,?,?,?,?)", String.valueOf(userID),name,phoneNumber,idNumber,String.valueOf(1),pwd);
	}
}
