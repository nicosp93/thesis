package hello.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
public class ServiceUserImpl implements ServiceUser{

	@Autowired
	private DAOUserImpl daousermpl;
	
	
	@Override
	public void register(User user) throws Exception {	
		
		
	}
	
	@Override
	public ArrayList<User> getAllUsers() throws Exception {	
		try {
			return daousermpl.getAllUsers();	
		}catch(Exception e){
			throw e;
		}
	}
	
		
}
