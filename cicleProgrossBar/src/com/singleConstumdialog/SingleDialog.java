package com.singleConstumdialog;

import java.util.ArrayList;

import com.example.cicleprogrossbar.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SingleDialog  extends Dialog{
	
	Context context;
	private ArrayList<SingleItemName> list;
	SingleAdapter  singleAdapter;
	SingleDialogItemOnSelectInterface interfaceOnitem;
	public void setList(ArrayList<SingleItemName> list) {
		this.list = list;
		singleAdapter.notifyDataSetChanged();
 	}
  
	public void setInterfaceOnitem(SingleDialogItemOnSelectInterface interfaceOnitem) {
		this.interfaceOnitem = interfaceOnitem;
	}

	protected SingleDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
 		this.context=context;
		init();	
	}

	private void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.single_dialog);
 		ListView listview=(ListView) this.findViewById(R.id.listview);
 		singleAdapter=new SingleAdapter();
 		listview.setAdapter(singleAdapter);
 		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				interfaceOnitem.Select(list.get(arg2));
			}
		});
	}
	
	
	

	private  class SingleAdapter extends  BaseAdapter{

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
	 
	 
	
	

}
