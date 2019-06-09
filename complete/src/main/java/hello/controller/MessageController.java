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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/getlastmessagesperdevice")
	public ResponseEntity<ArrayList<Message>> getAllMessagesPerDevice() throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getAMessagesPerDev() , HttpStatus.OK);
	}
	
	@GetMapping("/getlastweek")
	public ResponseEntity<ArrayList<Message>> getLastWeek(
			@RequestParam(value="name") String typeOfData
			) throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getMessagesLastWeek(typeOfData) , HttpStatus.OK);
	}
	@GetMapping("/getmsj")
	public ResponseEntity<ArrayList<Message>> getAllMessages(
			@RequestParam(value="name") String typeOfData
			) throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getAllMessages(typeOfData) , HttpStatus.OK);
	}
	@GetMapping("/getlast24hours")
	public ResponseEntity<ArrayList<Message>> getLast24hours(
			@RequestParam(value="name") String typeOfData
			) throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getLast24hours(typeOfData) , HttpStatus.OK);
	}
	
	@GetMapping("/gettypeofdata")
	public ResponseEntity<ArrayList<String>> getTypeOfData() throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getTypeOfData() , HttpStatus.OK);
	}

   
}
