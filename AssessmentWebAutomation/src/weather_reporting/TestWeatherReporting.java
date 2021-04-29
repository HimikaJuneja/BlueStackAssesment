package weather_reporting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestWeatherReporting {
	
	private JSONObject inputJSON ;
	
	public TestWeatherReporting() {
		inputJSON = getInputJSON();
	}
	
	public void run() throws Exception {
		
		//Variance
        long variance = (long) inputJSON.get("Variance");
        //int variance = (int)varianceS;
        
        //Cities
        JSONArray cities = (JSONArray)inputJSON.get("City");
        
        
        HashMap<String, Object> result = compareTemperatureFromUIandAPI(cities, variance);
        logResult(result);
        
	}
	
	private JSONObject getInputJSON(){
	
		try {
			JSONParser parser = new JSONParser();
			Object obj;
			obj = parser.parse(new FileReader("src\\weather_reporting\\Input.json"));
			return (JSONObject)obj;
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find Input.json");
		} catch (IOException e) {
			System.out.println("Unable to read Input.json");
		} catch (ParseException e) {
			System.out.println("Unable to parse Input.json");
		}	finally{
			System.out.println("Testing Failed");
		}	
		return null;
	}
	
	
	
	private HashMap<String, Object> compareTemperatureFromUIandAPI(JSONArray cities,long variance) throws Exception{
		
			HashMap<String, Object> citiesResult = new HashMap<String, Object>();
		  	Iterator cityIterator = cities.iterator();
	  
	        while (cityIterator.hasNext()) {
	        	String city = (String)cityIterator.next();
	        	float temperatureValueAPI = new APITemperatureGenerator().getTemperatureFor(city) ;
	    		float temperatureValueUI = new UITemperatureGenerator().getTemperatureFor(city);
	    		float temperatureDifference = Math.abs(temperatureValueUI - temperatureValueAPI);
	    		if(temperatureDifference <= variance) {
	    			 citiesResult.put(city, "Successfully matched weather for "+city+"") ;
	    		}else {
	    			citiesResult.put(city, new MatcherException("Weather for "+city+" does not match in UI & API"));
	    		}
	        }
	        return citiesResult;
	}
	
	private void logResult(HashMap<String, Object> result) {
		for(String city : result.keySet()) {
			System.out.println(result.get(city));
		}
	}
}
