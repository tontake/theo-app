package com.example.queensparkfif;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TheStartService extends BroadcastReceiver {

	@Override
	public void onReceive(Context con, Intent inte) {
		// TODO Auto-generated method stub
      if(Intent.ACTION_BOOT_COMPLETED.equals(inte.getAction())){
    	  Intent i=new Intent(con,BackgroundListener.class);
    	  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	  con.startService(i);
    	  
      }
	}

}
