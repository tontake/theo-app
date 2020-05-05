package com.example.queensparkfif;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.widget.ArrayAdapter;
import android.widget.Toast;



public class BackgroundListener extends IntentService {
ArrayList<String>products;
ArrayList<String>products2;
PendingIntent pendingIntent;
String result;
static String Result;
public static String  appid="0";
static int count=0;
static int annsize=0;
static int biblesize=0;
static int gallersize=0;
static int testsize=0;
static int praisesize=0;
static int videossize=0;
static int wordsize=0;
String []names={"announcements","biblestudy","gallery","testimonies","praise","videos","word"};
static int []namesize={annsize,biblesize,gallersize,testsize,praisesize,videossize,wordsize};
	

	@Override
public IBinder onBind(Intent intent) {
	// TODO Auto-generated method stub
	return null;
}


	public BackgroundListener() {
		super("Background");
		// TODO Auto-generated constructor stub
		
		
	}

	@Override
	protected void onHandleIntent(Intent inte) {
		// TODO Auto-generated method stub
		  
	    try{ 
			
		
		String link="http://10.0.2.2:8081/xampp/myfolder/wop/active.php"; 
		String data = URLEncoder.encode("appid", "UTF-8") + "=" + URLEncoder.encode(appid, "UTF-8");
		
		URL url = new URL(link); 
		URLConnection conn = url.openConnection(); 
		conn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter (conn.getOutputStream()); 
		wr.write( data ); 
		wr.flush(); 
		BufferedReader reader = new BufferedReader (new InputStreamReader(conn.getInputStream())); 
		StringBuilder sb = new StringBuilder(); 
		String line = null; 
		// Read Server Response
		while((line = reader.readLine()) != null) { 
			sb.append(line);
			break; 
			} 
		Result= sb.toString();
		appid=Result;

		}
		
		catch(Exception e){
			Result= new String("Exception: " + e.getMessage());
			 
	}
	    while(true){
        int c;
		for (c=0; c<names.length;c++){
		Intent tointent=new Intent(getApplicationContext(),Home.class);
		  pendingIntent = PendingIntent.getActivity(this,0,tointent,0);
		 
		 
		String url="http://10.0.2.2:8081/xampp/myfolder/wop/"+names[c]+".php";
		StringBuilder sb=new StringBuilder();
		try {
			Thread.sleep(5000);
			URL theurl=new URL(url);
		   URLConnection conn=theurl.openConnection();	
		   BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		   String line;
		  while((line=reader.readLine())!=null) {
			  sb.append(line);
			  
		  }
		  reader.close();
		 result= sb.toString();
		 
			
		   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//Toast.makeText(getApplicationContext(), "error in readind data", Toast.LENGTH_LONG).show();
			
			e.printStackTrace();
			result=new String(e.getMessage());
		}
		if(result!=null){
		try {
			products=new ArrayList<String>();
			products2=new ArrayList<String>();
			JSONObject jso=new JSONObject(result);
			JSONArray jsarr=jso.getJSONArray(names[c]);
			for(int i=0; i<jsarr.length(); i++){
				JSONObject obj= jsarr.getJSONObject(i);
				products.add(
						obj.getString("name")
					);
				
				products2.add(obj.getString("id"));
			}
			count=products2.size()-namesize[c];
			//count=products2.size();
			String ans=products.get(0);
			if(count>0){
			NotificationCompat.Builder mBuilder =
			          new NotificationCompat.Builder(this)
			                  .setSmallIcon(R.drawable.ic_launcher)
			                  .setContentTitle("WINGS OF PRAYER")
			                  .setContentText( "You have "+ count + " new "+ names[c] + " posted ")
			                  .setAutoCancel(true)
			                  .setContentIntent(pendingIntent );

			               Notification noti=mBuilder.build();
			                   noti.flags|=Notification.FLAG_AUTO_CANCEL;
			                   noti.defaults|=Notification.DEFAULT_SOUND;


				
			NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			notificationManager.notify(0, noti);
			}
            namesize[c]=products2.size();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getApplicationContext(),"error in json or retreiving data", Toast.LENGTH_LONG).show();
		}
		}
		}
	    }
		
	}
	private StringBuilder inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();

        InputStreamReader isr = new InputStreamReader(is);

        BufferedReader rd = new BufferedReader(isr);

        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
	
	

}
