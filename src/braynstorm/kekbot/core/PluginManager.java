package braynstorm.kekbot.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.pmw.tinylog.Logger;

import braynstorm.kekbot.lib.IKekBotPlugin;

public class PluginManager {
	
	private static PluginManager instance;
	
	private ArrayList<IKekBotPlugin> plugins;
	private String pluginsDir = "\\plugins";
	
	private PluginManager(){
		//curMenuScrollPane.setViewportView
		plugins = new ArrayList<IKekBotPlugin>();
		pluginsDir = Main.MAIN_FOLDER + pluginsDir;
	}
	
	public void loadPlugins(){
		Logger.info("Loading Plugins...");
		try {
			String line;
			String lines = "";
			Scanner s = new Scanner(new File(Main.MAIN_FOLDER + "\\plugins.ini"));
			while(s.hasNextLine()){
				
				line = s.nextLine();
				
				if(line.startsWith("#"))
					continue;
				
				if(IKekBotPlugin.class.isAssignableFrom(Class.forName(line)) && !lines.contains(line)){
					lines += line;
					IKekBotPlugin plugin = (IKekBotPlugin) Class.forName(line).newInstance();
					plugins.add(plugin);
				}else{
					
					Logger.warn("{} is already loaded! Skipping...", line);
				}
				
			}
			s.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void reloadPlugins(){
		for(IKekBotPlugin plugin : plugins){
			plugin.stop();
		}
		plugins = new ArrayList<IKekBotPlugin>();
		loadPlugins();
	}
	
	public IKekBotPlugin getPluginByName(String name){
		for(IKekBotPlugin plugin : plugins)
			if(plugin.getName().equals(name))
				return plugin;
		return null;
	}
	
	public boolean isPluginLoaded(String name){
		return getPluginByName(name) != null;
	}
	
	public static PluginManager getInstance(){
		if(instance == null)
			instance = new PluginManager();
		return instance;
	}
	
	public Iterator<IKekBotPlugin> getIterator(){
		return plugins.iterator();
	}
}
