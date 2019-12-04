package application;

public class Packet {
	//This class contains packet objects 
	 private int number;
	 private double time;
	 private String source;
	 private String destination;
	 private String protocol;
	 private int length;
	 private String info;
	 
//	 public Packet(int number,double time) {
//		 this.number = number;
//		 this.time = time;
//		 this.source = source;
//		 this.destination = destination;
//		 this.protocol = protocol;
//		 this.length = length;
//		 this.info = info;
//	 }
	 
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	 
}
