package hello.dto;


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
import hello.entity.Message;

@Component
public class DAOMessageImpl implements DAOMessage{
	
	@Autowired //Connect the class DataSource with the DAOMessageeImpl for the Spring automatic stuff 
	private DataSource datasource;
	
	
	public void register(Message message) throws Exception{
			
		String sql = "INSERT INTO messages(name, value ,sensor,time,date) values(?,?,?,?,?)";
		Connection con=null;
		try {
			
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,message.getName());
			ps.setString(2,message.getValue());
			ps.setString(3,message.getSensorId());
			ps.setString(4,message.getTime());
			ps.setString(5,message.getDate());
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
		String sql = "SELECT * FROM messages order by id desc";
		ArrayList<Message> messagesList= new ArrayList<>();
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				String userid = rs.getString("id");
				String date = rs.getString("date");
				String name = rs.getString("name");
				String sensor = rs.getString("sensor");	
				String value = rs.getString("value");
				String time = rs.getString("time");
				messagesList.add(new Message(date,sensor,time,value,name));
			}
			ps.close();
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}finally {
			if(con!=null) {
			con.close();
			}
		}
		return messagesList;
	}
	
	public ArrayList<Message> getLastMessagePerDevices() throws Exception {
		String sql = "select * from messages where id in (SELECT MAX(id) FROM messages GROUP BY sensor)";
		ArrayList<Message> messagesList= new ArrayList<>();
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				String userid = rs.getString("id");
				String date = rs.getString("date");
				String name = rs.getString("name");
				String sensor = rs.getString("sensor");	
				String value = rs.getString("value");
				String time = rs.getString("time");
				messagesList.add(new Message(date,sensor,time,value,name));
			}
			ps.close();
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}finally {
			if(con!=null) {
			con.close();
			}
		}
		return messagesList;
	}
}
