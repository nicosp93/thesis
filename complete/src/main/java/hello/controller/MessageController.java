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
	//Get all of the messages in the messages table
	public ResponseEntity<ArrayList<Message>> getAllMessages() throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getAllMessages() , HttpStatus.OK);
	}
	
	@GetMapping("/getlastmessagesperdevice")
	//Returns the last message for each device in the application
	public ResponseEntity<ArrayList<Message>> getAllMessagesPerDevice() throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getAMessagesPerDev() , HttpStatus.OK);
	}
	
	@GetMapping("/getlastweek")
	//Returns all the messages from the last 7 days
	// filtered by the kind of data
	// and if the user given is not a admin
	//only messages from the sensors that the user owns
	//are going to be retrieved
	public ResponseEntity<ArrayList<Message>> getLastWeek(
			@RequestParam(value="name") String typeOfData,
			@RequestParam(value="username") String username
			) throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getMessagesLastWeek(typeOfData, username) , HttpStatus.OK);
	}
	@GetMapping("/getmsj")
	//Get all of the messages filtered by type of data
	public ResponseEntity<ArrayList<Message>> getAllMessages(
			@RequestParam(value="name") String typeOfData
			) throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getAllMessages(typeOfData) , HttpStatus.OK);
	}
	@GetMapping("/getlast24hours")
	//Same as /getlastweek but with the last 24h hours.
	public ResponseEntity<ArrayList<Message>> getLast24hours(
			@RequestParam(value="name") String typeOfData,
			@RequestParam(value="username") String username
			) throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getLast24hours(typeOfData, username) , HttpStatus.OK);
	}
	
	@GetMapping("/gettypeofdata")
	//Returns all of the types of data stored in the application
	public ResponseEntity<ArrayList<String>> getTypeOfData() throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getTypeOfData() , HttpStatus.OK);
	}
	@GetMapping("/getrelation")
	//Given a user, returns all of his/her sensors names
	public ResponseEntity<ArrayList<String>> getRelation(
			@RequestParam(value="username") String username
			) throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getRelation(username) , HttpStatus.OK);
	}
	@GetMapping("/getlastyear")
	//Returns all the messages from the last year
	// filtered by the kind of data
	// and if the user given is not a admin
	//only messages from the sensors that the user owns
	//are going to be retrieved
	public ResponseEntity<ArrayList<Message>> getLastYear(
			@RequestParam(value="name") String typeOfData,
			@RequestParam(value="username") String username
			) throws MqttException, Exception {
		return new ResponseEntity<>(serviceMessage.getMessagesLastYear(typeOfData, username) , HttpStatus.OK);
	}
   
}
