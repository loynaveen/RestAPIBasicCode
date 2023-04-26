import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_ExtractValuesOfEachNodeInJSON {

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

		JsonPath jsonPath = response.jsonPath();

		Map<String, String> map = jsonPath.getMap("data");
		System.out.println(map);

		System.out.println(map.get("email"));
		Assert.assertEquals(map.get("id"), 2);
		Assert.assertEquals(map.get("email"), "janet.weaver@reqres.in");

	}

}
