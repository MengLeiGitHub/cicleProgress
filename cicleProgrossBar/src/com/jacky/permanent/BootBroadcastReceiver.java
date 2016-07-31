package com.jacky.permanent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) 
	{
		
		   Toast.makeText(context, "��", Toast.LENGTH_SHORT).show();
		 	Intent startServiceIntent = new Intent(context, MyService.class);
			startServiceIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startService(startServiceIntent);
 	}
}
