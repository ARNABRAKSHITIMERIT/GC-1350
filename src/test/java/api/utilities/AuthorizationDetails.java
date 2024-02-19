package api.utilities ;

import java.io.FileInputStream ;
import java.io.IOException ;
import java.util.Properties ;

public class AuthorizationDetails 
{

	public static String getAuthorizationUsername() throws IOException
	{
	String authorizationFilePath = ".\\src\\test\\resources\\authorization.properties" ;
	FileInputStream fileInputStream = new FileInputStream(authorizationFilePath) ;
	
	Properties properties = new Properties() ;
	properties.load(fileInputStream) ;
	
	String authorizationUsername = properties.getProperty("username") ;
	return authorizationUsername ;
	}
	
	public static String getAuthorizationPassword() throws IOException
	{
	String authorizationFilePath = ".\\src\\test\\resources\\authorization.properties" ;
	FileInputStream fileInputStream = new FileInputStream(authorizationFilePath) ;
	
	Properties properties = new Properties() ;
	properties.load(fileInputStream) ;
	
	String authorizationPassword = properties.getProperty("password") ;
	return authorizationPassword ;
	}
	
	public static String getAuthorizationToken() throws IOException
	{
	String authorizationFilePath = ".\\src\\test\\resources\\authorization.properties" ;
	FileInputStream fileInputStream = new FileInputStream(authorizationFilePath) ;
	
	Properties properties = new Properties() ;
	properties.load(fileInputStream) ;
	
	String authorizationToken = properties.getProperty("token") ;
	return authorizationToken ;
	}
	
}
