package com.arc.btn;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ArcBtn  extends View{
    ArrayList<BtnState> list=new ArrayList<BtnState>();
	
	public ArcBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Init();
	}
	int color[]={Color.RED,Color.BLACK,Color.BLUE,Color.CYAN,Color.GREEN};
	 
	private void  Init(){
		for(int i=0;i<color.length;i++){
			float  sweepitem=360/color.length;
			BtnState  b=new BtnState();
			b.setColor(color[i]);
			b.setStartSweep(sweepitem*i);
			b.setAlpha(1f);
			b.setEndSweep(sweepitem*(i+1));
			list.add(b);
		}
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth((float) 3.0); // Ïß¿í
		paint.setStyle(Style.FILL);
		RectF rf = new RectF();
 		int r = width / 2;
		rf.top = 0;
		rf.left = 0;
		rf.right = width;
		rf.bottom = height;
		
		for(int i=0;i<list.size();i++){
			BtnState  b=list.get(i);
  			paint.setColor(b.getColor());
  			b.setCenterX(width/2);
  			b.setCenterY(height/2);
  			if(b.isClick()) paint.setAlpha(50);
 			canvas.drawArc(rf, b.getStartSweep(),b.getEndSweep()-b.getStartSweep(), true, paint);
 			paint.setAlpha(100);
 			b.setClick(false);
 			list.set(i, b);
		}
		paint.setColor(Color.WHITE);
		canvas.drawCircle(width/2, height/2, height/4, paint);
  		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				check(event.getX(),event.getY());
				break;
			case MotionEvent.ACTION_UP:
				
				break;

			default:
				break;
			}
		
		
		return super.onTouchEvent(event);
	}
	
	private void check(float x, float y){
		int centerx=list.get(0).getCenterX(),centery=list.get(0).getCenterY();
		float x_=x-centerx;
		float y_=y-centery;
		//float  jiaodu=/*(float) (Math.atan2(y_,x_)/Math.PI*180f)-90*/;
		float  	jiaodu= (float) (360*Math.atan(y_/x_)/(2*Math.PI));
		if(x_<0&&y_<0){
			jiaodu+=180;
		}else if(x_>0&&y_<0){
			jiaodu+=360;

		}else if(x_<0&&y_>0){
			jiaodu+=180;
		}else if(x_>0&&y_>0){
			 

		}
		

		for(int i=0;i<list.size();i++){
			BtnState  b=list.get(i);
			 if(b.getStartSweep()<=jiaodu&&b.getEndSweep()>=jiaodu){
				 	Toast.makeText(getContext(), "btn"+i,Toast.LENGTH_SHORT).show();
				postInvalidate();
				 return ;
			 }
			
			
			
			
			
			
 		}
  		
	}
	
	
	
}
