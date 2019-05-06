package hello.dto;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import hello.entity.Message;
import hello.entity.User;

@Component
public class DAOUserImpl implements DAOUser{
	
	@Autowired //Connect the class DataSource with the DAOMessageeImpl for the Spring automatic stuff 
	private DataSource datasource;
	
	
	public void register(User user) throws Exception{
			
		String sql = "INSERT INTO users(admin, creation_time , first_name, last_namee, username) values(?,?,?,?,?)";
		Connection con=null;
		try {
			
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setBoolean(1,user.getAdmin());
			ps.setDate(2,user.getCreationTime());
			ps.setString(3,user.getFirstName());
			ps.setString(4,user.getLastName());
			ps.setString(5,user.getUsername());
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
	
}
