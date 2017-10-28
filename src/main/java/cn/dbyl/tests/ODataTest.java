package main.java.cn.dbyl.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ODataTest {
	String host = "https://sfapiqaautocand.sflab.ondemand.com";

	@Test(enabled = true)
	public void getTest1() {
		RestAssured.baseURI = host;

		Response result = RestAssured.given().auth().basic("cgrant@atTFTV1201", "pwd").contentType("application/json")
				.param("format", "json").param("formDataId", "1000000L").header("Content-Type", "application/json")
				.get("odata/v2/signForm");
		// Assert.assertEquals(result.getStatusCode(), 200);
		System.out.println("Test1 >>>>>" + result.getBody().asString());
		System.out.println("Test1 <<<<<<<" + result.xmlPath().get("feed.title").toString());

	}

	@Test(enabled = false)
	public void getTest2() {
		RestAssured.baseURI = host;

		Response result = RestAssured.given().auth().basic("cgrant@atTFTV1201", "pwd").param("metadata")
				.get("/odata/v2/");
		Assert.assertEquals(result.getStatusCode(), 200);
		System.out.println(result.getBody().asString());

	}

	@Test(enabled = true)
	public void postTest1() {
		RestAssured.baseURI = host;

		Response result = RestAssured.given().auth().basic("cgrant@atTFTV1201", "pwd")
				.header("Content-Type", "application/json")
				.body("{\"formDataIdList\":\"6682\",\"action\":\"forward\",\"COMMENT\":\"TFT monitor\"}")
				.post("/odata/v2/internal/massRouteForm");
		// Assert.assertEquals(result.getStatusCode(), 200);
		System.out.println(">>>>" + result.asString());

	}
}
