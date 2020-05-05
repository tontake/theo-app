package com.example.queensparkfif;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.queensparkfif.VideoSermonsList.ReadJson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class Gallery extends Activity {
GridView gv;
ArrayList<File>list;
ArrayList<String>imagestring;
ArrayList<String>products;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.galleryview);
		products=new ArrayList<String>();
		gv=(GridView)findViewById(R.id.gv);
		list=null;
		//list=imageReader(Environment.getExternalStorageDirectory());
		
		imagestring=new ArrayList<String>();
	
		new ReadJson().execute("http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fjson-api%2Fgallery.json");
		//gv.setAdapter(new GridAdapter(list,this,products));
		//imagestring.add("http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fimages%2Fchurch1.jpg");
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String img=products.get(arg2);
				
				// TODO Auto-generated method stub
				Intent app=new Intent(Gallery.this,GalleryDisplay.class);
				app.putExtra("result", img);
				startActivity(app);
				
			}
			
		
		});
	}
 //writtten temporary
	public class ReadJson extends AsyncTask<String,Void,String>{
		ProgressDialog pDialog;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			try{
				 pDialog = new ProgressDialog(Gallery.this);
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
				JSONArray jsarr=jso.getJSONArray("gallery");
				for(int i=0; i<jsarr.length(); i++){
					JSONObject obj= jsarr.getJSONObject(i);
					products.add(
							obj.getString("name")
						
							
							);
				}
			//	gv.setAdapter(new GridAdapter(list,this,products));
				
				// comment or uncomment check first
				//arr=new ArrayAdapter(VideoSermonsList.this,android.R.layout.simple_list_item_1,products);
					//Toast.makeText(Gallery.this,"Number of images is "+ products.size(), Toast.LENGTH_LONG).show();
				//lv1.setAdapter(arr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Toast.makeText(con,"error connecting to the server", Toast.LENGTH_LONG).show();
			}

				if(products.size()>0){
			gv.setAdapter(new GridAdapter(list,Gallery.this,products));
			
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
