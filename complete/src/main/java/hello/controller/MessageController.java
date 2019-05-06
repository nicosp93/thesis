package hello.controller;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import hello.entity.Message;
import hello.repository.MessageRepository;
import hello.service.ServiceMessage;

@RestController
public class MessageController {

	@Autowired
	private ServiceMessage serviceMessage;
	
	@Autowired
	private final MessageRepository msjRepository;
	
	MessageController(MessageRepository msjRepository){
		this.msjRepository = msjRepository;
	}
  
	@GetMapping("/getmessages")
	public ArrayList<Message> getAllMessages() throws MqttException, Exception {
		return serviceMessage.getAllMessages();

	}

   
}
