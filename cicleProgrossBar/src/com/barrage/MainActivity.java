package com.barrage;

 

 
import com.example.cicleprogrossbar.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 

public class MainActivity extends Activity {
	 ToBarrageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barrage);
      
         T2BarrageView b=(T2BarrageView) this.findViewById(R.id.barrageview);
         adapter=new ToBarrageAdapter(this);
         adapter.setI(10); 
         b.setAdapter(adapter);
         
        
        
      }
   
     
    public void show(View v){
    	 adapter.setI(22);
 		 adapter.notifyDataSetChanged();
    }
    
   
   public void reset(View v){
 		 adapter.notifyDataSetInvalidated();
   }
   boolean  isSatrt=true;
   public void zantingbofang(View v){
	   if(isSatrt){
		   ((Button)v).setText("开始");
			 adapter.AnimPause();
			 isSatrt=false;
 	   }else{
		   ((Button)v).setText("结束");
  		    adapter.AnimStart();
			 isSatrt=true;
 	   }
   }
   
}
