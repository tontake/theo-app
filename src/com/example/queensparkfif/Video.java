package com.example.queensparkfif;






import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.RadioGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Video extends Activity implements Runnable{
VideoView vow;
ProgressDialog dg;
Intent data;
Thread t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		vow=(VideoView)findViewById(R.id.video);
		data=getIntent();
	
		
	  // String videopath="android.resource//com.example.videostream/"+R.raw.chess;
	  // Uri u= Uri.parse(videopath);
		//vow.setVideoURI(u);
		MediaController mc=new MediaController(this);
		
		t=new Thread(this);
		
		 String videopath=data.getStringExtra("str");
		  Uri u=Uri.parse(videopath);
			vow.setVideoURI(u);
		//     vow.requestLayout();
		     vow.requestFocus();
		   //  vow.setBackgroundColor(Color.TRANSPARENT);
		     vow.setMediaController(mc);
		     vow.seekTo(10000);
		     dg=new ProgressDialog(Video.this);
		     dg.setTitle("STREAMING IN ANDROID");
		     dg.setCancelable(true);
		     dg.setIndeterminate(false);
		     dg.show();
		     
		     vow.setOnPreparedListener(new OnPreparedListener(){

				@Override
				public void onPrepared(MediaPlayer arg0) {
					// TODO Auto-generated method stub
					dg.dismiss();
					vow.start();
					//t.start();
					
				}
		    	 
		     });
		    // vow.start();
		     //t.start();
		    	
		     
		    
			   //  vow2.start();
			   
			   //  vow2.pause();    
		     
		     
		     
		     
		     
		     
		     
		
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public void run() {
		 //String videopath="android.resource//com.example.videostream/"+R.raw.chess;
		 // Uri u=Uri.parse(videopath);
		//	vow.setVideoURI(u);
		
			
		
		try {
			t.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		vow.pause();
		
	}

}
