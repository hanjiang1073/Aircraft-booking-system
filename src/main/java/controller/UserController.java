package controller;

import pojo.User;
import service.UserService;

public class UserController {
	private UserService userService;
	public String login(String name,String pwd) {
		User user=userService.login(name, pwd);
		System.out.println(user);
		return null;
		
	}
}
