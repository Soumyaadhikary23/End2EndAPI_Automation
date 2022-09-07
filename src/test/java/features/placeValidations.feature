Feature: Valodating Place API's

@AddPlace @Regression
Scenario Outline: Verify if the place is being Successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "Post" http request
	Then the api call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_ID created maps to "<name>" using "GetPlaceAPI"


Examples:
	|name  |    |language| |address|
	|Soumya|    |English | |kolkata|
#	|Papan |	|Spanish | |spain  |

@DeletePlace @Regression
Scenario: Verfy Delete Place functionality is working
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "Post" http request
	Then the api call is success with status code 200
	And "status" in response body is "OK"