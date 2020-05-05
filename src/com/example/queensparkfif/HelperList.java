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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
// Will use it on internet connection

//"http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fpraise%2F"+products.get(position)
public class HelperList extends Activity {
	 String url="";
	 ProgressDialog pDialog;
	 ListView lv1;
	  ArrayAdapter<String>audio;
	  ArrayList<String>fromhelp=new ArrayList<String>();
	  ArrayList<String>products;
	  ArrayList products2;
	  String tag="";
	  TextView header;
	  Intent holder;
	  EditText ed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listwrapper);
		header=(TextView)findViewById(R.id.Header);
		lv1=(ListView)findViewById(R.id.listView1);
		ed=(EditText)findViewById(R.id.mysearch);
		products=new ArrayList<String>();
		holder=getIntent();
		fromhelp=holder.getStringArrayListExtra("help");
		header.setText(fromhelp.get(0));
		tag=fromhelp.get(1);
		url=fromhelp.get(2);
		new ReadJson().execute(url);
		audio=new ArrayAdapter(HelperList.this,android.R.layout.simple_list_item_1,products);
		//Toast.makeText(VideoSermonsList.this,"Number of videos is "+ products.size(), Toast.LENGTH_LONG).show();
	lv1.setAdapter(audio);
		lv1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
					
					Intent i=new Intent(HelperList.this,Video.class);
					i.putExtra("str","http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fvideopraise%2F"+products.get(position));
					startActivity(i);
				
				
			}
			
		});
		
		//lv1.setAdapter(theann);
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
					HelperList.this.audio.getFilter().filter(arg0);
					audio.notifyDataSetChanged();
				
		
					
				}
		    	
		    });
	}
	public class ReadJson extends AsyncTask<String,Void,String>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			try{
				 pDialog = new ProgressDialog(HelperList.this);
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
				
				products2=new ArrayList();
				JSONObject jso=new JSONObject(resu);
				JSONArray jsarr=jso.getJSONArray(tag);
				for(int i=0; i<jsarr.length(); i++){
					JSONObject obj= jsarr.getJSONObject(i);
					products.add(
							obj.getString("name")
						
							
							);
					products2.add(obj.get("id"));
				}
				audio=new ArrayAdapter(HelperList.this,android.R.layout.simple_list_item_1,products);
				//Toast.makeText(VideoSermonsList.this,"Number of videos is "+ products.size(), Toast.LENGTH_LONG).show();
			lv1.setAdapter(audio);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}

