package com.example.melnykschinese;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

import android.util.Log;

public class EpisodeHandler implements ContentHandler{

	static ArrayList<Model> arrchaneltitle,arrchaneldesc,arritemtitle,arritemurl,arritemlength,arritemtype;
	 Model obj;
	static Model objitem;
	static Model objurl;
	public boolean IN_CH_TITLEFLAG=false;
	public boolean IN_CH_DESCFLAG=false;
	public boolean IN_ITEM_TITLE_FLAG=false;
	public boolean IN_ITEM_URL_FLAG=false;
	public boolean IN_ITEM_LENGTH_FLAG=false;
	public boolean IN_ITEM_TYPE_FLAG=false;
	public boolean IN_TITLE_FLAG=true;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		if(IN_CH_TITLEFLAG)
		{
			objitem.setchaneltitle(new String(ch,start,length));
			Log.e("chtitle",new String(ch,start,length));
		}
		if(IN_CH_DESCFLAG)
		{
			objitem.setchaneldesc(new String(ch,start,length));
			Log.e("chdesc",new String(ch,start,length));
		}
		if(IN_ITEM_TITLE_FLAG)
		{
			obj.setitemtitle(new String(ch,start,length));
			Log.e("itemtitle",new String(ch,start,length));
		}
		
		
	}
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		
		if(localName.equalsIgnoreCase("title"))
		{
		
			if(IN_CH_TITLEFLAG==true)
			{
				//arrchaneltitle.add(obj);
				IN_CH_TITLEFLAG=false;
			} 
			else
			{	
				arritemtitle.add(obj);
				IN_ITEM_TITLE_FLAG=false;
			}
		}
			
		if(localName.equalsIgnoreCase("description"))
			{
				//arrchaneldesc.add(obj);
				IN_CH_DESCFLAG=false;
			}
		
			
	}
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
	
		objitem=new Model();
	
		arrchaneltitle=new ArrayList<Model>();
		arrchaneldesc=new ArrayList<Model>();
		arritemtitle=new ArrayList<Model>();
		arritemurl=new ArrayList<Model>();
		arritemlength=new ArrayList<Model>();
		arritemtype=new ArrayList<Model>();
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		// TODO Auto-generated method stub
		
		obj=new Model();
		objurl=new Model();
		if(IN_TITLE_FLAG)
		{
			if(localName.equalsIgnoreCase("title"))
			{
				//obj.setchaneltitle("title");
				IN_CH_TITLEFLAG=true;
			}
		
			if(localName.equalsIgnoreCase("description"))
			{
				//obj.setchaneldesc("description");
				IN_CH_DESCFLAG=true;
				IN_TITLE_FLAG=false;
			}
		}
		
		if(IN_TITLE_FLAG==false && localName.equalsIgnoreCase("title"))
		{
				//obj.setitemtitle("title");
				IN_ITEM_TITLE_FLAG=true;
			
		}
		
		if(localName.equalsIgnoreCase("enclosure"))
		{
				objurl.setitemenclouserurl(atts.getValue("url"));
				arritemurl.add(objurl);
				obj.setitemenclouserlength(atts.getValue("legth"));
				arritemlength.add(obj);
				obj.setitemenclousertype(atts.getValue("type"));
				arritemtype.add(obj);
				Log.e("url",objurl.getitemenclouserurl());
				Log.e("legth",atts.getValue("legth"));
				Log.e("type",atts.getValue("type"));
				
		}
			
	}
	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
	

}
