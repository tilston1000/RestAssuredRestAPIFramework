package TestFramework;
import org.testng.annotations.Test;

import googleAPI.payLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class basics2 {
	
	@Test
	public void createPlaceAPI()
	{
		//BaseURL or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
		
		queryParam("key", "AIzaSyBhfJps_tOGgeBWkRxmU65VsO8N1wZ4YWE").
		body(payLoad.createPlaceData()).
		when().
			post("/maps/api/place/add/json").
		then().assertThat().
			   statusCode(200).and().contentType(ContentType.JSON).and().
			   body("status", equalTo("OK"));
		
		
	}
	

}
