package com.example.queensparkfif;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class PraiseAudioList extends Activity {
	
    ListView lv1;
    ArrayAdapter<String>arr;
    ArrayList<String>products;
    TextView header;
    EditText ed;
    ProgressDialog pDialog;
    Intent getheader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listwrapper);
		products=new ArrayList<String>();
		
		
		lv1=(ListView)findViewById(R.id.listView1);
		header=(TextView)findViewById(R.id.Header);
	
		header.setText("Audio");
		ed=(EditText)findViewById(R.id.mysearch);
		// To be uncommented when online
		
		
       new ReadJson().execute("http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fjson-api%2Fpraise.json");
		//arr=new ArrayAdapter(this,android.R.layout.simple_list_item_1,videos);
       arr=new ArrayAdapter(PraiseAudioList.this,android.R.layout.simple_list_item_1,products);
		//Toast.makeText(VideoSermonsList.this,"Number of videos is "+ products.size(), Toast.LENGTH_LONG).show();
	lv1.setAdapter(arr);
		
		//arr=new ArrayAdapter(this,android.R.layout.simple_list_item_1,products);
		
		//lv1.setAdapter(arr);
		 lv1.setTextFilterEnabled(true);
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
					PraiseAudioList.this.arr.getFilter().filter(arg0);
					arr.notifyDataSetChanged();
				
		
					
				}
		    	
		    });
			
		
		lv1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				  
				 String clicked=products.get(position);
					Intent audio=new Intent(PraiseAudioList.this,PraiseAudioView.class);
					audio.putExtra("str", clicked);
					
					startActivity(audio);
				
				
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
				 pDialog = new ProgressDialog(PraiseAudioList.this);
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
				
				JSONObject jso=new JSONObject(resu);
				JSONArray jsarr=jso.getJSONArray("praise");
				for(int i=0; i<jsarr.length(); i++){
					JSONObject obj= jsarr.getJSONObject(i);
					products.add(
							obj.getString("name")
						
							
							);
				}
				
				// comment or uncomment check first
				//arr=new ArrayAdapter(VideoSermonsList.this,android.R.layout.simple_list_item_1,products);
					//Toast.makeText(VideoSermonsList.this,"Number of videos is "+ products.size(), Toast.LENGTH_LONG).show();
				//lv1.setAdapter(arr);
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
