package com.singleConstumdialog;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cicleprogrossbar.R;

public class SingleAdapter extends  BaseAdapter{
	private ArrayList<SingleItemName> list;
	Context context;
	
	public SingleAdapter(Context context,ArrayList<SingleItemName> list){
		this.list=list;
		this.context=context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(list==null) 	return 0;
		else  return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		SingleItemName item=(SingleItemName) getItem(arg0);
		arg1=	LayoutInflater.from(context).inflate(R.layout.single_dialog_item, null);
		TextView t=(TextView) arg1.findViewById(R.id.text);
		t.setText(item.getItemname());
		return arg1;
	}
	
}