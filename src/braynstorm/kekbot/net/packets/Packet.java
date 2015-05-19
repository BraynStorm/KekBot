package braynstorm.kekbot.net.packets;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Packet {
	
	short opcode;
	short direction = 1;
	byte[] data;
	short size;
	
	public Packet(int opcode, byte[] data, boolean toClient){
		if(opcode <= 0xFFFF){
			this.opcode = (short) opcode;
			this.data = data;
			
			if(toClient)
				direction = 0x0010;
			
			this.size = (short) data.length;
			
		}
	}
	
	public byte[] toArray(){
		ByteBuffer b = ByteBuffer.allocate(6 + data.length);
		b.putShort(size);
		b.putShort(opcode);
		b.putShort(direction);
		b.put(data);
		return b.array();
	}


	public static Packet clientMovementPacket(int x, int y){
		
		int xsec = (int) ((x / 192.0) + 135);
		int ysec = (int) ((y / 192.0) + 92);
		
		int xcoord = (x - (xsec - 135) * 192) * 10;
		int ycoord = (y - (ysec - 92) * 192) * 10;
		
		ByteBuffer data = ByteBuffer.allocate(9);
		data.put((byte)1);
		data.put((byte)xsec);
		data.put((byte)ysec);
		data.put((byte) (xcoord & 0x00FF)); // x
		data.put((byte) ((xcoord & 0xFF00) >> 8)); // x
		data.putShort((short) 0); // Z,Z
		data.put((byte) (ycoord & 0x00FF)); // y
		data.put((byte) ((ycoord & 0xFF00) >> 8)); // y
		
		return new Packet(OpCodes.CLIENT_MOVEMENT_PACKET, data.array(), false);
	}
	
	public static Packet clientPasscodePacket(String passcode){
		ByteBuffer data = ByteBuffer.allocate(passcode.length() + 3);
		data.put((byte) 2);
		data.putShort((short) passcode.length());
		data.put(passcode.getBytes(StandardCharsets.US_ASCII));
		
		return new Packet(OpCodes.PASSCODE_PACKET, data.array(), false);
	}
}
