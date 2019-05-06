package hello.dto;



import hello.entity.Message;
import hello.entity.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface DAOUser  {
	
	public void register(User user) throws Exception;
	
	public ArrayList<User> getAllUsers() throws Exception;
	
}