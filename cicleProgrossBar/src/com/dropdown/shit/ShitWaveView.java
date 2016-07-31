package com.dropdown.shit;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ShitWaveView extends View {
	int  width,height;
	
	
	public ShitWaveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       height = getMeasuredHeight();
       width = getMeasuredWidth();
    }
	
	int fudong=10;
	float progress=0;
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Path p=new Path();
		int x = 0,y,y2 = 0;
		Random  random=new Random();
		 y2=(int) (height-height*progress);
 	    RectF rectf=new RectF(-5,y2 ,width+50,height+500);
        p.addArc(rectf, 90.0f - 145.0f / 2.0f, 145.0f);  

  		w+=5;
  		 for (int i = 5; i < width - 5; i++) {  
             p.lineTo(i, (float) (y2 + 100 * Math.cos((float) (i + w) / (float) (width - 5) * Math.PI)));  
             p.lineTo(i, (float) (y2 - 100 * Math.cos((float) (i + w) / (float) (width - 5) * Math.PI)));  
         }
  		
  		
   		
  		
 		Paint paint=new Paint();
		paint.setColor(Color.GREEN);
		paint.setStyle(Style.FILL);
		paint.setAntiAlias(true);
		canvas.drawPath(p, paint);
 	}
	int w=0;
	public void setProgress(float f){
		progress=f;
		invalidate();
	}
	public float  getProgress(){
		return progress;
	}
	
}
