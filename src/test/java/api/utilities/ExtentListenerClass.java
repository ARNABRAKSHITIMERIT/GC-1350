package api.utilities ;

import java.sql.Timestamp ;

import org.testng.ITestContext ;
import org.testng.ITestListener ;
import org.testng.ITestResult ;

import com.aventstack.extentreports.ExtentReports ;
import com.aventstack.extentreports.ExtentTest ;
import com.aventstack.extentreports.Status ;
import com.aventstack.extentreports.markuputils.ExtentColor ;
import com.aventstack.extentreports.markuputils.MarkupHelper ;
import com.aventstack.extentreports.reporter.ExtentSparkReporter ;
import com.aventstack.extentreports.reporter.configuration.Theme ;

public class ExtentListenerClass implements ITestListener 
{

	ExtentSparkReporter htmlReporter ;
	ExtentReports reports ;	
	ExtentTest test ;	

	public void configureReport () 
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()) ; 
		String timestamp_string=timestamp.toString() ; 
		String reportname = ("GC_API_Automation_Test_Report.html" + " " +  timestamp_string) ;
		reportname = reportname.replace(":" , "-") ;
		htmlReporter = new 	ExtentSparkReporter(System.getProperty("user.dir") + "//reports//" + reportname) ;
		reports = new ExtentReports () ;
		reports.attachReporter(htmlReporter) ;	
		reports.setSystemInfo("Machine : " , " Arnab Rakshit - IMerit Laptop" ) ;	
		reports.setSystemInfo("Operating System : " , System.getProperty("os.name") ) ;
		reports.setSystemInfo("User Name :" , System.getProperty("user.name")) ;
		reports.setSystemInfo("Web Application :" , "IMerit Engine") ;
		reports.setSystemInfo("Environment :" , "Stage") ;
		htmlReporter.config().setDocumentTitle("Extent Report Itest_Listener") ;
		htmlReporter.config().setReportName("Extent Report of Ground Control API Automation") ;
		htmlReporter.config().setTheme(Theme.DARK) ;
		htmlReporter.config().setTimeStampFormat("EEEE , MMMM dd , yyyy , hh:mm a '('zzz')' ") ; 
	}

	public void onStart (ITestContext Result)
	{
		configureReport() ;
		System.out.println("On Start Method Invoked ....") ; 
	}

	public void onFinish (ITestContext Result)
	{
		System.out.println("On Finish Method Invoked ....") ;
		reports.flush() ; 
	}

	public void onTestStart (ITestResult Result ) 
	{	
		System.out.println("Name of Test Method Started : " + Result.getName()) ; 
	}

	public void onTestSuccess (ITestResult Result ) 
	{	
		System.out.println("Name of Test Method Successfully Executed : " + Result.getName()) ;
		test = reports.createTest(Result.getName()) ;	
		test.log(Status.PASS , MarkupHelper.createLabel("Name of the Passed Test Case is : " + Result.getName() , ExtentColor.GREEN)) ; 
	}	

	public void onTestFailure (ITestResult Result) 
	{	
		System.out.println("Name of Test Method Failed : " + Result.getName()) ;
		test = reports.createTest(Result.getName()) ;	
		test.log(Status.FAIL , MarkupHelper.createLabel("Name of the Failed Test Case is : " + Result.getName() , ExtentColor.RED)) ; 
	}

	public void onTestSkipped (ITestResult Result ) 
	{	
		System.out.println("Name of Test Method Skipped : " + Result.getName()) ;
		test = reports.createTest(Result.getName()) ;	
		test.log(Status.SKIP , MarkupHelper.createLabel("Name of the Skipped Test Case is : " + Result.getName() , ExtentColor.YELLOW)) ; 
	}	

	public void onTestFailedButWithinSuccessPercentage (ITestResult Result ) 
	{
	}

}
