package com.example.queensparkfif;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class Page extends Activity {
 ListView lv;
 ArrayList<PageItem> qpr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page);
		lv=(ListView)findViewById(R.id.listView1);
		 qpr = new ArrayList<PageItem>();
		qpr.add(new PageItem("Biography","ic_launcher"));
		qpr.add(new PageItem("Video Sermons","videos"));
		qpr.add(new PageItem("Word","word"));
		qpr.add(new PageItem("Gallery","gallery"));
		qpr.add(new PageItem("Ezekiel TV Channel","ezekieltv"));
		
		qpr.add(new PageItem("Praise","music"));
		qpr.add(new PageItem("Bible Study","bible"));
		qpr.add(new PageItem("Purchase","bible"));
		qpr.add(new PageItem("Testimonies","testi"));
		qpr.add(new PageItem("Announcements","ann"));
		PageAdapter p=new PageAdapter(getApplicationContext(),1,qpr);
		lv.setAdapter(p);
		lv.setOnItemClickListener(sel);
		
	}
	private AdapterView.OnItemClickListener sel=new 
			AdapterView.OnItemClickListener() 
	{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			if(qpr.get(arg2).getName()=="Biography"){
				Intent miss=new Intent(Page.this,Mission.class);
				startActivity(miss);
			}
			else if(qpr.get(arg2).getName()=="Video Sermons"){
				Intent video=new Intent(Page.this,VideoSermonsList.class);
				startActivity(video);
			}
			else if(qpr.get(arg2).getName()=="Purchase"){
				Intent video=new Intent(Page.this,PurchaseList.class);
				startActivity(video);
			}
			else if(qpr.get(arg2).getName()=="Word"){
				Intent word=new Intent(Page.this,WordList.class);
				startActivity(word);
			}
			else if(qpr.get(arg2).getName()=="Ezekiel TV Channel"){
				//Intent ez=new Intent(Page.this,Ezekieltv.class);
				String url="https://livestream.com/accounts/19491040/events/8258678/player?width=560&height=315";
			Intent ez = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		     //   startActivity(intent);
				startActivity(ez);
			}
			else if(qpr.get(arg2).getName()=="Praise"){
				Intent praise=new Intent(Page.this,PraiseWorshipList.class);
				startActivity(praise);
			}
			else if(qpr.get(arg2).getName()=="Gallery"){
				Intent ga=new Intent(Page.this,Gallery.class);
				startActivity(ga);
			}
			else if(qpr.get(arg2).getName()=="Bible Study"){
				Intent bible=new Intent(Page.this,BibleStudyList.class);
				startActivity(bible);
			}
			else if(qpr.get(arg2).getName()=="Testimonies"){
				Intent test=new Intent(Page.this,TestimoniesList.class);
				startActivity(test);
			}
			else if(qpr.get(arg2).getName()=="Announcements"){
				Intent ann=new Intent(Page.this,AnnouncementList.class);
				startActivity(ann);
			}

		}
		
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
