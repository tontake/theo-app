package com.example.queensparkfif;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class Ezekieltv extends Activity{
 VideoView vi;
 MediaController m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videosermon);
		vi=(VideoView)findViewById(R.id.video);
		String videopath="https://livestream.com/accounts/19491040/events/8258678/player?width=560&height=315";
		Uri u=Uri.parse(videopath);
		vi.setVideoURI(u);
		MediaController mc=new MediaController(this);
		mc.setAnchorView(vi);
		vi.setMediaController(mc);
		vi.requestFocus();
		vi.setOnPreparedListener(new OnPreparedListener(){

			@Override
			public void onPrepared(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				
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

