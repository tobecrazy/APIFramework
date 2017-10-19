package main.java.cn.dbyl.tests;

import io.restassured.RestAssured;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import org.testng.annotations.Test;

public class RestAssuredTest {

	@Test
	public void getTest1() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8081;
		RestAssured.given().header("Content-Type", "application/json").auth().basic("admin", "3edc4rfv")
				.header("Authorization", "Basic YWRtaW46M2VkYzRyZnY=").when().get("/jenkins/api/json?pretty=true")
				.then().statusCode(200).body("jobs.name", hasItems("Demo")).log();

	}

	@Test
	public void postTest() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8081;
		RestAssured.given().header("Content-Type", "application/json").auth().basic("admin", "3edc4rfv")
				.param("test", "Build From API").header("Authorization", "Basic YWRtaW46M2VkYzRyZnY=").when()
				.post("/jenkins/job/Demo/buildWithParameters").then().statusCode(201).log();

	}

}
