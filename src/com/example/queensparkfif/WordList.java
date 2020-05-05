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

public class WordList extends Activity{
ListView lv1;
//String []wordlist={"Arch Bishop Ezekiel Guti update","Pastor Trust Patana Daily update","Various Ministers"};
ArrayAdapter<String> word;
EditText ed;
ProgressDialog pDialog;
TextView txt;
ArrayList<String>products;
ArrayList products2;
String url="http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fjson-api%2Fword.json";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listwrapper);
		//word=new ArrayAdapter(this,android.R.layout.simple_selectable_list_item,wordlist);
		lv1=(ListView)findViewById(R.id.listView1);
		txt =(TextView)findViewById(R.id.Header);
		txt.setText("Word");
		new ReadJson().execute(url);
		lv1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
					Intent i=new Intent(WordList.this,Wordview.class);
					i.putExtra("str", "id"+products2.get(arg2)+".txt");
					startActivity(i);
					
				
				
			}
			
			
			
		});
		ed=(EditText)findViewById(R.id.mysearch);
		//lv1.setAdapter(word);
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
					WordList.this.word.getFilter().filter(arg0);
					word.notifyDataSetChanged();
				
		
					
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
				 pDialog = new ProgressDialog(WordList.this);
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
				products2=new ArrayList();
				JSONObject jso=new JSONObject(resu);
				JSONArray jsarr=jso.getJSONArray("word");
				for(int i=0; i<jsarr.length(); i++){
					JSONObject obj= jsarr.getJSONObject(i);
					products.add(
							obj.getString("name")
						
							
							);
					products2.add(obj.get("id"));
				}
				word=new ArrayAdapter(WordList.this,android.R.layout.simple_list_item_1,products);
					//Toast.makeText(VideoSermonsList.this,"Number of videos is "+ products.size(), Toast.LENGTH_LONG).show();
				lv1.setAdapter(word);
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
