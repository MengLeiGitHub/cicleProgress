package com.ui;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.cicleprogrossbar.CicleProgressBar;
import com.cicleprogrossbar.CicleProgressBarVerical;
import com.cicleprogrossbar.MainActivity;
import com.example.cicleprogrossbar.R;
import com.example.waveview.WaterWaveView;
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

public class WaveActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 //setContentView(R.layout.activity_main);
		 progross();
	}


	
 
	private void progross() {
 		  setContentView(R.layout.activity_wave);
 		 /* WaterWaveView wave=(WaterWaveView) this.findViewById(R.id.wave);
 		  wave.startWave();*/
  	}

}
