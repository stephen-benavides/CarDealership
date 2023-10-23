package project_0.layers.service;

import project_0.layers.dao.UserDAO;
import project_0.layers.dao.UserDAOImpl;
import project_0.layers.models.User;

public class UserService {

	public static UserDAO ud = new UserDAOImpl();

	public static boolean addUser(User u) {
		return ud.addUser(u);
	}

	public static User logging(String username, String password) {
		return ud.logging(username, password);
	}

}
