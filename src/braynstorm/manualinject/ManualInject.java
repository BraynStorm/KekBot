package braynstorm.manualinject;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import braynstorm.kekbot.lib.IKekBotPlugin;
import braynstorm.kekbot.lib.IProxy;


public class ManualInject implements IKekBotPlugin {
	private static ManualInject instance = null;
	private GUIManualInject gui;
	public IProxy proxy;
	
	public static HashMap<String, JPanel> packets = new HashMap<String, JPanel>();
	public static ManualInject getInstance(){return instance;}
	public ManualInject(){
		instance = this;
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		packets.put("Movement Packet", new PanelPacketMove());
		packets.put("Custom Packet", new PanelCustomPacket());
		
		gui = new GUIManualInject();
		
		for(Entry<String, JPanel> entry : packets.entrySet()){
			gui.getCmbboxPacketType().insertItemAt(entry.getKey(), gui.getCmbboxPacketType().getItemCount());
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Manual Inject";
	}

	@Override
	public JPanel getJPanel() {
		return (JPanel)gui;
	}

	@Override
	public JToggleButton getToggleButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean requiresToggleButton() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setProxy(IProxy proxy) {
		this.proxy = proxy;
		
	}
	
}
