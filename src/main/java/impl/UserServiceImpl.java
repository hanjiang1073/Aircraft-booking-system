package impl;

import dao.UserDAO;
import pojo.User;
import service.UserService;

public class UserServiceImpl implements UserService{

	private UserDAO userDAO;
	@Override
	public User login(String name, String pwd) {
		// TODO 自动生成的方法存根
		return userDAO.getUser(name,pwd);
	}
	
}
