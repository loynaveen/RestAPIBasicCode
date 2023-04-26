import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_ValidatingJSONResponse {

	@Test
	public void getWeatherDetails() {
		// Base URL
		RestAssured.baseURI = "https://reqres.in/api";

		// Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/users/2");

		// print response in console
		System.out.println("Response body is: " + response.getBody().asString());
		String responseBody = response.getBody().asString();

		Assert.assertEquals(responseBody.contains("2"), true);
	}

}
