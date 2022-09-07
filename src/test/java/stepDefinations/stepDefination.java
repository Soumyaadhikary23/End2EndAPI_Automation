package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import  static org.junit.Assert.*;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.location;

import pojo.serialize;
import resources.APIRessoures;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class stepDefination  extends Utils{
	
	RequestSpecification Rep;
	ResponseSpecification resspec;
	Response response;
	static String Place_id;
	
	TestDataBuild data = new TestDataBuild();
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException{
	    // Write code here that turns the phrase above into concrete actions
		//spec builder concept
		  Rep =	given().spec(requestSpecification())
		.body(data.addPlacePayload(name, language, address));
		
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		
		APIRessoures resourceAPI=APIRessoures.valueOf(resource);
	System.out.println(resourceAPI.getResource());
	
	    // Write code here that turns the phrase above into concrete actions
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("Post"))
		 response=	Rep.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET")) 
		  response=	Rep.when().get(resourceAPI.getResource());
		 
	}
	
	
	@Then("the api call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	   assertEquals(response.getStatusCode(),200);
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyVlaue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(getJSpath(response,keyVlaue),Expectedvalue);
	    
	}
	//GET PLACE API
	@Then("verify place_ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String ExpectedName, String resource) throws IOException {
	    
		Place_id=getJSpath(response,"place_id");
		 Rep =	given().spec(requestSpecification()).queryParam("place_id", Place_id);
		 user_calls_with_post_http_request(resource,"GET");
		 String actaulName=getJSpath(response,"name");
		 assertEquals(actaulName,ExpectedName);
	}
	
	//Delete place api
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    
	Rep=given().spec(requestSpecification()).body(data.deletePlacePayload(Place_id));
	}
}
