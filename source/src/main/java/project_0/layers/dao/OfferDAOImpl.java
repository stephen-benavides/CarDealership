package project_0.layers.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project_0.layers.models.Offer;
import project_0.layers.models.User;
import project_0.layers.service.OfferService;
import project_0.layers.util.JDBCConnection;

public class OfferDAOImpl implements OfferDAO {

	Connection conn = JDBCConnection.getConnection();

	public boolean addOffer(Offer o) {
		try {
			String sql = "CALL add_offer(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, o.getUser_id());
			cs.setInt(2, o.getCar_id());
			cs.setInt(3, o.getAmount());

			cs.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	// I can invoke this as a system to remove all offers
	public Offer reviewOffer(int c_id) {
		try {
			String sql = "CALL reject_offers(?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, c_id);
			cs.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Offer> getPayments(int u_id) {
		try {
			String sql = "SELECT * FROM offer WHERE u_id = ? AND accepted = 1"; // 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);//
			ResultSet rs = ps.executeQuery();
			
			List<Offer> amount = new ArrayList<Offer>();
			while (rs.next()) {
				amount.add(new Offer(rs.getInt("O_ID"), rs.getInt("U_ID"), rs.getInt("C_ID"), rs.getInt("AMOUNT"),
						rs.getInt("ACCEPTED")));
			}
			return amount;

		} catch (SQLException e) {
			System.out.println("There are no cars in your personal lot");
		}
		return null;
	}

	@Override
	public List<Offer> getAllOffers() {
		try {
			String sql = "SELECT * FROM offer"; // --WHERE accepted = 0
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Offer> allOffers = new ArrayList<Offer>();
			while (rs.next()) {
				allOffers.add(new Offer(rs.getInt("O_ID"), rs.getInt("U_ID"), rs.getInt("C_ID"), rs.getInt("AMOUNT"),
						rs.getInt("ACCEPTED")));
			}
			return allOffers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean acceptOffer(int o_id, int c_id) {

		try {
			String sql1 = "UPDATE offer SET accepted = 1 WHERE o_id = ?";
			String sql2 = "SELECT u_id FROM offer WHERE o_id = ?"; //This gives me the user id
			String sql3 = "INSERT INTO car_user VALUES (?, ?)";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			//First SQL Statement
			ps1.setInt(1, o_id);
			ps1.execute();
			//Second SQL Statement
			ps2.setInt(1, o_id);
			ResultSet rs2 = ps2.executeQuery();
			User u = new User();
			if(rs2.next()) {
				u.setId(rs2.getInt("U_ID"));
			}
			//Third SQL Statement
			ps3.setInt(1, u.getId());
			ps3.setInt(2, c_id);
			ps3.execute();
	
			OfferService.reviewOffer(c_id);
			//Implement 2 methods from char 
			
			//method to add ownership -> make another dao to insert into car user where u_id and u_id
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}
	
}
