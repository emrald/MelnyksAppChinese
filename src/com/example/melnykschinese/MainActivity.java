package com.example.melnykschinese;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	Button btnlogin,btnsignup;
	Context context;
	AlertDialog alert;
	HttpURLConnection connection ;
	BufferedInputStream content;
	StringBuffer stringBuilder;
	@SuppressLint("NewApi")
	
	static ProgressDialog dialog;
    private Thread downloadThread;
    final static Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            dialog.dismiss();

        }

    };

    protected void onDestroy() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        super.onDestroy();
    }
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnsignup=(Button)findViewById(R.id.activity_main_btn_signup);
		btnlogin=(Button)findViewById(R.id.activity_main_btn_login);
		context=getApplicationContext();
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		btnlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				LayoutInflater inflater=getLayoutInflater();
				View layout=inflater.inflate(R.layout.alertlayout,null,false);
				
				final EditText etname=(EditText)layout.findViewById(R.id.alert_et_name);
				final EditText etpassword=(EditText)layout.findViewById(R.id.alert_et_password);
				final AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
				builder.setMessage("Login");
				
				builder.setView(layout);
				builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						String name=etname.getText().toString();
						String password=etpassword.getText().toString();
						
						final ProgressDialog progress=ProgressDialog.show(MainActivity.this,"","Loading");
						 new Thread()
						  {
						   public void run()
						   {
						     try{
						      MainActivity.this.runOnUiThread(new Runnable(){
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
						}.start();
						
						
							Intent i=new Intent(MainActivity.this,EpisodeActivity.class);
							startActivity(i);
						}
						/*else
							builder.setMessage("Login Failed");
							*/
							/* try {
							        // URL url = new URL ("http://ip:port/download_url");
							        URL url = new URL("http://melnyks.com/amember/audio/premium.xml");
							        String authStr = name + ":" + password;
							        byte[] authEncoded = Base64.encode(authStr.getBytes(), 1);

							        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
							        connection.setRequestMethod("GET");
							        connection.setDoOutput(true);
							        connection.setRequestProperty("Authorization", "Basic " + authEncoded);

							        content = new BufferedInputStream(connection.getInputStream());
							        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
							        String line;
							        while ((line = reader.readLine()) != null) {
							            stringBuilder.append(line);
							        }
							    }
							    catch (Exception e) {
							        e.printStackTrace();
							    }*/

							
							/*URL Url = null;
							try {
								Url = new URL("http://melnyks.com/amember/audio/premium.xml");
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
							Authenticator.setDefault(new Authenticator () {
							    protected PasswordAuthentication getPasswordAuthentication() {
							        return (new PasswordAuthentication(name, password.toCharArray()));
							    }
							});

							try {
								connection = (HttpURLConnection) Url.openConnection();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 try {
								connection.setRequestMethod("GET");
							} catch (ProtocolException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						        connection.setDoOutput(true);
							connection.setRequestProperty("Authorization", "Basic");

							try {
								content = new BufferedInputStream(connection.getInputStream());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							BufferedReader reader = new BufferedReader(new InputStreamReader(content));
							String line;
							try {
								while ((line = reader.readLine()) != null) {
								    stringBuilder.append(line);
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
						/*String authStr = null;
						try {
					        // URL url = new URL ("http://ip:port/download_url");
					        URL url = new URL("http://melnyks.com/amember/audio/premium.xml");
					        authStr = name + ":" + password;
					        byte[] authEncoded = Base64.encode(authStr.getBytes(), 0);

					        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					        connection.setRequestMethod("GET");
					        connection.setDoOutput(true);
					        connection.setRequestProperty("Authorization", "Basic " + authEncoded);

					        content = new BufferedInputStream(connection.getInputStream());
					        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					        String line;
					        while ((line = reader.readLine()) != null) {
					            stringBuilder.append(line);
					        }
					    }
					    catch (Exception e) {
					        e.printStackTrace();
					    }*/
						/*URL Url = null;
						try {
							Url = new URL("http://melnyks.com/amember/audio/premium.xml");
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Authenticator.setDefault(new Authenticator () {
						    protected PasswordAuthentication getPasswordAuthentication() {
						        return (new PasswordAuthentication("username", "password".toCharArray()));
						    }
						});

						try {
							connection = (HttpURLConnection) Url.openConnection();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						connection.setRequestProperty("Authorization", "Basic");

						try {
							content = new BufferedInputStream(connection.getInputStream());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						BufferedReader reader = new BufferedReader(new InputStreamReader(content));
						String line;
						try {
							while ((line = reader.readLine()) != null) {
							    stringBuilder.append(line);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						
						/* String webPage = "http://melnyks.com/amember/audio/premium.xml";
						     name = "test";
						     password = "MandariN10000";

						    String authString = name + ":" + password;
						    byte[] authEncBytes = Base64.encode(authString.getBytes(),0);
						    String authStringEnc = new String(authEncBytes);

						    URL url = null;
							try {
								url = new URL(webPage);
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    URLConnection urlConnection = null;
							try {
								urlConnection = url.openConnection();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
						    InputStream is = null;
							try {
								is = urlConnection.getInputStream();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    InputStreamReader isr = new InputStreamReader(is);
						    BufferedReader reader = isr;//new BufferedReader(new InputStreamReader(content));
							String line;
							try {
								while ((line = reader.readLine()) != null) {
								    stringBuilder.append(line);
								}
							}*/
			//		}
						    /*String line;
					        while ((line = reader.readLine()) != null) {
					            stringBuilder.append(line);
					        }*/
							
							//if(name.equals("test") && password.equals("MandariN10000"))
						/*if(authStr.equals(content))
							{
								Intent i=new Intent(MainActivity.this,EpisodeActivity.class);
								startActivity(i);
							}
							else
								builder.setMessage("Login Failed");
*/
			
		});
				
				builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
					}
				});
				alert=builder.create();
				alert.show();
				
			}
		});
	
	}
	static public class MyThread extends Thread {
        @Override
        public void run() {

            try {
                // Simulate a slow network
                try {
                    new Thread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);

            } finally {

            }
        }
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
