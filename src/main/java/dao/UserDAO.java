package dao;

import pojo.User;

public interface UserDAO {
	User getUser(String name,String pwd);
	int registUser(String name,String phoneNumber,String pwd,String idNumber);
}
