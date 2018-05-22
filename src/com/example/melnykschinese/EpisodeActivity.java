package com.example.melnykschinese;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class EpisodeActivity extends Activity{

	static URL url;
	ListView lv;
	CustomAdaptor adaptor; 
	Context context;
	ArrayList<Model> arlistitemtitle;
	TextView tvtitledetail,tvsubtitledetail;
	HttpURLConnection connection;
	StringBuilder stringBuilder;
	BufferedInputStream content;
	ProgressDialog pd;
	ProgressBar spinnerdialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.episodelist);

		lv=(ListView)findViewById(R.id.episodelist_listview);
		tvtitledetail=(TextView)findViewById(R.id.episodelist_tv_detail);
		tvsubtitledetail=(TextView)findViewById(R.id.episodelist_tv_subdetail);
	//	spinnerdialog = (ProgressBar)findViewById(R.id.progressBar1);
		

		try{
			URL url=new URL("http://melnyks.com/amember/audio/premium.xml");

			SAXParserFactory spx=SAXParserFactory.newInstance();
			SAXParser sp=spx.newSAXParser();	

			XMLReader xr=sp.getXMLReader();

			EpisodeHandler handler=new  EpisodeHandler();
			xr.setContentHandler(handler);

			xr.parse(new InputSource(url.openStream()));      

			tvtitledetail.setText(EpisodeHandler.objitem.gettchaneltitle()); 
			tvsubtitledetail.setText(EpisodeHandler.objitem.getchaneldesc());
			arlistitemtitle=EpisodeHandler.arritemtitle;
			adaptor=new CustomAdaptor(EpisodeActivity.this,arlistitemtitle);
			lv.setAdapter(adaptor);

	//		spinnerdialog.setVisibility(View.INVISIBLE);
			
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					// TODO Auto-generated method stub

					//(Model)lv.getAdapter().getItem(arg2))().getItem(arg2)).gettchaneltitle();
					/*final ProgressDialog progress=ProgressDialog.show(EpisodeActivity.this,"","Loading");
					 new Thread()
					  {
					   public void run()
					   {
					     try{
					      EpisodeActivity.this.runOnUiThread(new Runnable(){
					      @Override
					      public void run() {
					       // TODO Auto-generated method stub
					               // your code
					      }
					      });
					     }
					     catch(Exception e)
					     {
					     }
					     progress.dismiss();
					   }
					}.start();*/
			//		spinnerdialog.setVisibility(View.VISIBLE);
					
					String url=EpisodeHandler.arritemurl.get(arg2).getitemenclouserurl();
					Log.e("url",url);

					/*try {
			             DefaultHttpClient httpclient = new DefaultHttpClient();

			             httpclient.getCredentialsProvider().setCredentials(
			                     new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM), 
			                     new UsernamePasswordCredentials("test", "MandariN10000"));

			             HttpPost httppost = new HttpPost(url);

			             System.out.println("executing request " + httppost.getRequestLine());
			             HttpResponse response;
			             response = httpclient.execute(httppost);
			             HttpEntity entity = response.getEntity();

			             System.out.println("----------------------------------------");
			             System.out.println(response.getStatusLine());
			             if (entity != null) {
			                 System.out.println("Response content length: " + entity.getContentLength());
			             }
			             if (entity != null) {
			                entity.consumeContent();
			               // String data= EntityUtils.toString(entity);

			             }

			             httpclient.getConnectionManager().shutdown();  
			         } catch (ClientProtocolException e) {
			             // TODO Auto-generated catch block
			             e.printStackTrace();
			         } catch (IOException e) {
			             // TODO Auto-generated catch block
			             e.printStackTrace();
			         }*/
					/* Intent intent = new Intent(Intent.ACTION_VIEW);
					  WebView mWebView=new WebView(EpisodeActivity.this);
					  mWebView.getSettings().setJavaScriptEnabled(true);
				        mWebView.getSettings().setAllowFileAccess(true);
				        mWebView.getSettings().setPluginsEnabled(true);
					 */
					URL Url = null;
					try {
						Url = new URL(url);
					} catch (MalformedURLException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}

					Authenticator.setDefault(new Authenticator () {
						protected PasswordAuthentication getPasswordAuthentication() {
							
							return (new PasswordAuthentication("test", "MandariN10000".toCharArray()));
						}
					});

					try {
						connection = (HttpURLConnection) Url.openConnection();
					} catch (IOException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					connection.setRequestProperty("Authorization", "Basic");

					try {
						content = new BufferedInputStream(connection.getInputStream());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					String line;
					try {
						while ((line = reader.readLine()) != null) {
							stringBuilder.append(line);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(url.endsWith(".pdf"))
					{
						//	String log = getB64Auth("test", "MandariN10000");
						Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
						intent.setType("application/pdf");
						//intent.setDataAndType(Uri.parse(url), "application/pdf");
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						try {
							startActivity(intent);
						} 
						catch (ActivityNotFoundException e) {
							Toast.makeText(getApplicationContext(), 
									"No Application Available to View PDF", 
									Toast.LENGTH_SHORT).show();
							//							Intent i= new Intent(getApplicationContext(),WebPDF.class);
							//	                    	i.putExtra("URL", url);
							//	                    	startActivity(i);
						}
						//	startActivity(intent);
					}
					else
					{
						Toast.makeText(getApplicationContext(), "INVALID",Toast.LENGTH_SHORT).show();
					}

				}     	 

			});

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.e("Err",e+"");
		}



	}     

	private class GetCategoryList extends AsyncTask<Void, Void, Integer>{
		ArrayList<Model> data;
		@Override
		protected void onPreExecute() {
			pd = ProgressDialog.show(EpisodeActivity.this.getParent(), "Please wait", "Loading...");
			super.onPreExecute();
		}
		@Override
		protected Integer doInBackground(Void... params) {
			try {
				String url = "http://melnyks.com/amember/audio/premium.xml";

				HttpGet httpGet = new HttpGet(url);
				HttpParams httpParameters = new BasicHttpParams();
				int timeoutConnection = 3000;
				HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
				DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
				HttpResponse response = httpClient.execute(httpGet);
				InputStream is = response.getEntity().getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				StringBuffer buffer = new StringBuffer();
				String line = null;
				while ((line = br.readLine()) != null) {
					buffer.append(line);
					buffer.append('\r');
				}
				if(br != null) br.close();
				if(is != null) is.close();
				//		data = XMLtoModel.getAllCategories(buffer.toString());

				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				return 1;
			}
		}
		@Override
		protected void onPostExecute(Integer result) {
			/*if(result == 1){
				Toast.makeText(EpisodeActivity.this, "No Internet or exception occured.", Toast.LENGTH_SHORT).show();
			}else{
				adapter = new CACategoryList(MomentoActivity.this.getParent(), R.layout.row_momento, data);
				lvCatList.setAdapter(adapter);
			}*/
			pd.dismiss();
			super.onPostExecute(result);
		}

	}
	private String getB64Auth (String login, String pass) {
		String source=login+":"+pass;
		String ret="Basic "+Base64.encodeToString(source.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP);
		return ret;
	}
	
}
