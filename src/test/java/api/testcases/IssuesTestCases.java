package api.testcases ;

import java.io.IOException ;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager ;
import org.apache.logging.log4j.Logger ;
import org.testng.Assert ;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass ;
import org.testng.annotations.Test ;

import api.endpoints.IssuesEndPoints ;
import api.utilities.AuthorizationDetails ;
import io.qameta.allure.Description ;
import io.qameta.allure.Severity ;
import io.qameta.allure.SeverityLevel ;
import io.restassured.module.jsv.JsonSchemaValidator ;
import io.restassured.path.json.JsonPath ;
import io.restassured.response.Response ;

public class IssuesTestCases
{

   public static Logger logger ;

	@BeforeClass
	public void startIssuesTestCases ()
	{
		logger = LogManager.getLogger("GC_API") ; 
		logger.info(" API Test Execution started for ISSUES ") ;
	}

	@Test(priority = 5)
	@Description("Verify the Issue details of a given Issue")
	@Severity(SeverityLevel.MINOR)
	public void retreiveIssueTestCase () throws IOException
	{
		String authorizationToken = AuthorizationDetails.getAuthorizationToken() ;
		String issueId = "114" ;
		
		Response response = IssuesEndPoints.retreiveIssue(authorizationToken , issueId) ;
		response.then().log().all() ;
		
		JsonPath jsonPathView =  response.jsonPath() ;
		
		Assert.assertEquals( response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath ("retreiveIssue.json")).toString().contains("ValidatableResponse") , true ) ;
		Assert.assertEquals ( response.getStatusCode() , 200) ; 
		Assert.assertEquals( response.getStatusLine() , "HTTP/1.1 200 OK") ;
		Assert.assertTrue( response.getTime() < 10000) ;
		Assert.assertEquals ( jsonPathView.get("id").toString() , "114" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("task")) ,  "null" ) ;
		Assert.assertEquals ( jsonPathView.get("url").toString() , "https://app.imerit-stage.io/organizations/8b5c1da6-0d13-4001-b24a-f7bafc005fa5/projects/87b1fe34-f3cd-4434-888e-f418df2fabb5/issues/106&ignore_task=true&read_only=true" ) ;
		Assert.assertEquals ( jsonPathView.get("reporter.username").toString() , "ARNAB_RAKSHIT_TEST_1" ) ;
		Assert.assertEquals ( jsonPathView.getString("reporter.photo").startsWith("https://imerit-engine-stage.s3.amazonaws.com") , true ) ;
		Assert.assertEquals ( jsonPathView.get("reporter.role").toString() , "" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("assignee")) ,  "null" ) ;
		Assert.assertEquals ( jsonPathView.get("watchers[0].id").toString() , "ebd7af1c-1246-4b3b-a1f1-91b592b2c244" ) ;
		Assert.assertEquals ( jsonPathView.get("watchers[0].first_name").toString() , "Arnab" ) ;
		Assert.assertEquals ( jsonPathView.get("watchers[0].last_name").toString() , "Rakshit_Test1" ) ;
		Assert.assertEquals ( jsonPathView.get("watchers[0].username").toString() , "ARNAB_RAKSHIT_TEST_1" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("solution")) ,  "null" ) ;
		Assert.assertEquals ( jsonPathView.get("status").toString() , "open" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("category")) ,  "null" ) ;
		Assert.assertEquals ( jsonPathView.get("description").toString() , "ISSUE _ TEST ID" ) ;
		Assert.assertEquals ( jsonPathView.getString("screenshot").startsWith("https://imerit-engine-stage.s3.amazonaws.com/o/undefined/p/undefined") , true ) ;
		Assert.assertEquals ( jsonPathView.get("escalation").toString() , "teamlead" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("organization")) ,  "null" ) ;
		Assert.assertEquals ( jsonPathView.get("project.id").toString() , "87b1fe34-f3cd-4434-888e-f418df2fabb5" ) ;
		Assert.assertEquals ( jsonPathView.get("project.name").toString() , "5th October 2023 _ Project" ) ;
		Assert.assertEquals ( String.valueOf(jsonPathView.getString("project.organization")) ,  "null" ) ;
		Assert.assertEquals ( jsonPathView.get("events[0].id").toString() , "826c77c9-b5f9-423f-93bd-e59f62865045" ) ;
		Assert.assertEquals ( jsonPathView.get("events[0].issue").toString() , "114" ) ;
		Assert.assertEquals ( jsonPathView.getString("events[0].author.photo").startsWith("https://imerit-engine-stage.s3.amazonaws.com/media/") , true ) ;
		Assert.assertEquals ( jsonPathView.get("events[0].author.username").toString() , "ARNAB_RAKSHIT_TEST_1" ) ;
		Assert.assertEquals ( jsonPathView.get("events[0].event").toString() , "open" ) ;
		Assert.assertEquals ( jsonPathView.get("events[0].detail").toString() ,"{}" ) ;
		Assert.assertEquals ( jsonPathView.get("events[0].created_at").toString() , "2023-11-20T16:34:17.310988Z" ) ;
		Assert.assertEquals ( jsonPathView.get("events[0].updated_at").toString() , "2023-11-20T16:34:17.311016Z" ) ;
		Assert.assertEquals ( jsonPathView.get("reported_at").toString() , "2023-11-20T16:34:17.286359Z" ) ;
		Assert.assertEquals ( jsonPathView.get("updated_at").toString() , "2023-11-20T16:35:25.135877Z" ) ;
		Assert.assertEquals ( jsonPathView.get("filters.assignee[0].username").toString() , "ankus.nayak" ) ;
		Assert.assertEquals ( jsonPathView.get("filters.assignee[0].role").toString() , "team_lead" ) ;
		Assert.assertEquals ( jsonPathView.getString("filters.assignee[0].photo").startsWith("https://imerit-engine-stage.s3.amazonaws.com/media/") , true ) ;
		Assert.assertEquals ( jsonPathView.get("filters.category[0]").toString() , "missing-instructions" ) ;
		Assert.assertEquals ( jsonPathView.get("filters.escalation[0]").toString() , "teamlead" ) ;
		Assert.assertEquals ( jsonPathView.get("filters.escalation[1]").toString() , "project-manager" ) ;
		Assert.assertEquals ( jsonPathView.get("filters.escalation[2]").toString() , "client" ) ;
		
		Assert.assertEquals( response.getHeader("Content-Type") , "application/json" ) ;
		Assert.assertEquals( response.getHeader("Connection") , "keep-alive" ) ;
		Assert.assertTrue(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(response.getHeader("x-amzn-RequestId").toString()).matches()) ;
		Assert.assertEquals( response.getHeader("Referrer-Policy") , "same-origin" ) ;
		Assert.assertEquals( response.getHeader("Allow") , "GET, PUT, PATCH, HEAD, OPTIONS" ) ;
		Assert.assertEquals( response.getHeader("Content-Length") , response.getHeader("x-amzn-Remapped-Content-Length") ) ;
		Assert.assertEquals( response.getHeader("X-Frame-Options") , "DENY" ) ;
		Assert.assertEquals( response.getHeader("X-Content-Type-Options") , "nosniff" ) ;
		Assert.assertEquals( response.getHeader("X-Cache") , "Miss from cloudfront" ) ;
				
		logger.info(" RETREIVE_ISSUE has been executed ") ; 
	}
	
	@AfterClass
	public void finishIssuesTestCases  ()
	{
		logger.info(" API Test Execution ended for ISSUES ") ; 
	}

}
