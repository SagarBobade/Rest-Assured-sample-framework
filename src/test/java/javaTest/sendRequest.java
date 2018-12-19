package javaTest;

import static io.restassured.RestAssured.get;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class sendRequest {

	@Test
	public static int testResponseCode(String reqUrl, String methodName, double expectedCode1, String jsonBody,
			int expectedTime, ExtentReports extent) {
		System.out.println("method : " + methodName);
		int expectedCode = Integer.valueOf((int) Math.round(expectedCode1));
		System.out.println("expected code is :- " + expectedCode);
		if (!jsonBody.contains("NA")) {
			JSONObject jsonobj = new JSONObject(jsonBody);
		}
		Response resp = null;
		int code = 0;
		RestAssured.baseURI = reqUrl;
		RequestSpecification request = RestAssured.given();

		ExtentTest logger2 = extent.createTest(methodName + " " + reqUrl);

		if (methodName.equalsIgnoreCase("get")) {
			resp = get(reqUrl);
			code = resp.getStatusCode();
		} else if (methodName.equalsIgnoreCase("post")) {

			request.header("Content-Type", "application/json");
			resp = request.post();
			code = resp.getStatusCode();

		} else if (methodName.equalsIgnoreCase("put")) {

			request.header("Content-Type", "application/json");
			resp = request.put();
			code = resp.getStatusCode();

		} else if (methodName.equalsIgnoreCase("delete")) {

			request.header("Content-Type", "application/json");
			resp = request.delete();
			code = resp.getStatusCode();
		}
		System.out.println(methodName + " method :" + code);
		if (resp != null) {
			code = resp.getStatusCode();
		}

		if (code == Math.round(expectedCode)) {
			if (resp.getTime() > expectedTime) {
				logger2.log(Status.FAIL,
						"<b>Faild reason: </b>Response Time is greater than Expected Time.<br />"
								+ "<b>Expected code:</b> " + expectedCode + "<br /><b>Actual response:</b> " + code
								+ "<br />" + "<b>Response time: </b>" + resp.getTime() + "<br /><b>Expected time: </b>"
								+ expectedTime + "<br><b>Response Body: </b>" + resp.asString());
				return -1;
			}
			logger2.log(Status.PASS,
					"<b>Expected code:</b> " + expectedCode + "<br /><b>Actual response:</b> " + code + "<br />"
							+ "<b>Response time: </b>" + resp.getTime() + "<b><br />Expected time: </b>" + expectedTime
							+ "<br />Response Body: " + resp.asString());
			return 0;
		} else {
			logger2.log(Status.FAIL,
					"<b>Expected code:</b> " + expectedCode + "<br /><b>Actual response:</b> " + code + "<br />"
							+ "<b>Response time: </b> " + resp.getTime() + "<br /><b>Response Body: </b>"
							+ resp.asString());
			return -1;
		}

	}

}
