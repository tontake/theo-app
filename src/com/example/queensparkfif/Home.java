package com.example.queensparkfif;

import java.util.Calendar;

import android.media.RingtoneManager;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;
import me.leolin.shortcutbadger.*;


public class Home extends Activity {
Button btest;
Intent intent;
Intent servi;
PendingIntent pendingIntent;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		btest=(Button)findViewById(R.id.button1);
		
		intent = new Intent(Home.this, Church.class);
		servi=new Intent(Home.this,BackgroundListener.class);
		startService(servi);
		// pendingIntent = PendingIntent.getActivity(this,0,intent,0);
		final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
	    animation.setDuration(1200); // duration - half a second
	    animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
	    animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
	    animation.setRepeatMode(Animation.ZORDER_BOTTOM); // Reverse animation at the end so the button will fade back in
	    
	    btest.startAnimation(animation);
	
		btest.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			/*	 NotificationCompat.Builder mBuilder =
				                new NotificationCompat.Builder(Home.this)
				                        .setSmallIcon(R.drawable.ic_launcher)
				                        .setContentTitle("WINGS OF PRAYER")
				                        .setContentText("Tonderai posted a video ")
				                        .setContentIntent(pendingIntent );
				                        
				      
			
						

				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManager.notify(0, mBuilder.build());
				*/
				//arg0.clearAnimation();
				Intent callchurch=new Intent(Home.this,Church.class);
				
	
				//boolean success=ShortcutBadger.applyCount(Home.this, 2);
				//Toast.makeText(getApplicationContext(), "it has "+ success, Toast.LENGTH_LONG).show();
				
				startActivity(callchurch);

			}
			
		});
	}
/*end of the oncreate method*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

}
