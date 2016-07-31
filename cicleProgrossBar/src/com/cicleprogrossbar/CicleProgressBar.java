package com.cicleprogrossbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CicleProgressBar extends View {

	public CicleProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private float sweepTotal;
	private float sweepFirst=-90;
	private float sweepSecond;
	private int index = 2;
	Canvas canvas;
	private  boolean  flag=true;
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
 		RectF rf = new RectF();
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		rf.top = 0;
		rf.left = 0;
		rf.right = width;
		rf.bottom = height;
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		if(flag){
			CicleProgressBar.this.canvas=canvas;
			flag=false;
		}
		canvas=CicleProgressBar.this.canvas;
		canvas.drawColor(Color.WHITE);

		paint.setAntiAlias(true); // ÉèÖÃ»­±ÊÎªÎÞ¾â³Ý
		paint.setColor(Color.BLACK); // ÉèÖÃ»­±ÊÑÕÉ«
		canvas.drawColor(Color.WHITE); // °×É«±³¾°
		paint.setStrokeWidth((float) 3.0); // Ïß¿í
		paint.setStyle(Style.FILL);
		
		int num=50;
		float sweepitem = sweepTotal / num;

		for(int i=1;i<=num;i++){
 			if (index % 2 == 0) {
				paint.setColor(Color.GREEN);
			} else {
				paint.setColor(Color.GRAY);
			}
			index++;
			 
			canvas.drawArc(rf, sweepFirst, sweepitem, true, paint);
 			sweepFirst += sweepitem;
 		}
		paint.setColor(Color.WHITE);
		canvas.drawCircle(width/2, height/2, width/2-50, paint);

		
	/*	if (sweepFirst <= sweepTotal) {
			Log.e("=====", "=====");
			float sweepitem = sweepTotal / 10f;
			if (index % 2 == 0) {
				paint.setColor(Color.GREEN);
			} else {
				paint.setColor(Color.BLUE);
			}
			index++;
			canvas.drawArc(rf, sweepFirst, sweepitem, true, paint);
			sweepFirst += sweepitem;
 			invalidate();
		} else {

		}*/

	}

	public void startAnim() {
		this.post(null);
	}

	public void startAnim(float sweep) {
		sweepTotal = 360 * sweep;
		invalidate();
	}
}
