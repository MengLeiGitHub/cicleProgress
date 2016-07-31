package com.ui;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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

public class ShitDropDownActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		shitdrop() ;
	
	}
	private void shitdrop() {
		setContentView(R.layout.dropdownshit);

		this.findViewById(R.id.add).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						new Handler().post(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								DropDownShitLayout add = (DropDownShitLayout) findViewById(R.id.layout);
								add.addShit();
							}
						});

					}
				});
		final Handler handler = new Handler();
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						add();
					}
				});

			}
		}, 1000, 200);
	}

	private void add() {
		// TODO Auto-generated method stub
		DropDownShitLayout add = (DropDownShitLayout) findViewById(R.id.layout);
		add.addShit();
	}
}
