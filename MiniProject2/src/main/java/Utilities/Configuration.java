package Utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	InputStream input;
	Properties prop;

	public Properties loadConfigPropertiesFile(String filePath) {
		prop = new Properties();
	        try{
	        	input = new FileInputStream(filePath);
	            prop.load(input);
	        }catch(IOException io){
	            io.printStackTrace();
	        }finally {
	            if(input != null){
	                try{
	                    input.close();
	                }catch (IOException e){
	                    e.printStackTrace();
	                }
	            }
	        }
	    return prop;    
	}
	
	public String getProperty(String propertyName) {
		return prop.getProperty(propertyName);
	}
	
	
}
