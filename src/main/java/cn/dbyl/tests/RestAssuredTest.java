package main.java.cn.dbyl.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import main.java.cn.dbyl.APIUtils.JsonUtils;
import main.java.cn.dbyl.beans.JenkinsJobsInfoBean;
import main.java.cn.dbyl.beans.ZhihuAPIBean;

import static org.hamcrest.CoreMatchers.hasItems;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class RestAssuredTest {

	@Test
	public void getTest1() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8081;
		RestAssured.given().header("Content-Type", "application/json").auth().basic("admin", "3edc4rfv")
				.header("Authorization", "Basic YWRtaW46M2VkYzRyZnY=").when().get("/jenkins/api/json?pretty=true")
				.then().statusCode(200).body("jobs.name", hasItems("Demo")).log();
		Map<String, String> params = new HashMap<String, String>();
		params.put("Content-Type", "application/json");
		params.put("Authorization", "Basic YWRtaW46M2VkYzRyZnY=");
		Response response = RestAssured.given().header("Authorization", "Basic YWRtaW46M2VkYzRyZnY=")
				.get(RestAssured.baseURI + ":" + RestAssured.port + "/jenkins/api/json?pretty=true");
		JenkinsJobsInfoBean info = (JenkinsJobsInfoBean) JsonUtils.getInstance().parseStringToBean(response.asString(),
				JenkinsJobsInfoBean.class);
		System.out.println(response.getBody().asString() + "\n\njsonPath\n\n" + response.jsonPath().get("jobs.name"));
		System.out.println("=====>" + info.getJobs().get(0).getName());

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
