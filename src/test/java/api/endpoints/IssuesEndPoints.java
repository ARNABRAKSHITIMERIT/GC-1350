package api.endpoints;

import static io.restassured.RestAssured.given ;

import java.util.ResourceBundle ;

import io.restassured.http.ContentType ;
import io.restassured.response.Response ;

public class IssuesEndPoints
{

	public static ResourceBundle getIssuesUrlFromProperties ()
	{
		ResourceBundle	get_url = ResourceBundle.getBundle("Routes") ;
		return get_url ; 
	}

	public static Response	 retreiveIssue (String authorizationToken , String issueId)
	{
		String retreiveIssue_url = getIssuesUrlFromProperties().getString("retreiveIssue_url") ;
		
		Response response = 	given().accept(ContentType.JSON)
				                                                   .header("Authorization" , authorizationToken)
				                                                   .pathParam("issueId" , issueId)
				                                   .when().get(retreiveIssue_url) ;
		return response ;
	}

}	


