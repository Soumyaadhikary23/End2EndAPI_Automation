package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		//write a code that you give the place_id
		//it only execute when place_id = null
		
		stepDefination GM = new stepDefination();
		if(stepDefination.Place_id==null)
		{
		GM.add_place_payload_with("sam", "Bengali", "India");
		
		GM.user_calls_with_post_http_request("AddPlaceAPI", "POST");
		GM.verify_place_id_created_maps_to_using("sam", "GetPlaceAPI");
		}
		
	}

}
