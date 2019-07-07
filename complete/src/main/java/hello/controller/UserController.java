package hello.controller;

import java.sql.Date;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hello.entity.User;
import hello.service.ServiceUser;

@RestController
public class UserController {

	@Autowired
	private ServiceUser serviceUser;
  
	@GetMapping("/getusers")
	// Returns all users from the users table
	public ArrayList<User> getAllUsers() throws MqttException, Exception {
		return serviceUser.getAllUsers();
	}
	
	@RequestMapping("/register")
	//Register a new user with the data provided
	public boolean register(
			@RequestParam(value="admin") Boolean admin,
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="username") String username,
			@RequestParam(value="pasword") String password
			) throws Exception {
		User user = new User(admin, new Date(System.currentTimeMillis()) ,firstName, lastName, username, password);
		return serviceUser.register(user);
    }
	
	@RequestMapping("/delete")
	// Delete a user given a username
	public void delete(
			@RequestParam(value="username") String username
			) throws Exception {
		serviceUser.delete(username);
    }
	
	@RequestMapping("/getuser")
	//Returns all the information from a user
	//providing his/her username
	public User getUserByUsername(
			@RequestParam(value="username") String username
			) throws MqttException, Exception {
		return serviceUser.getUserByUsername(username);
	}
	
	
	@RequestMapping("/login")
	// Validates if the password matches the
	// whith the user associated
	public Boolean login(
			@RequestParam(value="username") String username,
			@RequestParam(value="password") String password
			) throws MqttException, Exception {
		return serviceUser.login(username, password);
	}
	@RequestMapping("/setrelation")
	// Save in the relations table
	//all of the sensors that this user owns
	public void setRelation(
			@RequestParam(value="username") String username,
			@RequestParam(value="devices") String[] devices
			) throws MqttException, Exception {
		 serviceUser.setRelation(username, devices);
	}
 
}
