package hello.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.Greeting;
import hello.entity.Message;
import hello.entity.User;
import hello.repository.MessageRepository;
import hello.service.ServiceMessage;
import hello.service.ServiceUser;

@RestController
public class UserController {

	@Autowired
	private ServiceUser serviceUser;
  
	@GetMapping("/getusers")
	public ArrayList<User> getAllUsers() throws MqttException, Exception {
		return serviceUser.getAllUsers();
	}
	
	@RequestMapping("/register")
	public void register(
			@RequestParam(value="admin") Boolean admin,
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="username") String username
			) throws Exception {
		User user = new User(admin, new Date(System.currentTimeMillis()) ,firstName, lastName, username);
		serviceUser.register(user);
    }
	
	@RequestMapping("/delete")
	public void delete(
			@RequestParam(value="username") String username
			) throws Exception {
		serviceUser.delete(username);
    }

   
}
