package braynstorm.kekbot.core;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.FileWriter;

import braynstorm.kekbot.lib.GameState;
import braynstorm.kekbot.lib.IKekBotPlugin;
import braynstorm.kekbot.navigator.Navigator;
import braynstorm.kekbot.net.Proxy;
import braynstorm.manualinject.ManualInject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	public final static String MAIN_FOLDER = ((new File(System.getProperty("java.class.path"))).getAbsoluteFile().getParentFile().toString()).split(";")[0];
	public static GUIMain mainWindow;
	public static Gson gson = new Gson();
	public static GsonBuilder gsonB = new GsonBuilder();
	
	
	//Fix them to be semi-plugins
	private static HashMap<String, IKekBotPlugin> plugins;
	
	private static GameState gameState;
	
	private Main(){
		gsonB.setPrettyPrinting();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss");
		Calendar cal = Calendar.getInstance(); // dateFormat.format(cal.getTime()) 
		Configurator.defaultConfig().addWriter(new FileWriter(MAIN_FOLDER + "\\logs\\" + dateFormat.format(cal.getTime())  + ".log" )).activate();

		
		Logger.info("Initializing.");
		gameState = new GameState();
		plugins = new HashMap<String, IKekBotPlugin>();
		
		plugins.put("Manual Inject", new ManualInject());
		plugins.put("Navigator", new Navigator());
		
		
		Logger.info("Initializing GUI.");
		
		try {
			Proxy.getInstance().connect();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Launch Window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow = new GUIMain();
					mainWindow.frmKekbot.setVisible(true);
					
					/*String plugins = "";
					Iterator<IKekBotPlugin> it = PluginManager.getInstance().getIterator();
					while(it.hasNext()){
						IKekBotPlugin plugin = it.next();
						plugin.setProxy(Proxy.getInstance());
						plugins += plugin.getName() + "\n";
					}*/
					
					//mainWindow.getListMenus().setListData(plugins.split("\n"));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		
	}
	
	public static void main(String [] args){
		new Main();
	}

	public static String getGameStateProp(String propName) {
		return gameState.props.get(propName);
	}
	
	public static boolean gameStateHasProp(String propName){
		return gameState.props.containsKey(propName);
	}

	public static IKekBotPlugin getPluginByName(String selectedValue) {
		return plugins.get(selectedValue);
	}

}
