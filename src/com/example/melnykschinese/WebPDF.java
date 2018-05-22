package com.example.melnykschinese;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebPDF extends Activity{
	
	Bundle extras = WebPDF.this.getIntent().getExtras(); 
   //  String text_url = extras.getString("URL");
	 String text_url = getIntent().getExtras().getString("URL");
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    WebView mWebView=new WebView(WebPDF.this);
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    mWebView.getSettings().setPluginsEnabled(true);
	    mWebView.loadUrl(text_url);
	    setContentView(mWebView);
	  }
	}