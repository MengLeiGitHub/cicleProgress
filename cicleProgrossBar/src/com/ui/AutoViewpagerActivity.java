package com.ui;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.autoviewpager.ChildViewPager;
import com.autoviewpager.ChildViewPager.OnSingleTouchListener;
import com.autoviewpager.ChildViewPagerStateChange;
import com.cicleprogrossbar.MainActivity;
import com.dropdown.shit.DropDownShitLayout;
import com.example.cicleprogrossbar.R;
import com.throwView.ThrowViewAddInterface;
import com.throwView.ThrowViewUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class AutoViewpagerActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		shitdrop() ;
	
	}
	private void shitdrop() {
		setContentView(R.layout.activity_autoviewpager);
		ChildViewPagerStateChange  state=(ChildViewPagerStateChange) this.findViewById(R.id.viewpagerstate);
		ChildViewPager  viewpager= (ChildViewPager) this.findViewById(R.id.viewpager);
		viewpager.setOnSingleTouchListener(new OnSingleTouchListener() {
			
			@Override
			public void onSingleTouch(Object obj) {
				// TODO Auto-generated method stub
				String in=obj.toString();
				Toast.makeText(getBaseContext(), ""+ in, Toast.LENGTH_SHORT).show();
			}
		});
		viewpager.setAcitvity(this);
		viewpager.setListinfo();
		viewpager.setStateChange(state);
		viewpager.startScroll();
	}

	 
}
