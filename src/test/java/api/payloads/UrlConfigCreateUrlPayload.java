package api.payloads ;

public class UrlConfigCreateUrlPayload
{

	private String category ;
	private String regex ;
	private UrlConfigGCConfig  gc_config ;
	
	public String getCategory()
	{
		return category ;
	}
	
	public void setCategory(String category)
	{
		this.category = category ;
	}
	
	public String getRegex()
	{
		return regex ;
	}
	
	public void setRegex(String regex)
	{
		this.regex = regex ;
	}

	public UrlConfigGCConfig getGc_config()
	{
		return gc_config ;
	}

	public void setUrlConfigGCConfig(UrlConfigGCConfig gc_config)
	{
		this.gc_config = gc_config ;
	}
		
}
