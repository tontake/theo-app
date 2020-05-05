package com.example.queensparkfif;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class Church extends Activity{
 Button bnext;
	public Church() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.church);
		 final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
		    animation.setDuration(1200); // duration - half a second
		    animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
		    animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
		    animation.setRepeatMode(Animation.ZORDER_BOTTOM); // Reverse animation at the end so the button will fade back in
		      bnext = (Button) findViewById(R.id.button1);
		    bnext.startAnimation(animation);
	}
  public void LoadPage(View v){
	  
	Intent page =new Intent(Church.this,Page.class);
	startActivity(page);
	  
  }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
