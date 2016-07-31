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
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class throwActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		throwview();
	
	}
	private void throwview() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_throw);
		{
			this.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
				
				@SuppressLint("NewApi")
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageView  t=new  ImageView(throwActivity.this);
					Random  random=new Random();
					int draw[]={R.drawable.love1,R.drawable.love2,R.drawable.love3,R.drawable.love4,
							R.drawable.shit1,R.drawable.shit2,R.drawable.shit3,R.drawable.shit4,R.drawable.shit5,};
					t.setImageResource(draw[random.nextInt(draw.length)]);
					final RelativeLayout r=(RelativeLayout) findViewById(R.id.base);
					Display d=getWindow().getDecorView().getDisplay();
   					r.addView(t);
  					new ThrowViewUtils(d.getWidth(), d.getHeight(), t, new ThrowViewAddInterface() {
						
						@Override
						public void addView(View v) {
							// TODO Auto-generated method stub
							r.removeView(v);
						}
					}).start();
				}
			});
 		}
		
		
		
		
	}
}
