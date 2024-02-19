package api.endpoints ;

public class Routes
{

	public static String base_url = "https://engine.imerit-stage.io/api/v1/" ;
	
	public static String createToken_url = base_url + "token/" ;
	public static String retreiveProject_url = base_url + "projects/{projectId}/" ;	
	
	public static String listUrlConfigs_url = base_url + "urlconfigs/project/{projectId}/" ;
	public static String retreiveUrlConfig_url = base_url + "urlconfigs/{urlConfigId}/" ;	
	public static String validateUrlConfig_url = base_url + "urlconfigs/validate/" ;
	public static String createUrlConfig_url = base_url + "urlconfigs/project/{projectId}/" ;
	public static String updateUrlConfig_url = base_url + "urlconfigs/{urlConfigId}/" ;
	public static String deleteUrlConfig_url = base_url + "urlconfigs/{urlConfigId}/" ;
	
}
