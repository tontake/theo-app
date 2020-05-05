package com.example.queensparkfif;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AnnouncementList extends Activity{
	 ListView lv1;
	  ProgressDialog pDialog;
	  ArrayAdapter<String>annou;
	  ArrayList<String>products2=new ArrayList<String>(); 
	  ArrayList<String>products;
	  TextView header;
	  String url="http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fjson-api%2Fannouncements.json";
	  EditText ed;
	  String[]ann={"Service Days","Green Basket","Pastor s Deeper Life","Big Sunday","Elder and Deacon Meeting"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listwrapper);
		lv1=(ListView)findViewById(R.id.listView1);
		header=(TextView)findViewById(R.id.Header);
		
		header.setText("Announcements");
		ed=(EditText)findViewById(R.id.mysearch);
		new ReadJson().execute(url);
		//lv1.setAdapter(theann);
		 lv1.setTextFilterEnabled(true);
		 lv1.setOnItemClickListener(new OnItemClickListener(){

				

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Intent i=new Intent(AnnouncementList.this,AnnouncementView.class);
					i.putExtra("str", "id"+products2.get(arg2)+".txt");
					//i.putExtra("str", "id1"+".txt");
					startActivity(i);
				}
				
			});
		    ed.addTextChangedListener(new TextWatcher(){

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					AnnouncementList.this.annou.getFilter().filter(arg0);
					annou.notifyDataSetChanged();
				
		
					
				}
		    	
		    });
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	public class ReadJson extends AsyncTask<String,Void,String>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			try{
				 pDialog = new ProgressDialog(AnnouncementList.this);
			pDialog.setMessage("Loading data. Please wait...");
			pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
				}
				catch(Exception e){
					//Toast.makeText(con,"Error connecting to server", Toast.LENGTH_LONG).show();
					
				}
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			String resu = result;
			pDialog.dismiss();
			
				
			try {
				products=new ArrayList<String>();
				JSONObject jso=new JSONObject(resu);
				JSONArray jsarr=jso.getJSONArray("announcements");
				for(int i=0; i<jsarr.length(); i++){
					JSONObject obj= jsarr.getJSONObject(i);
					products.add(
							obj.getString("name")
							);
					products2.add(obj.getString("id"));
				}
				annou=new ArrayAdapter(AnnouncementList.this,android.R.layout.simple_list_item_1,products);
					//Toast.makeText(VideoSermonsList.this,"Number of videos is "+ products.size(), Toast.LENGTH_LONG).show();
				lv1.setAdapter(annou);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Toast.makeText(con,"error connecting to the server", Toast.LENGTH_LONG).show();
			}

				
			
			
			
		}
				

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			StringBuilder sb=new StringBuilder();
			try {
				URL theurl=new URL(arg0[0]);
			   URLConnection conn=theurl.openConnection();	
			   BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			   String line;
			  while((line=reader.readLine())!=null) {
				  sb.append(line);
				  
			  }
			  reader.close();
			  return sb.toString();
			   
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				return new String(e.getMessage());
			}
		}
		
	}
}
