package com.example.queensparkfif;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BibleView extends Activity{
WebView web;
String url;
Intent i;
String MAIN="https://theoapp.firebaseapp.com";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bibleview);
		i=getIntent();
		 url=i.getStringExtra("help");
		 web=(WebView)findViewById(R.id.web);
			web.setWebViewClient(new MyWebViewClient());
		 if(url.equals("index.html")){
		
		String path="file:///android_asset/index.html";
		web.getSettings().setJavaScriptEnabled(true);
		web.loadUrl(path);
		 }
		 else{
			 web=(WebView)findViewById(R.id.web);
				String path=MAIN;
				web.getSettings().setJavaScriptEnabled(true);
				web.loadUrl(path); 
		 }
		
		
	}
	private class MyWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	       
	        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
	        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
	        startActivity(intent);
	        return false;
	    }
	    @Override
	    public void onReceivedError(WebView webview, int i, String s, String s1)
        {
            webview.loadUrl("file:///android_asset/error.html");
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
