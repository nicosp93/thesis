package hello.service;


import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.thethingsnetwork.data.common.messages.DataMessage;

import hello.entity.Message;
import hello.entity.User;


public interface ServiceMessage {

	public void register(String devId, DataMessage data)  throws Exception;
	
	public ArrayList<Message> getAllMessages() throws Exception;
	
	public ArrayList<Message> getAMessagesPerDev() throws Exception;
	
	public ArrayList<Message> getMessagesLastWeek(String typeOfData, String username) throws Exception;
	
	public ArrayList<Message> getAllMessages(String typeOfData) throws Exception;
	
	public ArrayList<String> getTypeOfData() throws Exception;
	
	public ArrayList<Message> getLast24hours(String typeOfData, String username) throws Exception;
	
	public User getUserByUsername(String username) throws Exception;
	
	public ArrayList<String> getRelation(String username) throws Exception;
	
	public ArrayList<Message> getMessagesLastYear(String typeOfData, String username) throws Exception;
	
}
