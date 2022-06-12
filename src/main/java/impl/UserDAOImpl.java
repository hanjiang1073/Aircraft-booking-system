package impl;

import java.util.Date;
import java.util.List;

import dao.BaseDAO;
import pojo.User;


public class UserDAOImpl extends BaseDAO<User>{
	long userID=new Date().getTime();//根据时间戳生成用户id

	public User getUser(String name, String pwd) {
		// TODO 自动生成的方法存根
		return load("select * from \"t_user\" where (\"userName\" = ? or \"phoneNumber\" = ?) and \"password\"=?",name,name,pwd);
	}
	
	//注册
	public String registUser(String name,String phoneNumber,String pwd,String idNumber) {
		//注册用户，默认类型为普通用户 role=1
		String str_userID=String.valueOf(userID);
		executeUpdate("insert into \"t_user\" values (?,?,?,?,?,?)",str_userID ,name,phoneNumber,idNumber,String.valueOf(1),pwd);
		return str_userID;
	}
	
	//列出所有信息
	public List<User> showUser(){
		return super.executeQuery("select * from \"t_user\"", null);
	}
	
	//按页列出所有用户信息
	public List<User> listUser(int pageNo,int pageSize){
		//eg select * from (select rownum no,testtab.* from testtab where rownum<=10) where no>=4; 检索记录4~10行
		return super.executeQuery("select \"userID\",\"userName\",\"phoneNumber\",\"IDNumber\",\"role\",\"password\" from (select rownum no,\"t_user\".* from \"t_user\" where rownum<=?) where no>=?", pageNo*pageSize,(pageNo-1)*pageSize+1);
	}
	//创建用户
	public void addUser(String[]info) {
		System.out.println(info);
		super.executeUpdate("insert into \"t_user\" values (?,?,?,?,?,?)",info );
	}
	
	//删除用户
	public void deleteUser(String userID) {
		super.executeUpdate("delete from \"t_user\" where \"userID\"=?", userID);
	}
	
	//更新用户
	public void updateUser(String role,String userID) {
		super.executeUpdate("update \"t_user\" set \"role\"=? where \"userID\"=?",role,userID);
	}
	
	//条件查询
	public List<User> queryUser(String sql) {
		System.out.println(sql);
		return super.executeQuery(sql, null);
	}
}
