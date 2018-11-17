package service;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import beans.Message;
import dao.DAOMessage;
import dao.DAOMessageImpl;

@Service
public class ServiceMessageImpl {

	@Autowired
	private DAOMessageImpl daomessageimpl;
    
	public void register(Message message) throws Exception {	
		try {
			daomessageimpl.register(message);
			System.out.println("Insert in DB| Sensor :"+ message.getSensor()+" |Temperature: "+message.getTemperature()+" |TIME: "+message.getTime()+" |DATE"+message.getDate());
		}catch(Exception e){
			throw e;
		}
		
	}
		
}
