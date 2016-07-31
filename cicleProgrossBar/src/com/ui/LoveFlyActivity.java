package com.ui;

import java.util.Random;

import com.airbubble.LoveBubbleRealayout;
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

public class LoveFlyActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		love();
	
	}

	private void love() {
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.drawpath);

		this.findViewById(R.id.add).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						LoveBubbleRealayout add = (LoveBubbleRealayout) findViewById(R.id.layout);
						add.addLove();
					}
				});
	}
}
