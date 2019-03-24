package hello.controller;


import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.events.EventHandler;
import org.thethingsnetwork.data.mqtt.Client;

import hello.dto.MessageData;
import hello.entity.Message;
import hello.facade.MessageFacade;
import hello.repository.MessageRepository;
import hello.service.ServiceMessage;

import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;
import org.thethingsnetwork.data.common.messages.RawMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;
import org.thethingsnetwork.data.common.messages.ActivationMessage;

import java.util.concurrent.atomic.AtomicLong;




@RestController
public class MessageController {

	@Autowired
	private ServiceMessage serviceMessage;
	
	private final MessageRepository msjRepository;
	
	MessageController(MessageRepository msjRepository){
		this.msjRepository = msjRepository;
	}
  
	@GetMapping("/getmessages")
	public List<Message> getAllMessages() throws MqttException, Exception {
		System.out.println("entro en el controller");
		return msjRepository.findAll();

	}

   
}
