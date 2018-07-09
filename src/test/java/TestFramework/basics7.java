package TestFramework;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jiraAPI.resources;

public class basics7 {
	
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\andrew.tilston\\Testing Info\\Projects\\APIDemoProject\\src\\files\\env.properties");		
		prop.load(fis);
	}
	
	@Test
	public void JiraAPICreateComment()
	{
		// Creating issue/defect
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID=" + reuseableMethods.getSessionKey()).  
		body("{ \"body\": \"Inserting comment from the automation code on 19/06/18\"," + 
			"\"visibility\": {" +
				"\"type\": \"role\"," +
				"\"value\": \"Administrators\" }" +
		"}").
		when().
			post(resources.createComment()).
		then().
			statusCode(201).extract().response();
	    	JsonPath js = reuseableMethods.rawToJson(res);
	    	String id = js.get("id");
	    	System.out.println(id);
		
		

	}

}
