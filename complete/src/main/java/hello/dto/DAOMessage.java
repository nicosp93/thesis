package hello.dto;



import hello.entity.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public interface DAOMessage  {
	
	public boolean register(Message message) throws Exception;
	
	public ArrayList<Message> getAllMessages() throws Exception;
	
	public ArrayList<Message> getLastMessagePerDevices() throws Exception;
	
	public ArrayList<Message> getMessagesLastWeek(String typeOfData) throws Exception;

	
}