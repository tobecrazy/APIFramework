package main.java.cn.dbyl.tests;

import static org.hamcrest.CoreMatchers.hasItems;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ODataTest {
	String host = "https://api15.sapsf.cn";

	@Test
	public void getTest1() {
		RestAssured.baseURI = host;

		Response result = RestAssured.given().auth().basic("admin@atTFTOAV1101", "welcome").get("/odata/v2/FormHeader");
		Assert.assertEquals(result.getStatusCode(), 200);
		System.out.println(result.getBody().asString());
		System.out.println(result.xmlPath().get("feed.title").toString());
		

	}

	@Test
	public void getTest2() {
		RestAssured.baseURI = host;
		Response result = RestAssured.given().auth().basic("admin@atTFTOAV1101", "welcome").param("metadata")
				.get("/odata/v2/");
		Assert.assertEquals(result.getStatusCode(), 200);
		System.out.println(result.getBody().asString());

	}
}
