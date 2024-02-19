package api.testcases ;

import java.io.IOException ;
import java.util.ArrayList ;
import java.util.List ;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager ;
import org.apache.logging.log4j.Logger ;
import org.testng.Assert ;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass ;
import org.testng.annotations.Test ;

import com.fasterxml.jackson.core.JsonProcessingException ;
import com.fasterxml.jackson.databind.ObjectMapper ;

import api.endpoints.UrlConfigEndPoints ;
import api.payloads.UrlConfigCreateUrlPayload ;
import api.payloads.UrlConfigFields ;
import api.payloads.UrlConfigGCConfig ;
import api.payloads.UrlConfigUpdateUrlPayload ;
import api.payloads.UrlConfigValidateUrlPayload ;
import api.utilities.AuthorizationDetails ;
import io.qameta.allure.Description ;
import io.qameta.allure.Severity ;
import io.qameta.allure.SeverityLevel ;
import io.restassured.module.jsv.JsonSchemaValidator ;
import io.restassured.path.json.JsonPath ;
import io.restassured.response.Response ;

public class UrlConfigTestCases 
{

	public static Logger logger ;
	
	String latestUrlConfigId ;

	@BeforeClass
	public void startUrlConfiguratorTestCases () throws JsonProcessingException
	{
		logger = LogManager.getLogger("GC_API") ; 
		logger.info(" API Test Execution started for URL CONFIGURATOR ") ;
	}

