package service;

import pojo.User;

public interface UserService {
	User login(String name,String pwd);
}
