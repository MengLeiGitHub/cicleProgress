package com.ui;

import java.util.Random;

import com.cicleprogrossbar.MainActivity;
import com.example.cicleprogrossbar.R;
import com.throwView.ThrowViewAddInterface;
import com.throwView.ThrowViewUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class RefrshListActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_refresh);
		 ListView list=(ListView) this.findViewById(R.id.list);
		 String strs[]=new String[20];
		 for(int i=0;i<strs.length;i++){
			 strs[i]=i+"test";
		 }
		  list.setAdapter(new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1, strs));
 	}

	
	

 

}
