package projektovanje.ostalo;

import java.io.IOException;
import java.util.logging.*;

public class Logovanje {
	private Logger logger;
	private static Handler handler;
	
	static 
	{
		try {
			handler = new FileHandler(PropertyFileUtils.getValue("loggerFajl"), true);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
		
	public Logovanje(Object klasa) {
		logger = Logger.getLogger(klasa.getClass().toString());
		logger.addHandler(handler);
		logger.setLevel(Level.ALL);
		handler.setLevel(Level.ALL);
		}
	
	synchronized public void logujDogadjaj(Level level,Object klasa, String dogadjaj) {
		logger.log(level, dogadjaj, klasa.getClass());
	}

	public Logovanje(){

	}
}
