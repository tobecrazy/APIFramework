package main.java.cn.dbyl.tests;

import io.restassured.RestAssured;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class JenkinsAPI {
	@Test
	public void getTest() {
		// http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=6tYzTvGZSOpYB5Oc2YGGOKt8
		RestAssured.baseURI = "http://api.map.baidu.com/telematics/v3/weather";
		RestAssured.port = 80;
		RestAssured.given().param("location", "beijing").param("output", "json").param("ak", "sXF8krbnX1LXbz0TpzivvOS2")
				.when().get("/weather").then().body("status", equalTo("success")).log().all();

	}

	// @Test
	public void getTest1() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.given().header("Content-Type", "application/json").header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
				.when().get("/girl").then().statusCode(200).body("girl", equalTo("I'm a girl !")).log().all();

	}

	// @Test
	public void postTest1() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.given().header("Content-Type", "application/json").header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
				.when().post("/boy").then().statusCode(200).body("boy", equalTo("I'm a boy !")).log().all();

	}

}
