package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
	ArrayList<Packet> packets= new ArrayList<Packet>();
	public ArrayList<Packet> readCsv(String filePath) {
		BufferedReader reader = null;
		try {
//			List<Packet> packets = new ArrayList<Packet>();
			String line = "";
			reader = new BufferedReader(new FileReader(filePath));
			reader.readLine();

			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");

				if (fields.length > 0) {
					Packet packet = new Packet();
					packet.setNumber(Integer.parseInt(fields[0].replace("\"", "")));
					packet.setTime(Double.parseDouble(fields[1].replace("\"", "")));
					packet.setSource(fields[2].replace("\"", ""));
					packet.setDestination(fields[3].replace("\"", ""));
					packet.setProtocol(fields[4].replace("\"", ""));
					packet.setLength(Integer.parseInt(fields[5].replace("\"", "")));
					packet.setInfo(fields[6].replace("\"", ""));
					packets.add(packet);
				}
			}
			// prints out read csv data to the console, this should be removed later
			for (Packet p : packets) {
				System.out.println(p.getNumber() + " " + p.getTime() + " " + p.getSource() + " " + p.getDestination()
						+ " " + p.getProtocol() + " " + p.getLength() + " " + p.getInfo());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return packets;
	}
}
