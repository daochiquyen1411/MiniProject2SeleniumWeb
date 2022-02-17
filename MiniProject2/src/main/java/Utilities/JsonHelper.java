package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHelper {
	  public static Object[][] getDataFromJson(String filePath, String jsonName, String elementName[]) throws IOException, ParseException {
		  	  	
			JSONParser parser = new JSONParser();

	        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));//path to the JSON file.

	        String json = jsonObject.toJSONString();


			JSONArray jsonArray = (JSONArray) jsonObject.get(jsonName);
		  
			String arr[] = new String[jsonArray.size()];
			Object[][] tabArray = new Object[jsonArray.size()][elementName.length];

			
			for(int i = 0; i < jsonArray.size() ; i++){
				
				JSONObject users = (JSONObject) jsonArray.get(i);

				
				for (int j = 0; j < elementName.length ; j++) {
					tabArray[i][j] = (String) users.get(elementName[j]);
				}
			}
		
			return tabArray;
			
	  }
	  
	  public void findElementNameInJson(String json) {
		  	
	  }
	  
}
