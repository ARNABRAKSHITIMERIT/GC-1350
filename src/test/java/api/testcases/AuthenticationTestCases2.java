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
import api.utilities.DataProviders ;
import io.qameta.allure.Description ;
import io.qameta.allure.Severity ;
import io.qameta.allure.SeverityLevel ;
import io.restassured.module.jsv.JsonSchemaValidator ;
import io.restassured.path.json.JsonPath ;
import io.restassured.response.Response ;

public class AuthenticationTestCases2
{

	public static Logger logger ;

	@BeforeClass
	public void startAuthenticationTestCases2  ()
	{
		logger = LogManager.getLogger("GC_API") ; 
		logger.info(" API Test Execution started for AUTHENTICATION 2 ") ;
	}

	@Test(priority = 3 , dataProvider = "testDataProviderAuthentication" , dataProviderClass = DataProviders.class)
	@Description("Generate the User Token of a given User using Data Provider and Apache POI")
	@Severity(SeverityLevel.BLOCKER)
	public void createTokenTestCase2 (String usernameExcelInput , String passwordExcelInput)
	{
		AuthenticationPayload credentials = new AuthenticationPayload () ;
		
		credentials.setUsername(usernameExcelInput) ;
		credentials.setPassword(passwordExcelInput) ; 
		
		Response response = AuthenticationEndPoints.createToken(credentials) ; 
		response .then().log().all() ; 
		
		JsonPath jsonPathView =  response.jsonPath() ;
		
		Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath ("createTokenSchema.json")).toString().contains("ValidatableResponse") , true ) ;
		Assert.assertEquals (response.getStatusCode() , 200) ; 
		Assert.assertEquals(response.getStatusLine() , "HTTP/1.1 200 OK") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		Assert.assertEquals ( jsonPathView.get("token").toString().matches("6df00438f5392faf4c9efc965b50f6cb4a66faa0|63f7137056d5b8ac61548761bc82e7de6c2fca83") , true ) ;
		
		Assert.assertEquals( response.getHeader("Content-Type") , "application/json" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "POST, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
				
		logger.info(" CREATE_TOKEN_2 has been executed ") ;
	}	

	@Test(priority = 4 , dataProvider = "testDataProviderProjectId" , dataProviderClass = DataProviders.class)
	@Description("Verify the Project details of a given Project using Data Provider and Apache POI")
	@Severity(SeverityLevel.NORMAL)
	public void retreiveProjectTestCase2 (String projectIdExcelInput) throws IOException
	{
		String authorizationToken = AuthorizationDetails.getAuthorizationToken() ;
		
		Response response = AuthenticationEndPoints.retreiveProject(authorizationToken , projectIdExcelInput) ; 
		response .then().log().all() ; 
		
		JsonPath jsonPathView =  response.jsonPath() ;
		
		Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath ("retreiveProject.json")).toString().contains("ValidatableResponse") , true ) ;
		Assert.assertEquals (response.getStatusCode() , 200) ;
		Assert.assertEquals(response.getStatusLine() , "HTTP/1.1 200 OK") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		Assert.assertEquals ( jsonPathView.get("id").toString().matches("caefc353-825d-401e-9050-a9e62bd746ad|92871b29-477b-41b8-aac0-da9f13b27f89|f657a665-f287-4d2c-a81c-66bc90f0bc45") , true ) ;
		Assert.assertEquals ( jsonPathView.get("status").toString().matches("completed|in-progress") , true ) ;
		Assert.assertEquals ( jsonPathView.get("name").toString().matches("IMERIT_TEST_PROJECT_NAME_2023.05.31-12.54.59|BEFORE_UPDATE86|BEFORE_UPDATE92") , true ) ;
		Assert.assertEquals ( jsonPathView.get("description").toString().matches("IMERIT_TEST_PROJECT_DESCRIPTION_2023.05.31-12.54.59|RAKSHIT_TEST_PROJECT_NEW_002") , true ) ;
		Assert.assertEquals ( jsonPathView.get("platform").toString() , "datasaur" ) ;
		Assert.assertEquals ( jsonPathView.get("ext_platform_id").toString().matches("||86|92") , true ) ;
		Assert.assertEquals ( jsonPathView.get("organization").toString() , "555a2d2e-cf1e-46a9-a45a-d2248a9fab08" ) ;
		Assert.assertEquals ( jsonPathView.get("participants").toString() , "[]" ) ;
		Assert.assertEquals ( jsonPathView.get("metrics.item_count").toString() , "0" ) ;
		Assert.assertEquals ( jsonPathView.get("metrics.approved_task_count").toString() , "0" ) ;
		Assert.assertEquals ( jsonPathView.get("metrics.task_count").toString() , "0" ) ;
		Assert.assertEquals ( jsonPathView.get("metrics.annotation_count").toString() , "0" ) ;
		Assert.assertEquals ( jsonPathView.get("metrics.worked_time").toString() , "0" ) ;
		Assert.assertEquals ( jsonPathView.get("started_at").toString().matches("2023-05-05T12:18:02Z|2023-05-21T17:46:15.963980Z|2023-05-21T17:40:56.380322Z") , true ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("completed_at")).toString().matches("2023-05-05T12:25:09Z|null") , true ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("canceled_at")) ,  "null" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("canceled_reason")) ,  "null" ) ;
		Assert.assertEquals ( jsonPathView.get("google_webhook_url").toString().matches("||http://rakshit.com|http://rakshit.com") , true ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.summary").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.task_count").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.average_time_per_task").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.top_research_website").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.experience_curve").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ui_config.idle_curve").toString() , "show" ) ;
		Assert.assertEquals ( jsonPathView.get("ext_platform_id").toString().length() == 0 || jsonPathView.get("ext_platform_id").toString().length() == 2  , true ) ;
		Assert.assertEquals ( jsonPathView.get("dashboard_id").toString() , "" ) ;
		Assert.assertEquals ( jsonPathView.get("created_at").toString().matches("2023-05-05T12:18:02.277483Z|2023-05-21T17:46:15.964025Z|2023-05-21T17:40:56.380358Z") , true ) ;
		Assert.assertEquals ( jsonPathView.get("updated_at").toString().matches("2023-05-31T07:24:17.027502Z|2023-05-21T17:46:16.882131Z|2023-05-21T17:40:57.304640Z") , true ) ;
			
		Assert.assertEquals( response.getHeader("Content-Type") , "application/json" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "GET, PUT, PATCH, DELETE, HEAD, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
				
		logger.info(" RETREIVE_PROJECT_2 has been executed ") ; 
	}
	
	@AfterClass
	public void finishAuthenticationTestCases2  ()
	{
		logger.info(" API Test Execution ended for AUTHENTICATION 2 ") ; 
	}

}
