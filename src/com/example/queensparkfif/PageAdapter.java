package com.example.queensparkfif;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PageAdapter extends ArrayAdapter<PageItem>{
	Context con;
	int choose;
	TextView name;
	ImageView image;
	int i=0;
	
	int []IMAGES={R.drawable.ic_launcher,R.drawable.videos,R.drawable.word,R.drawable.gallery,R.drawable.ic_launcher,R.drawable.music,R.drawable.bible,R.drawable.ic_launcher,R.drawable.testi,R.drawable.ann,};
	ArrayList<PageItem>qpr;
	

	public PageAdapter(Context context, int clicked,
      ArrayList<PageItem> objects) {
		super(context, clicked, objects);
		// TODO Auto-generated constructor stub
		this.choose=clicked;
		this.con=context;
		this.qpr=objects;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return qpr.size();
	}

	@Override
	public PageItem getItem(int position) {
		// TODO Auto-generated method stub
		return qpr.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return super.getItemId(position);
	}

	@Override
	public View getView(int position, View convert, ViewGroup parent) {
		// TODO Auto-generated method stub
	ViewHolder holder=null;
		if(convert==null){
			holder=new ViewHolder();
			convert=View.inflate(con, R.layout.row, null);
			holder.ima =(ImageView)convert.findViewById(R.id.myimg);
			holder.tex=(TextView)convert.findViewById(R.id.thename);
			convert.setTag(holder);

		}
		else{
			holder=(ViewHolder)convert.getTag();
		}
		holder.tex.setText(qpr.get(position).getName());
		
		holder.ima.setImageResource(IMAGES[position]);

		
		
		
		if(position%2==0){
			convert.setBackgroundColor(Color.WHITE);
		}
		else{
			
			convert.setBackgroundColor(Color.WHITE);
		}
		return convert;
	}
	class ViewHolder{
		private ImageView ima;
		private TextView tex;
		
		
	}

}
