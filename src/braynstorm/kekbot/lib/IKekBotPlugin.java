package braynstorm.kekbot.lib;

import javax.swing.JPanel;
import javax.swing.JToggleButton;


public interface IKekBotPlugin extends Runnable{
	
	public String getName();
	public JPanel getJPanel();
	public JToggleButton getToggleButton();
	
	public void setProxy(IProxy proxy);
	
	public boolean requiresToggleButton();

	public void start();
	public void stop();
}
