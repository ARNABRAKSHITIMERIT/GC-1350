package api.endpoints ;

import static io.restassured.RestAssured.given ;

import api.payloads.UrlConfigUpdateUrlPayload;
import api.payloads.UrlConfigValidateUrlPayload ;
import io.restassured.http.ContentType ;
import io.restassured.response.Response ;

public class UrlConfigEndPoints 
{

	public static Response	 listUrlConfigs (String authorizationToken , String projectId)
	{
		Response response = 	given().accept(ContentType.JSON)
				                                                   .header("Authorization" , authorizationToken)
				                                                   .pathParam("projectId" , projectId)
				                                   .when().get(Routes.listUrlConfigs_url) ; 
		return response ; 
	}
	
	public static Response	 retreiveUrlConfig (String authorizationToken , String urlConfigId)
	{
		Response response = 	given().accept(ContentType.JSON)
			                                                    	.header("Authorization" , authorizationToken)
				                                                    .pathParam("urlConfigId" , urlConfigId)
				                                    .when().get(Routes.retreiveUrlConfig_url) ; 
		return response ; 
	}
	
	public static Response	 validateUrlConfig (String authorizationToken , UrlConfigValidateUrlPayload payload)
	{
		Response response = 	given().accept(ContentType.JSON)
			                                                    	.contentType(ContentType.JSON)
			                                                    	.header("Authorization" , authorizationToken)
				                                                    .body(payload)
				                                    .when().post(Routes.validateUrlConfig_url) ; 
		return response ; 
	}
	
	public static Response	 createUrlConfig (String authorizationToken , String projectId , String payload)
	{
		Response response = 	given().accept(ContentType.JSON)
			                                                    	.contentType(ContentType.JSON)
			                                                    	.header("Authorization" , authorizationToken)
			                                                    	.pathParam("projectId" , projectId)
				                                                    .body(payload)
				                                    .when().post(Routes.createUrlConfig_url) ; 
		return response ; 
	}
	
	public static Response	 updateUrlConfig (String authorizationToken , String urlConfigId , UrlConfigUpdateUrlPayload payload)
	{
		Response response = 	given().accept(ContentType.JSON)
			                                                    	.contentType(ContentType.JSON)
			                                                    	.header("Authorization" , authorizationToken)
			                                                    	.pathParam("urlConfigId" , urlConfigId)
				                                                    .body(payload)
				                                    .when().put(Routes.updateUrlConfig_url) ; 
		return response ; 
	}
	
	public static Response	 deleteUrlConfig (String authorizationToken , String urlConfigId)
	{
		Response response = 	given().accept(ContentType.JSON)
				                                                  .header("Authorization" , authorizationToken)
				                                                  .pathParam("urlConfigId" , urlConfigId)
				                                  .when().delete(Routes.deleteUrlConfig_url) ; 
		return response ; 
	}
	
}
