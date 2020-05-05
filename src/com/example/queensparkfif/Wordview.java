package com.example.queensparkfif;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Wordview extends Activity {
	TextView anntext;
	InputStream is;
	String TheResults;
	Intent v;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wordview);
		anntext=(TextView)findViewById(R.id.textView2);
		v=getIntent();
		new Loadata().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	class Loadata extends AsyncTask<String,String,String>{
		
		

		@Override
		protected String doInBackground(String... arg) {
		TheResults=makeRequest("http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fword/"+v.getStringExtra("str"),"GET");
			return null;
		}
		protected void onPreExecute(){
		super.onPreExecute();
		
		
		}
		protected void onPostExecute(String o){
			
			
			runOnUiThread(new Runnable(){
			public void run(){
				
			anntext.setText(TheResults);
			}
			});
		}
		
	}
	public String makeRequest(String url,String method) {
		String result=null;
		try{
		
		if (method=="GET"){
			DefaultHttpClient client=new DefaultHttpClient();
			HttpGet get=new HttpGet(url);
			HttpResponse hresponse=client.execute(get);
			HttpEntity hentity=hresponse.getEntity();
			is=hentity.getContent();
		}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		try{
			BufferedReader reader= new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			String line=null;
			StringBuilder sb=new StringBuilder();
			while((line=reader.readLine())!=null){
				sb.append("\n"+line +"\n");
				
			}
			is.close();
			result=sb.toString();
			
			
		}
		catch(IOException u){
			u.printStackTrace();
		}
		
		
		
		
		return result;
	}
	
	
	
	
	

}