	@Test(priority = 6)
	@Description("Verify all the URL Configuration details for a given Project")
	@Severity(SeverityLevel.NORMAL)
	public void listUrlConfigsTestCase () throws IOException
	{
		String authorizationToken = AuthorizationDetails.getAuthorizationToken() ;
		String projectId = "caefc353-825d-401e-9050-a9e62bd746ad" ;
		
		Response response = UrlConfigEndPoints.listUrlConfigs(authorizationToken , projectId) ;
		response.then().log().all() ;
		
		JsonPath jsonPathView =  response.jsonPath() ;
		
		Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath ("listUrlConfigs.json")).toString().contains("ValidatableResponse") , true ) ;
		Assert.assertEquals (response.getStatusCode() , 200) ; 
		Assert.assertEquals(response.getStatusLine() , "HTTP/1.1 200 OK") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		Assert.assertEquals ( jsonPathView.get("count").toString() , "2" ) ;
		Assert.assertEquals ( jsonPathView.get("page_size").toString() , "100" ) ;
		Assert.assertEquals ( jsonPathView.get("current_page").toString() , "1" ) ;
		Assert.assertEquals ( jsonPathView.get("total_pages").toString() , "1" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("next")) ,  "null" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("previous")) ,  "null" ) ;
		Assert.assertEquals ( jsonPathView.get("results[0].id").toString() , "a4197550-5993-4dcd-a621-c9b2d09b6201" ) ;
		Assert.assertEquals ( jsonPathView.get("results[0].project").toString() , "caefc353-825d-401e-9050-a9e62bd746ad" ) ;
		Assert.assertEquals ( jsonPathView.get("results[0].category").toString() , "task" ) ;
		Assert.assertEquals ( jsonPathView.get("results[0].fields[0]").toString() ,"{}" ) ;
		Assert.assertEquals ( jsonPathView.get("results[0].match_params[0]").toString() ,"{}" ) ;
		Assert.assertEquals ( jsonPathView.get("results[0].gc_config").toString() ,"{}" ) ;
		Assert.assertEquals ( jsonPathView.get("results[0].regex").toString() ,"https://test.tool/project/95fe0cac-36a1-4705-bf73-3249275d8acc/task/(?P<task_id>[0-9a-z-]+)/role/(?P<role>(?:op|qa))/" ) ;
		Assert.assertEquals ( jsonPathView.get("results[0].created_at").toString() ,"2024-01-24T08:37:47.592898Z" ) ;
		Assert.assertEquals ( jsonPathView.get("results[0].updated_at").toString() ,"2024-01-24T08:37:47.592928Z" ) ;
		Assert.assertEquals ( jsonPathView.get("results[1].id").toString() , "91799c3f-11ca-410f-8d5e-876f4add716a" ) ;
		Assert.assertEquals ( jsonPathView.get("results[1].project").toString() , "caefc353-825d-401e-9050-a9e62bd746ad" ) ;
		Assert.assertEquals ( jsonPathView.get("results[1].category").toString() , "task" ) ;
		Assert.assertEquals ( jsonPathView.get("results[1].fields[0]").toString() ,"{}" ) ;
		Assert.assertEquals ( jsonPathView.get("results[1].match_params[0]").toString() ,"{}" ) ;
		Assert.assertEquals ( jsonPathView.get("results[1].gc_config").toString() ,"{}" ) ;
		Assert.assertEquals ( jsonPathView.get("results[1].regex").toString() ,"https://test.tool/project/95fe0cac-36a1-4705-bf73-3249275d8acc/task/(?P<task_id>[0-9a-z-]+)/role/(?P<role>(?:op|qa))/" ) ;
		Assert.assertEquals ( jsonPathView.get("results[1].created_at").toString() ,"2023-05-19T06:58:06.917130Z" ) ;
		Assert.assertEquals ( jsonPathView.get("results[1].updated_at").toString() ,"2023-05-19T06:58:06.917177Z" ) ;
		
		Assert.assertEquals( response.getHeader("Content-Type") , "application/json" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "GET, POST, HEAD, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
						
		logger.info(" LIST_URLCONFIGS has been executed ") ; 
	}
	
	@Test(priority = 7)
	@Description("Verify the URL Configuration details for a given URL Configuration")
	@Severity(SeverityLevel.NORMAL)
	public void retreiveUrlConfigTestCase () throws IOException
	{
		String authorizationToken = AuthorizationDetails.getAuthorizationToken() ;
		String urlConfigId = "57cd0432-00f0-499b-a197-5c810bd5966c" ;
		
		Response response = UrlConfigEndPoints.retreiveUrlConfig(authorizationToken , urlConfigId) ;
		response.then().log().all() ;
		
		JsonPath jsonPathView =  response.jsonPath() ;
		
		Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath ("retreiveUrlConfig.json")).toString().contains("ValidatableResponse") , true ) ;
		Assert.assertEquals (response.getStatusCode() , 200) ; 
		Assert.assertEquals(response.getStatusLine() , "HTTP/1.1 200 OK") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		Assert.assertEquals ( jsonPathView.get("id").toString() , "57cd0432-00f0-499b-a197-5c810bd5966c" ) ;
		Assert.assertEquals ( jsonPathView.get("project").toString() , "b0df0e5a-78fe-4425-bdaf-7777970191f6" ) ;
		Assert.assertEquals ( jsonPathView.get("category").toString() , "research" ) ;
		Assert.assertEquals ( jsonPathView.get("fields[0].name").toString() , "Portal_Type_2" ) ;
		Assert.assertEquals ( jsonPathView.get("fields[0].source_name").toString() , "worker" ) ;
		Assert.assertEquals ( jsonPathView.get("match_params").toString() ,"{}" ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.url").toString() ,"https://work.alegion.com/worker-portal/tasks/8700bd01-6277-4851-85f6-57aa1e953744" ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.fields[0].left").toString() ,"154" ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.fields[0].color").toString() ,"#dc2626" ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.fields[0].endAt").toString() ,"31" ) ;
		Assert.assertEquals ( jsonPathView.getFloat("gc_config.fields[0].width") , 42.02082824707031 ) ;
		Assert.assertEquals ( jsonPathView.getFloat("gc_config.fields[0].height") , 15.33331298828125 ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.fields[0].startAt").toString() ,"25" ) ;
		Assert.assertEquals ( jsonPathView.get("created_at").toString() ,"2024-01-24T08:09:19.690841Z" ) ;
		
		Assert.assertEquals( response.getHeader("Content-Type") , "application/json" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "GET, PUT, PATCH, DELETE, HEAD, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
						
		logger.info(" RETREIVE_URLCONFIG has been executed ") ; 
	}
	
	@Test(priority = 8)
	@Description("Verify the URL Configuration details for a given URL")
	@Severity(SeverityLevel.NORMAL)
	public void validateUrlConfigTestCase () throws IOException
	{
		String authorizationToken = AuthorizationDetails.getAuthorizationToken() ;
		
		UrlConfigValidateUrlPayload urlConfigValidateUrlPayload = new UrlConfigValidateUrlPayload () ;
		
		urlConfigValidateUrlPayload.setUrl("https://www.google.com/2") ;
		
		Response response = UrlConfigEndPoints.validateUrlConfig(authorizationToken , urlConfigValidateUrlPayload) ;
		response.then().log().all() ;
		
		JsonPath jsonPathView =  response.jsonPath() ;
		
		Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath ("validateUrlConfig.json")).toString().contains("ValidatableResponse") , true ) ;
		Assert.assertEquals (response.getStatusCode() , 200) ;
		Assert.assertEquals(response.getStatusLine() , "HTTP/1.1 200 OK") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		Assert.assertEquals ( jsonPathView.get("urlconfig.id").toString() , "f715bd0f-1ccc-4a16-acae-c06ecae369af" ) ;
	    Assert.assertEquals ( jsonPathView.get("urlconfig.project").toString() , "ad4e0077-03c7-4777-8144-980f60fd6987" ) ;
		Assert.assertEquals ( jsonPathView.get("urlconfig.category").toString() , "task" ) ;
		Assert.assertEquals ( jsonPathView.get("urlconfig.fields[0].name").toString() , "Number" ) ;
		Assert.assertEquals ( jsonPathView.get("urlconfig.fields[0].source").toString() , "url" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("urlconfig.fields[0].default")) ,  "null" ) ;
	    Assert.assertEquals ( jsonPathView.get("urlconfig.fields[0].source_name").toString() , "2" ) ;
        Assert.assertEquals ( jsonPathView.get("urlconfig.match_params").toString() ,"{}" ) ;
	    Assert.assertEquals ( jsonPathView.get("urlconfig.gc_config.url").toString() , "https://www.google.com/2" ) ;
		Assert.assertEquals ( jsonPathView.get("urlconfig.gc_config.fields[0].left").toString() , "151" ) ;
	    Assert.assertEquals ( jsonPathView.get("urlconfig.gc_config.fields[0].color").toString() , "#115e59" ) ;
		Assert.assertEquals ( jsonPathView.get("urlconfig.gc_config.fields[0].endAt").toString() , "24" ) ;
		Assert.assertEquals ( jsonPathView.getFloat("urlconfig.gc_config.fields[0].width") , 7.8020782470703125 ) ;
		Assert.assertEquals ( jsonPathView.getFloat("urlconfig.gc_config.fields[0].height") , 15.333343505859375 ) ;
		Assert.assertEquals ( jsonPathView.get("urlconfig.regex").toString() , "https://www.google.com/(?P<Number>.*)" ) ;
		Assert.assertEquals ( jsonPathView.get("urlconfig.created_at").toString() , "2024-01-25T16:43:01.284263Z" ) ;
		Assert.assertEquals ( jsonPathView.get("urlconfig.updated_at").toString() , "2024-01-25T16:43:28.706114Z" ) ;
		Assert.assertEquals ( jsonPathView.get("parsed_fields.category").toString() , "task" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("parsed_fields.Number")) ,  "null" ) ;
		
		Assert.assertEquals( response.getHeader("Content-Type") , "application/json" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "POST, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
						
		logger.info(" VALIDATE_URLCONFIG has been executed ") ; 
	}
	
	@Test(priority = 9)
	@Description("Create a new URL Configuration for a given Project")
	@Severity(SeverityLevel.NORMAL)
	public void createUrlConfigTestCase () throws IOException
	{
		String authorizationToken = AuthorizationDetails.getAuthorizationToken() ;
		String projectId = "b0df0e5a-78fe-4425-bdaf-7777970191f6" ;
		
		UrlConfigGCConfig UrlConfigGCConfig = new UrlConfigGCConfig() ;
		UrlConfigCreateUrlPayload urlConfigCreateUrlPayload = new UrlConfigCreateUrlPayload () ;
		
		UrlConfigGCConfig.setUrl("https://work.alegion.com/worker-portal/tasks/8700bd01-6277-4851-85f6") ;
		
		urlConfigCreateUrlPayload.setCategory("task") ;
		urlConfigCreateUrlPayload.setUrlConfigGCConfig(UrlConfigGCConfig) ;
		urlConfigCreateUrlPayload.setRegex("https://work.alegion.com/worker-portal/tasks/(?P<Task_Id>.*)") ;
		
		ObjectMapper objectMapper = new ObjectMapper() ; 
		String createUrlPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(urlConfigCreateUrlPayload) ;
				
		Response response = UrlConfigEndPoints.createUrlConfig(authorizationToken , projectId , createUrlPayload) ;
		response.then().log().all() ;
		
		JsonPath jsonPathView =  response.jsonPath() ;
		
		Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath ("createUrlConfig.json")).toString().contains("ValidatableResponse") , true ) ;
		Assert.assertEquals (response.getStatusCode() , 201) ; 
		Assert.assertEquals(response.getStatusLine() , "HTTP/1.1 201 Created") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		Assert.assertEquals ( jsonPathView.get("id").toString().length() , 36 ) ;
		Assert.assertEquals ( jsonPathView.get("id").toString().contains("-") , true ) ;
		Assert.assertEquals ( jsonPathView.get("project").toString() , "b0df0e5a-78fe-4425-bdaf-7777970191f6" ) ;
		Assert.assertEquals ( jsonPathView.get("category").toString() , "task" ) ;
		Assert.assertEquals ( jsonPathView.get("fields").toString() ,"[]" ) ;
		Assert.assertEquals ( jsonPathView.get("match_params").toString() ,"{}" ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.url").toString() , "https://work.alegion.com/worker-portal/tasks/8700bd01-6277-4851-85f6" ) ;
		Assert.assertEquals ( jsonPathView.get("regex").toString() , "https://work.alegion.com/worker-portal/tasks/(?P<Task_Id>.*)" ) ;
		Assert.assertEquals ( jsonPathView.get("created_at").toString().substring(0, 11) , jsonPathView.get("updated_at").toString().substring(0, 11) ) ;
		
		Assert.assertEquals( response.getHeader("Content-Type") , "application/json" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "GET, POST, HEAD, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
				
		latestUrlConfigId =  jsonPathView.get("id").toString() ;
		
		logger.info(" CREATE_URLCONFIG has been executed ") ; 
	}
	
	@Test(priority = 10)
	@Description("Update an existing URL Configuration")
	@Severity(SeverityLevel.NORMAL)
	public void updateUrlConfigTestCase () throws IOException
	{
		String authorizationToken = AuthorizationDetails.getAuthorizationToken() ;
		String urlConfigId = "57cd0432-00f0-499b-a197-5c810bd5966c" ;
		
		UrlConfigFields urlConfigFields = new UrlConfigFields() ;
		UrlConfigUpdateUrlPayload urlConfigUpdateUrlPayload  = new UrlConfigUpdateUrlPayload () ;
		
		urlConfigFields.setName("Portal_Type_2") ;
		urlConfigFields.setSource_name("worker") ;
		
		List <UrlConfigFields> listOfFields = new ArrayList<UrlConfigFields> () ;
		listOfFields.add(urlConfigFields) ;
		
		urlConfigUpdateUrlPayload.setCategory("research") ;
		urlConfigUpdateUrlPayload.setFields(listOfFields) ;
				
		Response response = UrlConfigEndPoints.updateUrlConfig(authorizationToken , urlConfigId , urlConfigUpdateUrlPayload) ;
		response.then().log().all() ;
		
		JsonPath jsonPathView =  response.jsonPath() ;
		
		Assert.assertEquals(response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath ("updateUrlConfig.json")).toString().contains("ValidatableResponse") , true ) ;
		Assert.assertEquals (response.getStatusCode() , 200) ; 
		Assert.assertEquals(response.getStatusLine() , "HTTP/1.1 200 OK") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		Assert.assertEquals ( jsonPathView.get("project").toString() , "b0df0e5a-78fe-4425-bdaf-7777970191f6" ) ;
		Assert.assertEquals ( jsonPathView.get("category").toString() , "research" ) ;
		Assert.assertEquals ( jsonPathView.get("fields[0].name").toString() , "Portal_Type_2" ) ;
		Assert.assertEquals ( jsonPathView.get("fields[0].source_name").toString() , "worker" ) ;
		Assert.assertEquals ( jsonPathView.get("match_params").toString() ,"{}" ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.url").toString() , "https://work.alegion.com/worker-portal/tasks/8700bd01-6277-4851-85f6-57aa1e953744" ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.fields[0].left").toString() , "154" ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.fields[0].color").toString() , "#dc2626" ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.fields[0].endAt").toString() , "31" ) ;
		Assert.assertEquals ( jsonPathView.getFloat("gc_config.fields[0].width") , 42.02082824707031 ) ;
		Assert.assertEquals ( jsonPathView.getFloat("gc_config.fields[0].height") , 15.33331298828125 ) ;
		Assert.assertEquals ( jsonPathView.get("gc_config.fields[0].startAt").toString() , "25" ) ;
		Assert.assertEquals ( jsonPathView.get("regex").toString() , "https://work.alegion.com/(?P<Portal_Type_2>.*)-portal/tasks/8700bd01-6277-4851-85f6-57aa1e953744" ) ;
		Assert.assertEquals ( jsonPathView.get("created_at").toString() , "2024-01-24T08:09:19.690841Z" ) ;
		
		Assert.assertEquals( response.getHeader("Content-Type") , "application/json" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "GET, PUT, PATCH, DELETE, HEAD, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
				
		logger.info(" UPDATE_URLCONFIG has been executed ") ; 
	}
	
	@Test(priority = 11 , dependsOnMethods = { "createUrlConfigTestCase" })
	@Description("Delete the newly created URL Configuration")
	@Severity(SeverityLevel.NORMAL)
	public void deleteUrlConfigTestCase () throws IOException
	{
		String authorizationToken = AuthorizationDetails.getAuthorizationToken() ;
		String urlConfigId = latestUrlConfigId ;
		
		Response response = UrlConfigEndPoints.deleteUrlConfig(authorizationToken , urlConfigId) ;
		response.then().log().all() ;
		
		Assert.assertEquals (response.getStatusCode() , 204) ; 
		Assert.assertEquals(response.getStatusLine() , "HTTP/1.1 204 No Content") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		
		Assert.assertEquals( response.getHeader("Content-Type") , "text/plain; charset=utf-8" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "GET, PUT, PATCH, DELETE, HEAD, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
						
		logger.info(" DELETE_URLCONFIG has been executed ") ; 
	}
	
	@AfterClass
	public void finishUrlConfiguratorTestCases  ()
	{
		logger.info(" API Test Execution ended for URL CONFIGURATOR ") ; 
	}

}
