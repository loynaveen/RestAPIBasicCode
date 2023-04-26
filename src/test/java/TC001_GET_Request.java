import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {

	@Test
	public void getweatherDetails() {

		// Base URL
		RestAssured.baseURI = "https://reqres.in/api";

		// Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/users/2");

		// print response in console
		System.out.println("Response body is: " + response.getBody().asString());

		// status code validation
		System.out.println("The status code is: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);

		// status line verification
		System.out.println("Status line is: " + response.getStatusLine());
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
	}

}
