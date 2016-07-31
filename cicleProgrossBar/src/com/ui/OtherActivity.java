package com.ui;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.cicleprogrossbar.CicleProgressBar;
import com.cicleprogrossbar.CicleProgressBarVerical;
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
import android.widget.Toast;

public class OtherActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 //setContentView(R.layout.activity_main);
		 progross();
	}


	
	int i;

	private void progross() {
		
		  setContentView(R.layout.activity_main);
		  
		  /*RingProgressBar r=(RingProgressBar) findViewById(R.id.ring);
		  r.startAnim(0.7f); r.setOnClickListener(new View.OnClickListener() {
		  
		  @Override public void onClick(View arg0) { // TODO Auto-generated
		  method stub ((RingProgressBar)arg0).startAnim(0.8f); } });
		  
		 
		  MLProgressBar mlpb=(MLProgressBar)
		  findViewById(R.id.mlpb); mlpb.startAnim(); 
		  */
		final CicleProgressBarVerical
		  vericl=(CicleProgressBarVerical) findViewById(R.id.vericl);
		  
		  new Timer().schedule(new TimerTask(){
			  
			 @Override
			public void run() {
				// TODO Auto-generated method stub
				  vericl.post(new Runnable(){
					  @Override
					public void run() {
						// TODO Auto-generated method stub
						  vericl.startAnim(i++);	
						  Toast.makeText(getApplicationContext(), "123", 1).show();
					}
					  
				  });
	
			}
			  
		  }, 1000,1000);
		  
		  
		  
		 /* CicleProgressBar cicle= (CicleProgressBar) findViewById(R.id.cicle);
		  cicle.startAnim(0.7f); */
		/*
		 * WaveProgressBar wave=(WaveProgressBar) findViewById(R.id.wave);
		 * wave.startAnim(60);
		 */
		/*
		 * WaveLoadingView mWaveLoadingView = new WaveLoadingView(this);
		 * setContentView(mWaveLoadingView);
		 * mWaveLoadingView.setWaveColor(0xff0099CC) .setTextColor(0xffFFFFFF)
		 * .setTextSize(40) .setTextColor(0xff000000) .setAmplitude(20)
		 * .setPalstance(0.5f) .setRefreshTime(4);
		 * mWaveLoadingView.setOnFinishedListener(new
		 * WaveLoadingView.OnFinishedListener() {
		 * 
		 * @Override public void onFinished() { System.out.println("Íê³É"); } });
		 * setContentView(mWaveLoadingView);
		 */
	}

}
