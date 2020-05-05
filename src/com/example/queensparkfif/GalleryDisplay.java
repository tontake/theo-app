package com.example.queensparkfif;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GalleryDisplay extends Activity {
ImageView im;
Button download;
String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagegallery);
		Intent i=getIntent();
		download=(Button)findViewById(R.id.button1);
		 url=i.getStringExtra("result");
		download.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Download();
			}
			
			
		});
		im=(ImageView)findViewById(R.id.image);
		
		//Picasso.with(this).load(i.getStringExtra("str"))
	   // .into(im);
		
		Picasso.with(this).load("http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fimages%2F"+url).resize(300, 250)
	    .into(im);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
	public void Download(){
		File rootdir=new File(Environment.getExternalStorageDirectory(),"TheoApp");
		if(!rootdir.exists()){
			rootdir.mkdirs();
			
		}
		File file=new File(rootdir,url);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//placeholder for url downloading usng url.openConnection()
		
		
	//	InputStream is = this.getResources().openRawResource(R.drawable.church3);
		try {
			URL u = new URL("http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fimages%2F"+url);
	        InputStream is = u.openStream();
			
			FileOutputStream output=new FileOutputStream(file);
			
			byte[]buffer=new byte[1024];
			int byteCount=0;
			try {
				while((byteCount=is.read(buffer))>0){
					
					try {
						output.write(buffer,0, byteCount);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Intent inte=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		inte.setData(Uri.fromFile(file));
		getApplicationContext().sendBroadcast(inte);
		Toast.makeText(getApplicationContext(), "File saved successfully",Toast.LENGTH_LONG).show();
	}

}
