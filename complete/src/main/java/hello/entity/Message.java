package hello.entity;

import java.io.Serializable;

import javax.persistence.*;

import hello.dto.MessageData;

@Entity
@Table(name="messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="date")
	private String date;
	
	@Column(name="sensor")
	private String sensorId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="value")
	private String value;
	
	@Column(name="time")
	private String time;
	
	public Message  (String d, String s, String time,String value, String name ) {
		this.date = d;
		this.sensorId = s;
		this.value = value;
		this.name = name;
		this.time = time;
	}
	
	public Message () {	
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}