package project_0.layers.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project_0.layers.models.Car;
import project_0.layers.util.JDBCConnection;

public class CarDAOImpl implements CarDAO {

	public static Connection conn = JDBCConnection.getConnection();

	public boolean addCar(Car c) {
		try {
			String sql = "CALL add_car(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, c.getName());
			cs.setString(2, c.getColor());
			cs.setString(3, c.getDescription());
			cs.execute();

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public Car getCar(int id) {
		try {
			String sql = "SELECT * FROM car WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Car c = new Car();
				c.setId(rs.getInt("C_ID"));
				c.setName(rs.getString("NAME"));
				c.setColor(rs.getString("COLOR"));
				c.setDescription(rs.getString("DESCRIPTION"));
				return c;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Car getCar(String name) {
		try {
			String sql = "SELECT * FROM car WHERE name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Car c = new Car();
				c.setId(rs.getInt("C_ID"));
				c.setName(rs.getString("NAME"));
				c.setColor(rs.getString("COLOR"));
				c.setDescription(rs.getString("DESCRIPTION"));
				return c;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Car> getAllCars() {
		try {
			String sql = "SELECT * FROM car";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Car> c = new ArrayList<Car>();
			while (rs.next()) {
				c.add(new Car(rs.getInt("C_ID"), rs.getString("NAME"), rs.getString("COLOR"),
						rs.getString("DESCRIPTION")));
			}
			return c;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteCar(int id) {
		try {

			String sql = "DELETE FROM car WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeQuery();

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Car> getMyCars(int u_id) {
		try {
			String sql = "SELECT car.c_id, car.name, car.color, car.description FROM car INNER JOIN car_user ON car_user.c_id = car.c_id WHERE car_user.u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(u_id));
			ResultSet rs = ps.executeQuery();

			List<Car> cars = new ArrayList<Car>();

			while (rs.next()) {
				cars.add(new Car(rs.getInt("C_ID"), rs.getString("NAME"), rs.getString("COLOR"),
						rs.getString("DESCRIPTION")));
			}
			return cars;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
