package hello.dto;

import hello.entity.Message;


public class MessageData {

	private String date;
	private String temperature;
	private String sensorId;
	private String time;
	
	public MessageData(String temperature, String devId, String string, String string2) {
		this.temperature = temperature;
		this.sensorId = devId;
		this.time = string;
		this.date = string2;
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public MessageData(Message msg) {
		MessageData ret = new MessageData("", msg.getSensorId(), msg.getTime(), msg.getDate());
	}

	@Override
	public String toString() {
		return "MessageData [date=" + date + ", temperature=" + temperature + ", sensorId=" + sensorId + ", time="
				+ time + "]";
	}
}
