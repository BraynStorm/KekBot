package braynstorm.kekbot.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import braynstorm.kekbot.lib.IProxy;
import braynstorm.kekbot.net.packets.Packet;


public class Proxy implements Runnable, IProxy {

	private static int port = 30000;
	
	static Socket proxy;
	static ServerSocket listener;
	
	private static Proxy instance;
	private static Thread proxyThread;
	
	public static Proxy getInstance(){
		if(instance == null)
			instance = new Proxy();
		return instance;
	}
	
	public void connect() throws IOException{
		//Main.mainWindow.getKexyStatusLabel().setText("Not Connected");
		if(proxy == null || !proxy.isBound() || !proxy.isConnected() || proxy.isClosed()){
			if(listener == null || listener.isClosed() || !listener.isBound()){
				listener = new ServerSocket(port);
				proxy = listener.accept();
				proxyThread = new Thread(instance);
				//proxyThread.start();
				//Main.mainWindow.getKexyStatusLabel().setText("Connected");
				listener.close();
			}
		}
	}
	
	private Proxy(){}
	
	public boolean isConnected(){
		return proxy != null && !proxy.isClosed() && proxy.isConnected();
	}
	
	public void sendPacket(byte[] data){
		try{
			proxy.getOutputStream().write(data);
			for(int i = 0; i < data.length; i++){
				System.out.print(Integer.toHexString(data[i]) + " ");
			}
			System.out.println();
			proxy.getOutputStream().flush();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public void sendPacket(Packet p){
		sendPacket(p.toArray());
	}
	
	/**
	 * @param line Formatted like so 7625:1:02 06 00 31 39 32 38 33 37
	 */
	public void sendPacket(String line){
		// Manual Inject a string
		String[] parts = line.split(":");
		
		parts[2] = parts[2].replace(" ", "");
		int direction = Integer.parseInt(parts[1],16);
		int opcode = Integer.parseInt(parts[0], 16);
		int[] data = new int[(parts[2].length() / 2) + 6];
		
		data[0] = ((( data.length - 6) & 0xFF00) >> 8);
		data[1] = (( data.length - 6) & 0x00FF);
		data[2] = ((opcode & 0xFF00) >> 8);
		data[3] = opcode & 0x00FF;
		data[4] = direction & 0xFF00;
		data[5] = direction & 0x00FF;
		
		byte[] stringd = hexStringToByteArray(parts[2]);
		
		for(int i = 0; i < stringd.length; i++){
			data[i + 6] = stringd[i];
		}
		
		sendPacket(intArrToByteArr(data));
	}
	
	
	
	public byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	
	public byte[] intArrToByteArr(int [] arr){
		ByteBuffer buff = ByteBuffer.allocate(arr.length);
		for(int i : arr){
			buff.put((byte)i);
		}
		return buff.array();
	}
	
	@Override
	public void run(){
		try {
			DataInputStream inStream = new DataInputStream(proxy.getInputStream());
			
			while(isConnected()){
				System.out.println("Cycling");
				int size = inStream.readChar();
				
				if(size <= 0)
					continue;
				
				System.out.print(size);
				
				int opcode = inStream.readChar();
				
				if((opcode & 0xFFFF) == 0)
					continue;
				
				System.out.print(" (" + Integer.toHexString(opcode) + ") ");
				
				byte[] data = new byte[size];
				inStream.read(data); // Actually Read it;
				
				if(data.length > 0){
					
					for(byte b : data){
						System.out.print(Integer.toHexString(b) + " ");
					}
					
					System.out.println();
				}

			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	private int readReversedShort(DataInputStream stream) throws IOException{
		return (int)((stream.readByte() << 8) + stream.readByte());
	}
	
	
	
}
