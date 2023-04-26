import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_GET_Request_PrintAllHeaders {

	@Test
	public void getWeatherDetails() {
		// Base URL
		RestAssured.baseURI = "https://reqres.in/api";

		// Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/users?page=2");

		// print response in console
		System.out.println("Response body is: " + response.getBody().asString());

		Headers allHeaders = response.headers();
		for (Header header : allHeaders) {
			System.out.println(header.getName() + "--->" + header.getValue());
		}
	}

}
