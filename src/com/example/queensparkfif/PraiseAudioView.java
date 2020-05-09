package com.example.queensparkfif;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PraiseAudioView extends Activity {
	ImageButton btn;
	TextView txt;
	
	
	MediaPlayer p;
	ImageButton btn2;
	SeekBar seek;
	Intent froma;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_the_media_screen);
		txt=(TextView)findViewById(R.id.text);
	    btn=(ImageButton)findViewById(R.id.main2);
	    btn2=(ImageButton)findViewById(R.id.main3);
	    seek=(SeekBar)findViewById(R.id.seekBar1);
	    froma=getIntent();
	    p=new MediaPlayer();
	    
	    
	    	
	    	
	    	
	    	
	    seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
	           if(p.isPlaying()){
	        	p.seekTo(i);
	           }
	           
	        }

	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {

	        }

	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {

	        }
	    });

	    
	    
	    btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				btn.setBackgroundColor(Color.GREEN);
				btn2.setBackgroundColor(Color.WHITE);
				try{
					Toast.makeText(getApplicationContext(), "Ready ...", Toast.LENGTH_SHORT).show();
					p.setDataSource("http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fpraise%2F"+froma.getStringExtra("str"));
					
					p.prepare();
					p.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						
						@Override
						public void onPrepared(MediaPlayer arg0) {
							// TODO Auto-generated method stub
						//	Toast.makeText(getApplicationContext(), "Playing...", Toast.LENGTH_LONG).show();
							p.start();
							
							
							txt.setText("PLAYING ....");
							if(p.isPlaying()){
						    	seek.setMax(p.getDuration());
						        seek.setProgress(p.getCurrentPosition());
						       new Timer().scheduleAtFixedRate(new TimerTask() {
						           @Override
						           public void run()
						           {
						                seek.setProgress(p.getCurrentPosition());
						           }
						       },0,1000);
							}
							
							
						}
					});
					
				}
				catch(Exception e){
				e.printStackTrace();	
				}
				
			    
				
			}


	    	
	    });
		//anim= AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
		btn2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				btn2.setBackgroundColor(Color.RED);
				btn.setBackgroundColor(Color.WHITE);
				if(p.isPlaying()){
					
					p.stop();
					p.reset();
					txt.setText("Stopped ...");
				}
				
			}
			
		});
		seek.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if(p.isPlaying()){
				SeekBar b1=(SeekBar)v;
				int pos=(p.getDuration()/100)*b1.getProgress();
				p.seekTo(pos);
				}
				return false;
			}
		});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	


}
