package hello.dao;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import hello.beans.Message;

@Component
public class DAOMessageImpl implements DAOMessage{
	
	@Autowired //Connect the class DataSource with the DAOMessageeImpl for the Spring automatic stuff 
	private DataSource datasource;
	
	
	public void register(Message message) throws Exception{
			
		String sql = "INSERT INTO messages(temperature,sensor,time,date) values(?,?,?,?)";
		Connection con=null;
		try {
			
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,message.getTemperature());
			ps.setString(2, message.getSensor());
			ps.setString(3, message.getTime());
			ps.setString(4, message.getDate());
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

	public ArrayList<Message> getAllMessages() throws Exception {
		String sql = "SELECT * FROM messages;";
		ArrayList<Message> messagesList= new ArrayList<>();
			
			
		Connection con=null;
		try {
			
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				String userid = rs.getString("id");
				String temperature = rs.getString("temperature");
				String sensor = rs.getString("sensor");	
				String time = rs.getString("time");
				String date = rs.getString("date");
				
				messagesList.add(new Message(temperature,sensor,time,date));
			}
			ps.close();
		}catch(Exception e){
			throw e;
		}finally {
			if(con!=null) {
			con.close();
			}
		}
		return messagesList;
	}
	
}
