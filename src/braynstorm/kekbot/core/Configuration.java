package braynstorm.kekbot.core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Configuration {
	public static HashMap<String, Object> props;

	private Configuration(){}
	
	@SuppressWarnings("unchecked")
	public static void load(String string) throws FileNotFoundException {
		String absolutePathToFile = Main.MAIN_FOLDER + "\\" + string;
		
		//Read the config file.
		try {
			props = Main.gson.fromJson(new FileReader(absolutePathToFile), HashMap.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void save(String string){
		String absolutePathToFile = Main.MAIN_FOLDER + "\\" + string;
		
		try {
			PrintWriter p = new PrintWriter(absolutePathToFile);
			Main.gson.toJson(props, p);
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
