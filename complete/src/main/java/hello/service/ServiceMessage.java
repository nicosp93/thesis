package hello.service;


import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.thethingsnetwork.data.common.messages.DataMessage;

import hello.entity.Message;


public interface ServiceMessage {

	public void register(String devId, DataMessage data)  throws Exception;
	
	public ArrayList<Message> getAllMessages() throws Exception;
	
	
}
