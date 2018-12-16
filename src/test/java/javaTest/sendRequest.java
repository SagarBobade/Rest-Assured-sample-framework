package javaTest;

import static io.restassured.RestAssured.get;

import io.restassured.response.Response;

public class sendRequest {

public static int testResponseCode(String reqUrl, String methodName, double expectedCode) {
		System.out.println(reqUrl);
		System.out.println(expectedCode);
		Response resp = null;
		int code;
		
		if(methodName.equalsIgnoreCase("get")) {
			resp = get(reqUrl);
		}
			code = resp.getStatusCode();

		if(code == Math.round(expectedCode)) {
			return 0;
		}
		else {
			return -1;
		}
		
	}


}
