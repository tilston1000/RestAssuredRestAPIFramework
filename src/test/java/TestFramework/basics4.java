package TestFramework;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class basics4 {
	
	@Test
	public void postDataXML() throws IOException
	{
		String postData = GenerateStringFromResource("C:\\Users\\andrew.tilston\\Testing Info\\Projects\\postdata.xml");
		RestAssured.baseURI="https://maps.googleapis.com";
		Response resp = given().
		
		queryParam("key", "AIzaSyBhfJps_tOGgeBWkRxmU65VsO8N1wZ4YWE").
		body(postData).
		when().
			post("/maps/api/place/add/xml").
		then().assertThat().
			   statusCode(200).and().contentType(ContentType.XML).
			   extract().response();
		
		XmlPath x = reuseableMethods.rawToXML(resp);			
	}
	
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	

}
