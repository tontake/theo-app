package com.example.queensparkfif;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoSermons extends Activity{
 VideoView vi;
 Intent vid;
 Uri u;
 MediaController m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videosermon);
		vi=(VideoView)findViewById(R.id.video);
		vid=getIntent();
	   String videoname=vid.getStringExtra("result");
		//path
	
	//String videopath="http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fvideos%2Fezekiel.mp4";
		if(videoname.equals("Ezekiel 2020.mp4")){
		 u=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ezekiel_2020);	
		}
		else{
			String videopath="http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fvideos%2F"+videoname;
			 u=Uri.parse(videopath);
		}
		
		
		vi.setVideoURI(u);
		
		MediaController mc=new MediaController(this);
		mc.setAnchorView(vi);
		
		vi.setMediaController(mc);
		vi.requestFocus();
		vi.setOnPreparedListener(new OnPreparedListener(){

			@Override
			public void onPrepared(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				vi.seekTo(5000);
				vi.start();
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
