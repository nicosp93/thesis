package hello.dto;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import hello.entity.Message;
import hello.entity.User;
import hello.service.passwordencode.PasswordEncoder;

@Component
public class DAOUserImpl implements DAOUser{
	
	@Autowired //Connect the class DataSource with the DAOMessageeImpl for the Spring automatic stuff 
	private DataSource datasource;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	public void register(User user) throws Exception{
		String sql = "INSERT INTO users(admin, creation_time , first_name, last_name, username, password) values(?,?,?,?,?,?)";
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setBoolean(1,user.getAdmin());
			ps.setDate(2,user.getCreationTime());
			ps.setString(3,user.getFirstName());
			ps.setString(4,user.getLastName());
			ps.setString(5,user.getUsername());
			ps.setString(6,passEncoder.encode(user.getPassword()));
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			throw e;
		}finally {
			if(con!=null) {
			con.close();
			}
		}
	}
	public void setRelation(String username ,String device) throws Exception {
		String sql = "INSERT INTO relations(username, device) values(?,?)";
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,device);
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			throw e;
		}finally {
			if(con!=null) {
			con.close();
			}
		}
	}
	
	
	public void delete(String username) throws Exception{
		String sql = "DELETE FROM users WHERE username = ?";
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,username);
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			throw e;
		}finally {
			if(con!=null) {
			con.close();
			}
		}
	}
	
	public User findByUsername(String username){
		String sql = "SELECT * FROM users WHERE username=? ";
		ArrayList<User> userList= new ArrayList<>();
		Connection con=null;
		User userFromDB = null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Boolean admin = rs.getBoolean("admin");
				Date creationTime = rs.getDate("creation_time");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");	
				String userName = rs.getString("username");
				String password = rs.getString("password");
				
				userFromDB = new User(admin, creationTime, firstName, lastName, username, password);
			}
			ps.close();
			return userFromDB;
		}catch(Exception e){
			return null;
		}finally {
			if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}

	public ArrayList<User> getAllUsers() throws Exception {
		String sql = "SELECT * FROM users";
		ArrayList<User> userList= new ArrayList<>();
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				Boolean admin = rs.getBoolean("admin");
				Date creationTime = rs.getDate("creation_time");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");	
				String username = rs.getString("username");
				
				userList.add(new User(admin,creationTime,firstName,lastName,username));
			}
			ps.close();
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}finally {
			if(con!=null) {
			con.close();
			}
		}
		return userList;
	}
	public ArrayList<String> getRelation(String username){
		ArrayList<String> relationList= new ArrayList<>();
		Connection con=null;
		User userFromDB = null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM relations WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String relation = rs.getString("device");
				relationList.add(relation);
			}
			ps.close();
			return relationList;
		}catch(Exception e){
			return null;
		}finally {
			if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
	
}
