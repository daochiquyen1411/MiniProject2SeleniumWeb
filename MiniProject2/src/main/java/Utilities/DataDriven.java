package Utilities;


import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataDriven {
	
	@DataProvider(name = "jsonLoginUser")
	public static Object[][] jsonCredentialForAddHotelTest() throws IOException, ParseException {
		String[] elementName = {"username","password"};
		return JsonHelper.getDataFromJson("src/test/java/TestData/SauceUser.json", "UserInfo", elementName);
	}
}
