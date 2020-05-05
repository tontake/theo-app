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

public class Tithers extends Activity {
  ListView lv1;
  ArrayAdapter <String>testim;
  EditText ed;
  ArrayList<String>products;
  ArrayList products2;
  String url="http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fjson-api%2Ftestimonies.json";
  ProgressDialog pDialog;
 // String[]tes={"Elder Matara-Powerful Testimony","Mdlazi Breakthrough","Zvabva-God intervened","Sister Vero Prayer answered"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listwrapper);
		lv1=(ListView)findViewById(R.id.listView1);
		ed=(EditText)findViewById(R.id.mysearch);
		//testim=new ArrayAdapter(this,android.R.layout.simple_list_item_1,tes);
		//lv1.setAdapter(testim);
		new ReadJson().execute(url);
		 lv1.setTextFilterEnabled(true);
		 lv1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Tithers.this,TestimoniesView.class);
				if(position==0){
				i.putExtra("str", "id"+products2.get(0)+".txt");
				startActivity(i);
			}
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
					Tithers.this.testim.getFilter().filter(arg0);
					testim.notifyDataSetChanged();
				
		
					
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
				 pDialog = new ProgressDialog(Tithers.this);
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
				//products2 =new ArrayList();
				products=new ArrayList<String>();
				JSONObject jso=new JSONObject(resu);
				JSONArray jsarr=jso.getJSONArray("testimonies");
				for(int i=0; i<jsarr.length(); i++){
					JSONObject obj= jsarr.getJSONObject(i);
					
				}
				products.add(
						"St Kitts");
				products.add(
						"Mt Horeb");
				products.add(
						"Mt Zion");
				products.add(
						"Antioch");
				products.add(
						"Shakinah");
				
				testim=new ArrayAdapter(Tithers.this,android.R.layout.simple_list_item_1,products);
					//Toast.makeText(VideoSermonsList.this,"Number of videos is "+ products.size(), Toast.LENGTH_LONG).show();
				lv1.setAdapter(testim);
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

