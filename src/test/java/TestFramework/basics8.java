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

public class basics8 {
	
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\andrew.tilston\\Testing Info\\Projects\\APIDemoProject\\src\\files\\env.properties");		
		prop.load(fis);
	}
	
	@Test
	public void JiraAPIUpdate()
	{
		// Creating issue/defect
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID=" + reuseableMethods.getSessionKey()).  
		pathParams("commentid", "10005").
		
		body("{ \"body\": \"Updating andy's comment from the automation code from 19/06/18, oh yeah!\"," + 
			"\"visibility\": {" +
				"\"type\": \"role\"," +
				"\"value\": \"Administrators\" }" +
		"}").
		when().
			put("/rest/api/2/issue/10004/comment/{commentid}").
		then().
			statusCode(200).extract().response();
	}

}
