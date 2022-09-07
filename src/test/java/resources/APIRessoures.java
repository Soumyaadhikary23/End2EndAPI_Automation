package resources;

//enum is special class in java which has collection  of constants and methods

public enum APIRessoures {
	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");
	
	private String Resource;
	
	APIRessoures(String resource)
	{
		this.Resource=resource;
	}
	
	public String getResource()
	{
		return Resource;
	}
}
