package hello.dao;



import hello.beans.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public interface DAOMessage  {
	
	public void register(Message message) throws Exception;
	
	public ArrayList<Message> getAllMessages() throws Exception;
	
}