package main.java.cn.dbyl.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ODataDemo {
	String host = "https://api15.sapsf.cn";

	// https://api15.sapsf.cn/odata/v2/restricted/FormTemplate?$format=json
	@Test(enabled = true)
	public void getTest3() {
		RestAssured.baseURI = host;

		Response result = RestAssured.given().auth().basic("admin@atTFTOAV1101", "welcome").contentType("application/json")
				.param("format", "json").param("formDataId", "1000000L").header("Content-Type", "application/json")
				.get("odata/v2/signForm");
		// Assert.assertEquals(result.getStatusCode(), 200);
		System.out.println("Test1 >>>>>" + result.prettyPeek().asString());
		System.out.println("Test1 <<<<<<<" + result.xmlPath().get("feed.title").toString());

	}

//	@Test
	public void getTest1() {
		RestAssured.baseURI = host;

		Response result = RestAssured.given().auth().basic("admin@atTFTOAV1101", "welcome")
				.contentType("application/json").param("format", "json").get("/odata/v2/FormTemplate");
		Assert.assertEquals(result.getStatusCode(), 200);
		System.out.println(">>>>>\n\n" + result.prettyPeek().body().prettyPrint());
		// System.out.println(result.jsonPath().toString());

	}

	// @Test
	public void getTest2() {
		RestAssured.baseURI = host;
		Response result = RestAssured.given().auth().basic("admin@atTFTOAV1101", "welcome").param("metadata")
				.get("/odata/v2/");
		Assert.assertEquals(result.getStatusCode(), 200);
		System.out.println(result.getBody().asString());

	}
}
