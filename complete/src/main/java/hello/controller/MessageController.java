package hello.controller;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import hello.entity.Message;
import hello.repository.MessageRepository;
import hello.service.MyProperties;
import hello.service.ServiceMessage;

@RestController
public class MessageController {

	@Autowired
	private ServiceMessage serviceMessage;
  
	@Autowired
    private MyProperties myConfig;
	
	
	@GetMapping("/getmessages")
	public ResponseEntity<ArrayList<Message>> getAllMessages() throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getAllMessages() , HttpStatus.OK);
	}

   
}
