package application;

import java.util.ArrayList;

public class CaptureAnalyzer {
	private ArrayList<Integer> number = new ArrayList<Integer>();
	private ArrayList<Double> time = new ArrayList<Double>();
	private ArrayList<String> source = new ArrayList<String>();
	private ArrayList<String> destination = new ArrayList<String>();
	private ArrayList<String> protocol = new ArrayList<String>();
	private ArrayList<Integer> length = new ArrayList<Integer>();
	private ArrayList<String> info = new ArrayList<String>();


	// This class provides methods for analyzing the parsed csv file
	// An array list of packet objects and/or arraylists of each attribute may be
	// useful
	public CaptureAnalyzer(ArrayList<Packet> packets) {

		for (int i = 0; i < packets.size(); i++) {

			number.add(packets.get(i).getNumber());
			time.add(packets.get(i).getTime());
			source.add(packets.get(i).getSource());
			destination.add(packets.get(i).getDestination());
			protocol.add(packets.get(i).getProtocol());
			length.add(packets.get(i).getLength());
			info.add(packets.get(i).getInfo());
		}
	}

	// display interface type

	// display capture filter

	// display link type
	// display dropped packets percentage
	// display # of packets
	public int numOfPackets() {
		return number.size();
	}

	// display time span
	// display average pps
	public double pps() {
		double maxTime = time.get(numOfPackets() - 1);
		double numPackets = numOfPackets();
		double pps = numPackets / maxTime;
		return pps;
	}

	// display pps in graph

	// display average packet size
	public int avgSize() {
		int size = 0;
		for (int i = 0; i < length.size(); i++) {
			size = size + length.get(i);
		}
		size = size / length.size();
		return size;

	}

	// get percentages of each protocol type
	public int percentProtocols(String type) {
		double count = 0;
		int percent =0;
		for (int i = 0; i < protocol.size(); i++) {
			if (protocol.get(i).contains(type)) {
				count++;
			}
		}

		percent = (int) ((count / numOfPackets())*100);
		
		return percent;

	}

	public ArrayList<Integer> getNumber() {
		return number;
	}

	public void setNumber(ArrayList<Integer> number) {
		this.number = number;
	}

	public ArrayList<Double> getTime() {
		return time;
	}

	public void setTime(ArrayList<Double> time) {
		this.time = time;
	}

	public ArrayList<String> getSource() {
		return source;
	}

	public void setSource(ArrayList<String> source) {
		this.source = source;
	}

	public ArrayList<String> getDestination() {
		return destination;
	}

	public void setDestination(ArrayList<String> destination) {
		this.destination = destination;
	}

	public ArrayList<String> getProtocol() {
		return protocol;
	}

	public void setProtocol(ArrayList<String> protocol) {
		this.protocol = protocol;
	}

	public ArrayList<Integer> getLength() {
		return length;
	}

	public void setLength(ArrayList<Integer> length) {
		this.length = length;
	}

	public ArrayList<String> getInfo() {
		return info;
	}

	public void setInfo(ArrayList<String> info) {
		this.info = info;
	}

}
