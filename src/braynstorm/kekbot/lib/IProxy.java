package braynstorm.kekbot.lib;

import braynstorm.kekbot.net.packets.Packet;

public interface IProxy {
	public void sendPacket(byte[] data);
	public void sendPacket(String line);
	public void sendPacket(Packet p);
}
