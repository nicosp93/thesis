package hello.service;


import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.thethingsnetwork.data.common.messages.DataMessage;

import hello.entity.Message;
import hello.entity.User;


public interface ServiceUser {

	public void register(User user)  throws Exception;
	
	public void delete(String username) throws Exception;
	
	public User getUserByUsername(String username) throws Exception;
	
	public ArrayList<User> getAllUsers() throws Exception;
	
	
	
}
