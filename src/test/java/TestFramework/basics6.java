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

public class basics6 {
	
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\andrew.tilston\\Testing Info\\Projects\\APIDemoProject\\src\\files\\env.properties");		
		prop.load(fis);
	}
	
	@Test
	public void JiraAPICreateIssue()
	{
		// Creating issue/defect
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID=" + reuseableMethods.getSessionKey()).  
		body("{" +
			"\"fields\": {" +
			"\"project\":{" +
				"\"key\": \"RES\"" +
			"}," +
			"\"summary\": \"Issue 3 for adding comment\"," +
			"\"description\": \"Creating my bug on 19/06/18\"," +
			"\"issuetype\": {" +
				"\"name\": \"Bug\"" +
			"}" +
		"}}").
		when().
			post("/rest/api/2/issue").
		then().
			statusCode(201).extract().response();
		    JsonPath js = reuseableMethods.rawToJson(res);
		    String id = js.get("id");
		    System.out.println(id);
	}

}
