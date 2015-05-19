package braynstorm.kekbot.net.packets;

public final class OpCodes {
	/**
	 *[0] = skyclick (bool)
	  [1] = x sector (byte)
	  [2] = y sector (byte)
	  [4] + [3] = xpos (ushort)
	  [6] + [5] = ypos (ushort)
	  [8] + [7] = zpos (ushort) ?usage
	  x = (xsec - 135) * 192 + (xpos / 10)
	  y = (ysec - 92) * 192 + (ypos / 10)  */
	
	//32(b021i)5c:23:5c:00:01:4e:69:77:07:50:00:dd:05:00
	public static final int SERVER_MOVEMENT_PACKET = 0xB021;
	//32(7021o)01:4e:69:77:07:50:00:dd:05
	public static final int CLIENT_MOVEMENT_PACKET = 0x7021;
	public static final int PASSCODE_PACKET = 0x7625;
	
	
	
}
