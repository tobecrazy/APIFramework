package main.java.cn.dbyl.tests;

import io.restassured.RestAssured;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class RestAssuredTest {
	@Test
	public void getTest() {
		RestAssured.baseURI = "http://api.douban.com/v2/book";
		RestAssured.port = 80;
		RestAssured.given().param("q", "java8").when().get("/search").then().body("count", equalTo(2));
		RestAssured.get("/1220562").then().body("title", equalTo("满月之夜白鲸现"));

	}

}
