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
import hello.service.passwordencode.PasswordEncoder;
import hello.dto.DAOMessage;
import hello.dto.DAOMessageImpl;
import hello.dto.DAOUserImpl;

@Service
public class ServiceUserImpl implements ServiceUser{

	@Autowired
	private DAOUserImpl daousermpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean register(User user) throws Exception {	
		try {
			User userInDB = daousermpl.findByUsername(user.getUsername());
			if(userInDB == null) {
				daousermpl.register(user);
				return true;
			}else {
				throw new Exception("The user with that username already exist");
			}
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void delete(String username) throws Exception{
		User userInDB = daousermpl.findByUsername(username);
		if(userInDB == null) {
			throw new Exception("The user that you want to remove does not exist");
		}
		if(username.equals(userInDB.getUsername())) {
			daousermpl.delete(username);		
		}else {
			throw new Exception("User fount in DB is not the same one, something wrong happened");
		}
	}
	
	@Override
	public User getUserByUsername(String username) throws Exception {
		try {
			return daousermpl.findByUsername(username);
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public ArrayList<User> getAllUsers() throws Exception {	
		try {
			return daousermpl.getAllUsers();	
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public Boolean login(String username, String password) throws Exception {	
		try {
			User userInDb = getUserByUsername(username);
			if(userInDb != null && passwordEncoder.matches(password, userInDb.getPassword())){
				return true;
			}else {
				return false;
			}
		}catch(Exception e){
			throw e;
		}
	}
	@Override
	public void setRelation(String username, String[] devices) throws Exception{
		try {
			for(int i =0; devices.length > i ; i++) {
				 daousermpl.setRelation(username , devices[i]);	
			}
		}catch(Exception e){
			throw e;
		}
	}
	
		
}
