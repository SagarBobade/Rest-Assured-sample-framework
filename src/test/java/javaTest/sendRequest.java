package javaTest;

import static io.restassured.RestAssured.get;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class sendRequest {

	@Test
	public static int testResponseCode(String reqUrl, String methodName, double expectedCode, String jsonBody) {
		System.out.println("method : " + methodName);
		System.out.println("expected code is :- " + expectedCode);
		if (!jsonBody.contains("NA")) {
			JSONObject jsonobj = new JSONObject(jsonBody);
		}
		Response resp = null;
		int code = 0;
		RestAssured.baseURI = reqUrl;
		RequestSpecification request = RestAssured.given();

		if (methodName.equalsIgnoreCase("get")) {
			resp = get(reqUrl);
		} else if (methodName.equalsIgnoreCase("post")) {

			request.header("Content-Type", "application/json");
			resp = request.post();

		} else if (methodName.equalsIgnoreCase("put")) {

			request.header("Content-Type", "application/json");
			resp = request.put();

		} else if (methodName.equalsIgnoreCase("delete")) {

			request.header("Content-Type", "application/json");
			resp = request.delete();

		}
		System.out.println(methodName + " method :" + code);
		if (resp != null) {
			code = resp.getStatusCode();
		}

		if (code == Math.round(expectedCode)) {
			return 0;
		} else {
			return -1;
		}

	}

}
