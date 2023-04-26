import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Request {

	@Test
	public void getListUsers() {

		// Base URL
		RestAssured.baseURI = "https://reqres.in/api";

		// Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/users?page=2");

		// print response in console
		System.out.println("Response body is: " + response.getBody().asString());

		// validating headers
		String contentType = response.header("Content-Type");
		System.out.println("Content Type is :" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content Encoding is :" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");

	}

}
