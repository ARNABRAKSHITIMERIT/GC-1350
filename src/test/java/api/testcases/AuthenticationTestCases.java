package api.testcases ;

import java.io.IOException ;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager ;
import org.apache.logging.log4j.Logger ;
import org.testng.Assert ;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass ;
import org.testng.annotations.Test ;

import api.endpoints.AuthenticationEndPoints ;
import api.payloads.AuthenticationPayload ;
import api.utilities.AuthorizationDetails ;
import io.qameta.allure.Description ;
import io.qameta.allure.Severity ;
import io.qameta.allure.SeverityLevel ;
import io.restassured.module.jsv.JsonSchemaValidator ;
import io.restassured.path.json.JsonPath ;
import io.restassured.response.Response ; 

public class AuthenticationTestCases 
{

	public static Logger logger ;

	@BeforeClass
	public void startAuthenticationTestCases  ()
	{
		logger = LogManager.getLogger("GC_API") ; 
		logger.info(" API Test Execution started for AUTHENTICATION ") ;
	}

	@Test(priority = 1)
	@Description("Generate the User Token of a given User")
	@Severity(SeverityLevel.BLOCKER)
	public void createTokenTestCase () throws IOException
	{
		AuthenticationPayload credentials = new AuthenticationPayload () ;
		
		String username = AuthorizationDetails.getAuthorizationUsername() ;
		String password = AuthorizationDetails.getAuthorizationPassword() ;
		
		credentials.setUsername(username) ;
		credentials.setPassword(password) ; 
		
		Response response = AuthenticationEndPoints.createToken(credentials) ; 
		response .then().log().all() ; 
		
		JsonPath jsonPathView =  response.jsonPath() ;
		
		Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath ("createTokenSchema.json")).toString().contains("ValidatableResponse") , true ) ;
		Assert.assertEquals (response.getStatusCode() , 200) ;
		Assert.assertEquals(response.getStatusLine() , "HTTP/1.1 200 OK") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		Assert.assertEquals ( jsonPathView.get("token").toString() , "3428114439ac2e7976927307b8c6e3d68acd999c" ) ;
		
		Assert.assertEquals( response.getHeader("Content-Type") , "application/json" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "POST, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
				
		logger.info(" CREATE_TOKEN has been executed ") ;
	}

	@Test(priority = 2)
	@Description("Verify the Project details of a given Project")
	@Severity(SeverityLevel.NORMAL)
	public void retreiveProjectTestCase () throws IOException
	{
		String authorizationToken = AuthorizationDetails.getAuthorizationToken() ;
		String projectId = "caefc353-825d-401e-9050-a9e62bd746ad" ;
		
		Response response = AuthenticationEndPoints.retreiveProject(authorizationToken , projectId) ;
		response .then().log().all() ; 
		
		JsonPath jsonPathView =  response.jsonPath() ;
		
		Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath ("retreiveProject.json")).toString().contains("ValidatableResponse") , true ) ;
		Assert.assertEquals (response.getStatusCode() , 200) ; 
		Assert.assertEquals(response.getStatusLine() , "HTTP/1.1 200 OK") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		Assert.assertEquals ( jsonPathView.get("id").toString() , "caefc353-825d-401e-9050-a9e62bd746ad" ) ;
		Assert.assertEquals ( jsonPathView.get("status").toString() , "completed" ) ;
		Assert.assertEquals ( jsonPathView.get("name").toString() , "IMERIT_TEST_PROJECT_NAME_2023.05.31-12.54.59" ) ;
		Assert.assertEquals ( jsonPathView.get("description").toString() , "IMERIT_TEST_PROJECT_DESCRIPTION_2023.05.31-12.54.59" ) ;
		Assert.assertEquals ( jsonPathView.get("platform").toString() , "datasaur" ) ;
		Assert.assertEquals ( jsonPathView.get("ext_platform_id").toString() , "" ) ;
		Assert.assertEquals ( jsonPathView.get("organization").toString() , "555a2d2e-cf1e-46a9-a45a-d2248a9fab08" ) ;
		Assert.assertEquals ( jsonPathView.get("participants").toString() , "[]" ) ;
		Assert.assertEquals ( jsonPathView.get("metrics.item_count").toString() , "0" ) ;
		Assert.assertEquals ( jsonPathView.get("metrics.approved_task_count").toString() , "0" ) ;
		Assert.assertEquals ( jsonPathView.get("metrics.task_count").toString() , "0" ) ;
		Assert.assertEquals ( jsonPathView.get("metrics.annotation_count").toString() , "0" ) ;
		Assert.assertEquals ( jsonPathView.get("metrics.worked_time").toString() , "0" ) ;
		Assert.assertEquals ( jsonPathView.get("started_at").toString() , "2023-05-05T12:18:02Z" ) ;
		Assert.assertEquals ( jsonPathView.get("completed_at").toString() , "2023-05-05T12:25:09Z" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("canceled_at")) ,  "null" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("canceled_reason")) ,  "null" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.summary").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.task_count").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.average_time_per_task").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.top_research_website").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.experience_curve").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.idle_curve").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("form_spec").toString() , "{}" ) ;
		Assert.assertEquals ( jsonPathView.get("dashboard_id").toString() , "" ) ;
		Assert.assertEquals ( jsonPathView.get("created_at").toString() , "2023-05-05T12:18:02.277483Z" ) ;
		Assert.assertEquals ( jsonPathView.get("updated_at").toString() , "2023-05-31T07:24:17.027502Z" ) ;
		
		Assert.assertEquals( response.getHeader("Content-Type") , "application/json" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "GET, PUT, PATCH, DELETE, HEAD, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
						
		logger.info(" RETREIVE_PROJECT has been executed ") ; 
	}
	
	@AfterClass
	public void finishAuthenticationTestCases  ()
	{
		logger.info(" API Test Execution ended for AUTHENTICATION ") ; 
	}

}
