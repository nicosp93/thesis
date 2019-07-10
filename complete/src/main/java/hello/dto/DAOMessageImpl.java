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

@Component
public class DAOMessageImpl implements DAOMessage{
	
	@Autowired //Connect the class DataSource with the DAOMessageeImpl for the Spring automatic stuff 
	private DataSource datasource;
	
	
	public boolean register(Message message) throws Exception{
			
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
			System.out.println(ps.toString());
			ps.executeUpdate();
			ps.close();
			return true;
		}catch(Exception e){
			throw e;
		}finally {
			if(con!=null) {
			con.close();
			}
			return false;
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
	
	public ArrayList<Message> getAllMessages(String typeOfData) throws Exception {
		ArrayList<Message> messagesList= new ArrayList<>();
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM messages WHERE name = ?");
			ps.setString(1,typeOfData);
			ResultSet rs = ps.executeQuery();
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
	
	public ArrayList<Message> getLast24hours(String typeOfData) throws Exception {
		ArrayList<Message> messagesList= new ArrayList<>();
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * FROM messages WHERE time < DATE_SUB(NOW(), INTERVAL 1 HOUR) AND name = ? and date = CURRENT_DATE() order by time desc");
			ps.setString(1,typeOfData);
			ResultSet rs = ps.executeQuery();
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
	public ArrayList<Message> getLast24hours(String typeOfData, ArrayList<String> devices) throws Exception {
		ArrayList<Message> messagesList= new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		for( int i = 0 ; i < devices.size(); i++ ) {
		    builder.append("?,");
		}
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * FROM messages WHERE time < DATE_SUB(NOW(), INTERVAL 1 HOUR) AND name = ? and date = CURRENT_DATE() and sensor in("  
									 + builder.deleteCharAt( builder.length() -1 ).toString() +  ")order by time desc");
			ps.setString(1,typeOfData);
			int index = 2;
			for( String o : devices ) {
			   ps.setString(  index++, o );
			}
			ResultSet rs = ps.executeQuery();
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
	public ArrayList<Message> getMessagesLastWeek(String typeOfData) throws Exception {
		
		String sql = "select * from messages where date between date_sub(now(),INTERVAL 1 WEEK) and now() ORDER BY `messages`.`date` ASC";
		ArrayList<Message> messagesList= new ArrayList<>();
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from messages where date between date_sub(now(),INTERVAL 1 WEEK) and now() and name= ? ORDER BY `messages`.`date` ASC");
			ps.setString(1,typeOfData);
			ResultSet rs = ps.executeQuery();
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
public ArrayList<Message> getMessagesLastWeek(String typeOfData, ArrayList<String> devices) throws Exception {	
		ArrayList<Message> messagesList= new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		for( int i = 0 ; i < devices.size(); i++ ) {
		    builder.append("?,");
		}
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from messages where date between date_sub(now(),INTERVAL 1 WEEK) and now() and name= ? and sensor in("
				 + builder.deleteCharAt( builder.length() -1 ).toString() + ") ORDER BY `messages`.`date` ASC");
			ps.setString(1,typeOfData);
			int index = 2;
			for( String o : devices ) {
			   ps.setString(  index++, o );
			}
			ResultSet rs = ps.executeQuery();
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
	
	public ArrayList<String> getTypeOfData() throws Exception{
		String sql = "select name from messages where name in ('temperature_1','temperature_2','luminosity_1','luminosity_2','relative_humidity_1','barometric_pressure_1') group by name";
		ArrayList<String> messagesList= new ArrayList<>();
		Connection con=null;
		try {
			con = datasource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				messagesList.add(name);
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
	public ArrayList<Message> getMessagesLastYear(String typeOfData) throws Exception {
			
			ArrayList<Message> messagesList= new ArrayList<>();
			Connection con=null;
			try {
				con = datasource.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from messages where date between date_sub(now(),INTERVAL 1 YEAR) and now() and name= ? ORDER BY `messages`.`date` ASC");
				ps.setString(1,typeOfData);
				ResultSet rs = ps.executeQuery();
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
	public ArrayList<Message> getMessagesLastYear(String typeOfData, ArrayList<String> devices) throws Exception {	
			ArrayList<Message> messagesList= new ArrayList<>();
			StringBuilder builder = new StringBuilder();
	
			for( int i = 0 ; i < devices.size(); i++ ) {
			    builder.append("?,");
			}
			Connection con=null;
			try {
				con = datasource.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from messages where date between date_sub(now(),INTERVAL 1 YEAR) and now() and name= ? and sensor in("
					 + builder.deleteCharAt( builder.length() -1 ).toString() + ") ORDER BY `messages`.`date` ASC");
				ps.setString(1,typeOfData);
				int index = 2;
				for( String o : devices ) {
				   ps.setString(  index++, o );
				}
				ResultSet rs = ps.executeQuery();
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
