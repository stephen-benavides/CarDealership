package project_0.layers.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project_0.layers.models.User;
import project_0.layers.util.JDBCConnection;

public class UserDAOImpl implements UserDAO {

	public static Connection conn = JDBCConnection.getConnection();

	public boolean addUser(User u) {
		try {
			String sql = "CALL add_user(?,?)";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, u.getUsername());
			cs.setString(2, u.getPassword());
			cs.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User logging(String username, String password) {
		try {
			String sql = "SELECT role,u_id FROM c_user WHERE username = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			User u = new User();

			if (rs.next()) {
				u.setRole(rs.getInt("ROLE"));
				u.setId(rs.getInt("U_ID"));
				return u;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
