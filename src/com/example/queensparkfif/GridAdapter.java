package com.example.queensparkfif;

import java.io.File;
import java.util.ArrayList;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class GridAdapter extends BaseAdapter  {
ArrayList<File>f;
ArrayList<String> pr;
ImageView img;
Context co;
int ndex=3;
	public GridAdapter(ArrayList<File> f,Context con,ArrayList<String>pr) {
	//super();
	this.f = f;
	this.co=con;
	this.pr=pr;
}
	 class ViewHolder {   
		 ImageView myimage; 
	 }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View convert, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
			ViewHolder holder=null;
			if(convert==null){
				holder=new ViewHolder();
			convert=View.inflate(co,R.layout.griditem, null);
			holder.myimage=(ImageView)convert.findViewById(R.id.myi);
			//img.setImageURI(Uri.parse(f.get(arg0).toString()));
			

		
				
			
			Picasso.with(co).load("http://storage.googleapis.com/my-project-1529147168833.appspot.com%2Fimages%2F"+pr.get(arg0)).resize(200, 150)
		    .into(holder.myimage);
			//img.setImageResource(R.drawable.ic_launcher);
			
			
			convert.setTag(holder);
			
			}
		return convert;
	}

}
