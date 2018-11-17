package hello;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;


import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.events.EventHandler;
import org.thethingsnetwork.data.common.messages.ActivationMessage;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;
import org.thethingsnetwork.data.common.messages.RawMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;
import org.thethingsnetwork.data.mqtt.Client;

import beans.Message;
import service.*;

@SpringBootApplication
public class Application {
	private final AtomicLong counter = new AtomicLong();
	//private static String payload="emptyPayload";
	 
	private MqttClientPersistence persistence = new MemoryPersistence();
    private final static MqttConnectOptions connOpts2 = new MqttConnectOptions();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Map<Class, List<EventHandler>> handlers = new HashMap<>();
	
    public static void main(String[] args) throws URISyntaxException, MqttException, Exception {
    	
    	
    	String region = "eu";
		String appId = "test-app123";
		String accessKey = "ttn-account-v2.0iPHQyr3gf3pcRSG4wugSNnbp6HIT5qJC4KU07ogrk8";
		
		Client client = new Client(region, appId, accessKey , connOpts2);
	        class Response {

	            private boolean led;

	            public Response(boolean _led) {
	                led = _led;
	            }
	        }

	       /* client.onMessage(null, "led", (String _devId, DataMessage _data) -> {
	            try {
	                RawMessage message = (RawMessage) _data;
	                // Toggle the LED
	                DownlinkMessage response = new DownlinkMessage(0, new Response(!message.asBoolean()));

	                System.out.println("Sending: " + response);
	                client.send(_devId, response);
	            } catch (Exception ex) {
	                System.out.println("Response failed: " + ex.getMessage());
	            }
	        });*/
      
	        //Each time a message is stored in the server execute this
	        client.onMessage((String devId, DataMessage data) ->{ 
	        	//Extract data from the message
	        	String payload= byteArrayToString(((UplinkMessage)data).getPayloadRaw());	        	
	        	String date =  ((UplinkMessage)data).getMetadata().getTime().substring(0,10);
	        	String time = ((UplinkMessage)data).getMetadata().getTime().substring(11,19);
	        	String temperature;
	        	try{
	        		temperature=((UplinkMessage)data).getPayloadFields().toString();
	        	}catch(Exception e){
	        		temperature="-273";
	        	} 
	        	
	        	//Read the configutation of the DB from the spring bean definition
	        	ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
	        	
	        	//Create msj to store in DB
	        	Message message = new Message(temperature,devId,time,date);
	        	//Call the sevice of register in DB
	        	ServiceMessageImpl servicem = (ServiceMessageImpl)appContext.getBean("ServiceMessageImpl");
	        	
	        	try {
	        		servicem.register(message);
	        	}catch(Exception e){
	        		System.out.println(e.getMessage());
	        	}
	        	System.out.println(devId + " |PAYLOAD: "+ payload + " |TIME: "+ time + " |DATE: "+ date + " |TEMPERATURE: "+temperature);
	        
	        });

	        client.onActivation((String _devId, ActivationMessage _data) -> System.out.println("Activation: " + _devId + ", data: " + _data.getDevAddr()));

	        client.onError((Throwable _error) -> System.err.println("error: " + _error.getMessage()));

	        client.onConnected((Connection _client) -> System.out.println("connected !"));
			
	        client.start();
	        
	        SpringApplication.run(Application.class, args);
    	
    }
    public static String byteArrayToString(byte[] in) {
        char out[] = new char[in.length * 2];
        for (int i = 0; i < in.length; i++) {
            out[i * 2] = "0123456789ABCDEF".charAt((in[i] >> 4) & 15);
            out[i * 2 + 1] = "0123456789ABCDEF".charAt(in[i] & 15);
        }
        return new String(out);
    }
   
}
