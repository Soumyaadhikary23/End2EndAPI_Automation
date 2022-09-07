package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.location;
import pojo.serialize;

public class TestDataBuild {
	
	
	public serialize addPlacePayload(String name, String language, String address) {
		
		serialize ST = new serialize();
		ST.setAccuracy(50);
		ST.setAddress(address);
		ST.setLanguage(language);
		ST.setName(name);
		ST.setPhone_number("7059920149");
		ST.setWebsite("http://google.com");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ST.setTypes(myList);
		
		location l = new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ST.setLocation(l);
		
		return ST;
	}
	
	public  String deletePlacePayload(String place_id) 
	{
		return "{\"place_id\":\""+place_id+"\"}";
	}

}
