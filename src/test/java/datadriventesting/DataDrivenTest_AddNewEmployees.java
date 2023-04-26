package datadriventesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {

	@Test(dataProvider = "empDataProvider")
	public void postNewEmployees(String name, String job) {

		RestAssured.baseURI = "https://reqres.in/api";

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();

		requestParams.put("name", name);
		requestParams.put("job", job);

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString());

		Response response = httpRequest.request(Method.POST, "/users");

		System.out.println(response.getBody().asString());

		String responseBody = response.getBody().asString();

		Assert.assertEquals(responseBody.contains(name), true);
		Assert.assertEquals(responseBody.contains(job), true);

		Assert.assertEquals(response.getStatusCode(), 201);

	}

	@DataProvider(name = "empDataProvider")
	String[][] getEmpData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/datadriventesting/empdata.xlsx";

		int rowNum = XLUtils.getRowCount(path, "Sheet1");
		int cellNum = XLUtils.getCellCount(path, "Sheet1", 1);

		String empData[][] = new String[rowNum][cellNum];

		for (int i = 1; i <= rowNum; i++)
			for (int j = 0; j < cellNum; j++)
				empData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);

		// String empData[][] = { { "Dinesh", "General" }, { "Suresh", "Worker" }, {
		// "Love", "LoveDa" } };
		return empData;
	}
}
