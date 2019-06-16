package hello.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.entity.Message;
import hello.entity.User;
import hello.repository.MessageRepository;
import hello.dto.DAOMessage;
import hello.dto.DAOMessageImpl;
import hello.dto.DAOUserImpl;

@Service
public class ServiceMessageImpl implements ServiceMessage{

	@Autowired
	private DAOMessageImpl daomessageimpl;
	
	@Override
	public void register(String devId, DataMessage data) throws Exception {	
		try {
			//Extract data from the message
        	//String payload= byteArrayToString(((UplinkMessage)data).getPayloadRaw());	        	
        	String date =  ((UplinkMessage)data).getMetadata().getTime().substring(0,10);
        	String time = ((UplinkMessage)data).getMetadata().getTime().substring(11,19);
        	
        	//Parse body from the message
        	Map<String,Object> body;

    		body=((UplinkMessage)data).getPayloadFields();
    		System.out.println(body.toString());
    		//Iterator it = body.keySet().stream().iterator();
    		for(Iterator it = body.keySet().stream().iterator(); it.hasNext();) {
    			String i_key =(String) it.next();
    			String i_value = body.get(i_key).toString();
    			Message message = new Message(date,devId, time, i_value, i_key);
    			System.out.println("Insert in DB| Sensor :"+ message.getSensorId()+" |Name: "+message.getName()+"|Value: "+message.getValue()+ "|TIME: "+message.getTime()+" |DATE: "+message.getDate());
    			daomessageimpl.register(message);
    		}		
		}catch(Exception e){
			throw e;
		}
		
	}
	
	@Override
	public ArrayList<Message> getAllMessages() throws Exception {	
		try {
			return daomessageimpl.getAllMessages();	
		}catch(Exception e){
			throw e;
		}
	}
	@Override
	public ArrayList<Message> getAllMessages(String typeOfData) throws Exception {	
		try {
			return daomessageimpl.getAllMessages(typeOfData);
		}catch(Exception e){
			throw e;
		}
	}
	@Override
	public ArrayList<Message> getLast24hours(String typeOfData, String username) throws Exception {	
		try {
			if (isAdmin(username)) {
				return daomessageimpl.getLast24hours(typeOfData);
			}else {
				ArrayList<String> devices = daomessageimpl.getRelation(username);
				return daomessageimpl.getLast24hours(typeOfData, devices);
			}
			
		}catch(Exception e){
			throw e;
		}
	}
	@Override
	public ArrayList<Message> getAMessagesPerDev() throws Exception {	
		try {
			return daomessageimpl.getLastMessagePerDevices();	
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public ArrayList<String> getTypeOfData() throws Exception{
		try {
			return daomessageimpl.getTypeOfData();	
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public ArrayList<Message> getMessagesLastWeek(String typeOfData, String username) throws Exception {
		try {
			
			if (isAdmin(username)) {
				return daomessageimpl.getMessagesLastWeek(typeOfData);	
			}else {
				ArrayList<String> devices = daomessageimpl.getRelation(username);
				return daomessageimpl.getMessagesLastWeek(typeOfData, devices);	
			}
			
		}catch(Exception e){
			throw e;
		}
	}
	public boolean isAdmin(String username){
		try {
			User user = getUserByUsername(username);
			return user.getAdmin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}	
	
	public User getUserByUsername(String username) throws Exception{
		try {
			return daomessageimpl.findByUsername(username);
		}catch(Exception e){
			throw e;
		}
	}
	public ArrayList<String> getRelation(String username) throws Exception{
		try {
			return daomessageimpl.getRelation(username);
		}catch(Exception e){
			throw e;
		}
	}
	public static String byteArrayToString(byte[] in) {
        char out[] = new char[in.length * 2];
        for (int i = 0; i < in.length; i++) {
            out[i * 2] = "0123456789ABCDEF".charAt((in[i] >> 4) & 15);
            out[i * 2 + 1] = "0123456789ABCDEF".charAt(in[i] & 15);
        }
        return new String(out);
    }
		
}
