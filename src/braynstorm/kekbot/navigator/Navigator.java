package braynstorm.kekbot.navigator;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pmw.tinylog.Logger;

import braynstorm.kekbot.lib.IKekBotPlugin;
import braynstorm.kekbot.lib.IProxy;
import braynstorm.kekbot.navigator.navmesh.NavMeshRegion;
import braynstorm.kekbot.navigator.navmesh.Node;



public class Navigator implements IKekBotPlugin, Runnable {
	public IProxy proxy;
	GUINavigator gui;
	JToggleButton tb;
	
	static NavMeshRegion navEurope;
	public static int curX;
	public static int curY;
	
	public Navigator(){
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logger.info("Initializing Navigator...");
		gui = new GUINavigator();
		tb = new JToggleButton();
		tb.setText(getName());
		
		//Load navmesh regions
		try {
			navEurope = new NavMeshRegion("68x110-81x96.nvmr", (byte) 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		Logger.debug("Thread ran!");
	}

	@Override
	public String getName() {
		return "Navigator";
	}

	@Override
	public JPanel getJPanel() {
		return gui;
	}

	@Override
	public JToggleButton getToggleButton() {
		return tb;
	}

	@Override
	public boolean requiresToggleButton() {
		return true;
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

	public static Node getNodeFromPoint(Point point) {
		return navEurope.getNode(point.x, point.y);
	}

	
	
	
}
