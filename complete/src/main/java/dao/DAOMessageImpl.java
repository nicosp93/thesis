package dao;


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
import beans.Message;

@Component
public class DAOMessageImpl{
	
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


	
}
