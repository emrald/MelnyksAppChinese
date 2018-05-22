package com.example.melnykschinese;

public class Model {

	
	String chaneltitle,chaneldesc,itemtitle,itemenclouserurl,itemenclousertype,itemenclouselength;
	
	
	public void setchaneltitle(String chaneltitle)
	{
		this.chaneltitle=chaneltitle;
	}
	
	public void setchaneldesc(String chaneldesc)
	{
		this.chaneldesc=chaneldesc;
	}
	
	public void setitemtitle(String itemtitle)
	{
		this.itemtitle=itemtitle;
	}
	
	public void setitemenclouserurl(String itemenclouserurl)
	{
		this.itemenclouserurl=itemenclouserurl;
	}
	
	public void setitemenclouserlength(String itemenclouselength)
	{
		this.itemenclouselength=itemenclouselength;
	}
	
	public void setitemenclousertype(String enclousertype)
	{
		this.itemenclousertype=itemenclousertype;
	}
		
	public String gettchaneltitle()
	{
		return this.chaneltitle;
	}
	
	public String getchaneldesc()
	{
		return this.chaneldesc;
	}
	
	public String getitemtitle()
	{
		return this.itemtitle;
	}
	
	public String getitemenclouserurl()
	{
		return this.itemenclouserurl;
	}
	
	public String getitemenclouserlength()
	{
		return this.itemenclouselength;
	}
	
	public String getitemenclousertype()
	{
		return this.itemenclousertype;
	}
}
