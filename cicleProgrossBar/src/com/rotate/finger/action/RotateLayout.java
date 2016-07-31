package com.rotate.finger.action;

import com.example.cicleprogrossbar.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RotateLayout extends RelativeLayout{

	int rightimg[]={},leftimg[];
	boolean  isFrist=true;
	LayoutParams cloaselayout,rightlayout;
	
	
	
 	public RotateLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
   		ImageView  cloase=new ImageView(context);
		cloase.setImageResource(R.drawable.close);
	    cloaselayout=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		cloaselayout.addRule(RelativeLayout.ALIGN_PARENT_TOP );
 		cloase.setLayoutParams(cloaselayout);
		addView(cloase);
		leftimg =new int[]{cloase.getWidth(),cloase.getHeight()};

		ImageView  center=new ImageView(context);
		center.setImageResource(R.drawable.ic_launcher);
		LayoutParams centerlayout=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		centerlayout.addRule(RelativeLayout.CENTER_IN_PARENT);
		center.setLayoutParams(centerlayout);
 		addView(center);
 		
 		ImageView  rotateTouch=new ImageView(context);
		rotateTouch.setImageResource(R.drawable.love1);
	    rightlayout=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rightlayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		rightlayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rotateTouch.setLayoutParams(rightlayout);
 		addView(rotateTouch);
 		;
 		rightimg =new int[]{rotateTouch.getWidth(),rotateTouch.getHeight()};
 		
 		
 		
 		rotateTouch.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					downX=arg1.getRawX();
					downY=arg1.getRawY();
					break;
				case MotionEvent.ACTION_MOVE:
					ScaleOrRotate(arg1.getRawX(),arg1.getRawY(),arg0);
				 	Scale( getRotate(arg1));
					 
					break;
				case MotionEvent.ACTION_UP:
					break;
				}
				return true;
  			}

			
		});
 		
 		center.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					break;
				case MotionEvent.ACTION_MOVE:
					moveViewWithFinger(RotateLayout.this, arg1.getX(), arg1.getY(),arg0);
					break;
				case MotionEvent.ACTION_UP:
					break;
				}
				return true;
			}
		});
  	}
	
	@SuppressLint("NewApi")
	private void moveViewWithFinger(RotateLayout rotateLayout,
			float rawX, float rawY,View v) {
		// TODO Auto-generated method stub
		/**/
		setX(  getX()+rawX );
		setY(  rawY  + getY());
	}
	float f=0;
	@SuppressLint("NewApi")
	private  void ScaleOrRotate(float rawX, float rawY, View v){
		/*RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)getLayoutParams();
		params.leftMargin = (int) rawX + v.getWidth();
		params.topMargin  = (int) rawY + v.getHeight();
 		setLayoutParams(params);*/
 		float scalx=(rawX-downX)/v.getWidth()+1;
 		float scaly=(rawY-downY)/v.getHeight()+1;
 		 
 		if(scalx>0.1)
 			setScaleX(scalx);
 		if(scaly>0.1)
 			setScaleY(scaly); 		
  	}
	
	
	@SuppressLint("NewApi")
	private void   Scale(float rotate){
		 setRotation(getRotation()-rotate);
 	}
	
	
	float  lastX,lastY,downX,downY;
	private float[] MoveEnventCheck(MotionEvent arg1){
		float x=arg1.getRawX();
		float y=arg1.getRawY();
		float  xy[]={x-lastX,y-lastY};
		lastX=x;
		lastY=y;
 		return xy;
	}
 
	  // 取旋转角度  
    private float rotation(MotionEvent event) {  
        double delta_x = event.getRawX()-downX;  
        double delta_y = event.getRawY()-downY;  
        double radians = Math.atan2(delta_y, delta_x);  
        return (float) Math.toDegrees(radians);  
    }  
  
    //计算旋转的角度
    private  float getRotate(MotionEvent event){
    	View center=getChildAt(1);
    	float rawx=event.getRawX(),rawy=event.getRawY();
    	int location[]=new int[2];
    	center.getLocationOnScreen(location);
    	 
    	double  absolute_a=Math.sqrt(location[0]*location[0]+location[1]*location[1]);
    	double  absolute_b=Math.sqrt(rawx*rawx+rawy*rawy);
    	double  absolute_ab=location[0]*rawx+rawy*location[1];
    	double  cos_ab=absolute_ab/(absolute_a*absolute_b);
    	Log.e("tag", Math.acos(cos_ab)+"  "+(Math.acos(cos_ab)*360));
      	return (float) (Math.acos(cos_ab)* 180 / Math.PI);
    }
		
}
