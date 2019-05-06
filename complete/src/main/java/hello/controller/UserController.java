package hello.controller;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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

   
}
