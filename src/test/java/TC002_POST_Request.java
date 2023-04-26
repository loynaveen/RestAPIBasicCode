import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {

	@Test
	public void registrationSuccessful() {

		// Base URL
		RestAssured.baseURI = "https://reqres.in/api";

		// Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Payload sending along with POST Request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Loy");
		requestParams.put("job", "teamMember");

		httprequest.header("Content-Type", "application/json");

		httprequest.body(requestParams.toJSONString());

		// Response Object
		Response response = httprequest.request(Method.POST, "/users");

		// print response in console
		System.out.println("Response body is: " + response.getBody().asString());

		// status code validation
		System.out.println("The status code is: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 201);

		String successCode = response.jsonPath().get("createdAt");
		System.out.println(successCode);

	}

}
