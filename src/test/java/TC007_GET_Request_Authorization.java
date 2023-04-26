import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_Request_Authorization {

	@Test
	public void getWeatherDetails() {
		// Base URL
		RestAssured.baseURI = "https://reqres.in/api";

		// Request Object
		RequestSpecification httprequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "eve.holt@reqres.in");
		requestParams.put("password", "cityslicka");

		httprequest.header("Content-Type", "application/json");

		httprequest.body(requestParams.toJSONString());

		// Response Object
		Response response = httprequest.request(Method.POST, "/login");

		// for GET where Authorization is required
//		PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
//		auth.setUserName("eve.holt@reqres.in");
//		auth.setPassword("cityslicka");

		// status code validation
		System.out.println("The status code is: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);

		// status line verification
		System.out.println("Status line is: " + response.getStatusLine());
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");

		System.out.println("Response body is: " + response.getBody().asString());

	}

}
