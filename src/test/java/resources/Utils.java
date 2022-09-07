package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		
		if(req==null) {
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));  //for log file name=logging.txt auto create by the method FileOutputStream
		req= new RequestSpecBuilder().setBaseUri(GlobalProperties("baseURL")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	  
	}
	
	public static String GlobalProperties(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream FIS = new FileInputStream("D:\\api automation\\ApiFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(FIS);
		return prop.getProperty(key);
	}
	
	public String getJSpath(Response response, String key) {
		
		String resp = response.asString();
		JsonPath JS = new JsonPath(resp);
		return JS.get(key).toString();
	}
 
}
