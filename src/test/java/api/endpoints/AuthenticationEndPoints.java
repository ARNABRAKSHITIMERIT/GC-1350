package api.endpoints ;

import static io.restassured.RestAssured.given ;

import api.payloads.AuthenticationPayload ;
import io.restassured.http.ContentType ;
import io.restassured.response.Response ;


public class AuthenticationEndPoints
{

	public static Response	 createToken (AuthenticationPayload payload)
	{
		Response response = 	given().accept(ContentType.JSON)
				                                                   .contentType(ContentType.JSON)
				                                                   .body(payload)
				                                  .when().post(Routes.createToken_url) ;                                                  
		return response ; 
	}

	public static Response	 retreiveProject (String authorizationToken , String projectId)
	{
		Response response = 	given().accept(ContentType.JSON)
				                                                  .header("Authorization" , authorizationToken)
				                                                  .pathParam("projectId" , projectId)
				                                  .when().get(Routes.retreiveProject_url) ; 
		return response ; 
	}

}
