package com.cicleprogrossbar;

import com.example.cicleprogrossbar.R;
import com.jacky.permanent.SerActivity;
import com.ui.AutoViewpagerActivity;
import com.ui.LoveFlyActivity;
import com.ui.OtherActivity;
import com.ui.RefrshListActivity;
import com.ui.RotateActivity;
import com.ui.ShitDropDownActivity;
import com.ui.WaveActivity;
import com.ui.cameraActivity;
import com.ui.pxbitmapActivity;
import com.ui.throwActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 
	 setContentView(R.layout.main);
		 
		 
		
	}

	
	public void add(View v) {
		Intent intent=new Intent();
			switch(v.getId()){
			case R.id.love:
				intent.setClass(this, LoveFlyActivity.class);
				break;
			case R.id.other:
				intent.setClass(this, OtherActivity.class);
				break;
			case R.id.dropdown:
				intent.setClass(this, ShitDropDownActivity.class);
				break;
			case R.id.xiangshang:
				intent.setClass(this, throwActivity.class);
				break;
			case R.id.xuanzhuan:
				intent.setClass(this, RotateActivity.class);
				break;
			case R.id.houtaifuwu:
				intent.setClass(this, SerActivity.class);
				break;
			case R.id.refsh:
				intent.setClass(this, RefrshListActivity.class);
				break;	
			case R.id.danmu:
				intent.setClass(this, com.barrage.MainActivity.class);
				break;	
			case R.id.pxbitmap:
				intent.setClass(this, pxbitmapActivity.class);
				break;	
			case R.id.wave:
				intent.setClass(this, WaveActivity.class);
				break;	
			case R.id.camera:
				intent.setClass(this, cameraActivity.class);
				break;
			case R.id.autoviewpager:
				intent.setClass(this, AutoViewpagerActivity.class);
				break;
 			}
			startActivity(intent);
	}

}
