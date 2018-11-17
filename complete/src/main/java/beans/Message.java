package beans;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Component
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;  
	
	private String temperature;
	private String sensor;
	private String time;
	private String date;
	
	
	
	public Message (String temp, String sensor) {
		this.temperature = temp;
		this.sensor = sensor;
		
	}
	public Message(String temperature, String devId, String time, String date){
		this.sensor = devId;
		if (temperature.equals("-273")) {//If its a test message.
			this.temperature = temperature;
		}else { // Ommit {temperature_1=21.6} from the json and only get the value
			this.temperature = temperature.substring(15, temperature.length()-1);
		}
		this.time=time;
		this.date=date;
	}
	public Message() {
		
	}

	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		
		if (temperature.equals("-273")) {//If its a test message.
			this.temperature = temperature;
		}else { // Ommit {temperature_1=21.6} from the json and only get the value
			this.temperature = temperature.substring(15, temperature.length()-1);
		}
	}
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}