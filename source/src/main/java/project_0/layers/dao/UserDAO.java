package project_0.layers.dao;

import project_0.layers.models.User;

public interface UserDAO {

	/*
	 * user - can access the database user - can interact with database (cars and
	 * offers) user - can be assigned a role in the database
	 */

	// User can register.
	public boolean addUser(User u);

	// User can logging.
	public User logging(String username, String password);

}