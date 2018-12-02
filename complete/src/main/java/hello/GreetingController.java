package hello;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.events.EventHandler;
import org.thethingsnetwork.data.mqtt.Client;

import hello.beans.Message;
import hello.dao.DAOMessageImpl;
import hello.service.ServiceMessage;

import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;
import org.thethingsnetwork.data.common.messages.RawMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;
import org.thethingsnetwork.data.common.messages.ActivationMessage;

import java.util.concurrent.atomic.AtomicLong;




@RestController
public class GreetingController {

	
	@Autowired
	ServiceMessage service;
	
	/*private final AtomicLong counter = new AtomicLong();
	private static String payload="emptyPayload";
	 
	private MqttClientPersistence persistence = new MemoryPersistence();
    private final static MqttConnectOptions connOpts2 = new MqttConnectOptions();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Map<Class, List<EventHandler>> handlers = new HashMap<>();*/
	
    
   // private static String template = "Hello, %s!"+ payload;
	@RequestMapping("/getmessages")
	public ArrayList<Message> getAllMessages() throws MqttException, Exception {
		ArrayList<Message> mejs;
		try {

			mejs = service.getAllMessages();
		}catch(Exception e){
			throw e;
		}
		return mejs;
	}
	
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World")  String name) throws MqttException, Exception {
    	
    	
    	/*String region = "eu";
		String appId = "test-app123";
		String accessKey = "ttn-account-v2.0iPHQyr3gf3pcRSG4wugSNnbp6HIT5qJC4KU07ogrk8";
		
		Client client = new Client(region, appId, accessKey , connOpts2);
	        class Response {

	            private boolean led;

	            public Response(boolean _led) {
	                led = _led;
	            }
	        }

	        client.onMessage(null, "led", (String _devId, DataMessage _data) -> {
	            try {
	                RawMessage message = (RawMessage) _data;
	                // Toggle the LED
	                DownlinkMessage response = new DownlinkMessage(0, new Response(!message.asBoolean()));

	                /**
	                 * If you don't have an encoder payload function:
	                 * client.send(_devId, new Response(0, message.asBoolean() ? new byte[]{0x00} : new byte[]{0x01}));
	                 
	                System.out.println("Sending: " + response);
	                client.send(_devId, response);
	            } catch (Exception ex) {
	                System.out.println("Response failed: " + ex.getMessage());
	            }
	        });

	        
	       
    	
	     // simulate messsage print
	        
	        client.onMessage((String devId, DataMessage data) ->{ 
	        	
	        	 payload= byteArrayToString(((UplinkMessage)data).getPayloadRaw());
	        	String metadata = ((UplinkMessage)data).getMetadata().toString();
	        	String temperature = ((UplinkMessage)data).getPayloadFields().toString();
	        	System.out.println(devId + " |PAYLOAD: "+ payload + " |META: "+ metadata + " |TEMPERATURE: "+temperature);
	        });

	        client.onActivation((String _devId, ActivationMessage _data) -> System.out.println("Activation: " + _devId + ", data: " + _data.getDevAddr()));

	        client.onError((Throwable _error) -> System.err.println("error: " + _error.getMessage()));

	        client.onConnected((Connection _client) -> System.out.println("connected !"));
			
	        client.start();
	        */
	        
        return new Greeting(1,
                            "");
        
    }
    
    
    
    
    
}
