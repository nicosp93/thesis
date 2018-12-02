package hello.service;


import java.util.ArrayList;

import hello.beans.Message;

public interface ServiceMessage {

	public void register(Message message) throws Exception;
	public ArrayList<Message> getAllMessages() throws Exception;
}
