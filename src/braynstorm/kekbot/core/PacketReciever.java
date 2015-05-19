package braynstorm.kekbot.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


public class PacketReciever implements Runnable {

	InputStream inStream;
	DataInputStream reader;
	public boolean running = false;
	public PacketReciever(InputStream inStream) {
		this.inStream = inStream;
		reader = new DataInputStream(inStream);
		running = true;
	}

	@Override
	public void run() {
		while(running){
			try {
				reader.readInt();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

}
