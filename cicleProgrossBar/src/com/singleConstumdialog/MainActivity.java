package com.singleConstumdialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
 
import com.example.cicleprogrossbar.R;
 

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);   
		draglist();
		
	
	}
	private void draglist() {
		// TODO Auto-generated method stub
		
		setContentView(R.layout.activity_list);
		DragView  list=(DragView ) this.findViewById(R.id.list);
		ArrayList<SingleItemName> lists=new ArrayList<SingleItemName>();
		 lists.add(new Person(1,"小明"));
		 lists.add(new Person(1,"小王"));
		 lists.add(new Person(1,"小李"));
		 lists.add(new Person(1,"小张"));
		 lists.add(new Person(1,"小刘"));
		SingleAdapter s=new SingleAdapter(this, lists);
		
		list.setAdapter(s);
		
		
	}
	private void shitdrop() {
		setContentView(R.layout.activity_main);
		//	setContentView(new Rubble(this,"谢谢惠顾",new Rect(100, 200,     600,450),2,1f,14));  
		this.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				show(arg0);
			}
		});
		
 		
	}
	
	public void  show(View v){
		
		SingleDialog single=new SingleDialog(MainActivity.this);
		 single.show();
		ArrayList<SingleItemName> list=new ArrayList<SingleItemName>();
		 list.add(new Person(1,"小明"));
		 list.add(new Person(1,"小王"));
		 list.add(new Person(1,"小李"));
		 list.add(new Person(1,"小张"));
		 list.add(new Person(1,"小刘"));
		 single.setList(list);
		
	}

	 
}
