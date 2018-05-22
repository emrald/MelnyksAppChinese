package com.example.melnykschinese;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdaptor extends BaseAdapter {

	LayoutInflater inflater;
	ArrayList<Model>aritemtitle;
	public CustomAdaptor(Context context,ArrayList<Model> arlistitemtitle) {
		// TODO Auto-generated constructor stub
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
		aritemtitle=arlistitemtitle;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return aritemtitle.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return aritemtitle.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView=inflater.inflate(R.layout.episodelistrow,null,false);
		
		TextView title=(TextView)convertView.findViewById(R.id.episodelistrow_tv_itemtitle);
		title.setText(aritemtitle.get(position).getitemtitle());
		
		return convertView;
	}

}
