package com.cicleprogrossbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MLProgressBar extends View {
	private int filledColor = Color.parseColor("#FFAF60");// 表示 未填�??
	private int unfilled = Color.parseColor("#BEBEBE");// 表示 填充颜色
	private int middleColor = Color.parseColor("#FFFFFF");// 表示 圆中间的小圆的颜�??
	private int SpacerColor = Color.parseColor("#F34F23");// 表示 间隔颜色

	private boolean isDone = false;
	private float startSweep=90;

	public MLProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	int index = 2;
	float rotate = 0, rotateItem = 10;
	Canvas canvasthis;

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (!isDone)
			canvasthis = canvas;
		canvas = canvasthis;
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

		paint.setAntiAlias(true); // 设置画笔为无锯齿
		// canvas.drawColor(Color.WHITE); //白色背景
		paint.setStrokeWidth((float) 3.0); // 线宽
		paint.setStyle(Style.FILL);
		paint.setColor(unfilled); // 设置画笔颜色

		if (!isDone) {
			canvas.drawCircle(width / 2, height / 2, height / 2, paint);
			float SweepArcItem =60;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// paint.setColor(filledColor);
			int colorstart[]={Color.BLACK,Color.BLUE,Color.CYAN,Color.GRAY,Color.GREEN,Color.LTGRAY,Color.MAGENTA,Color.RED};
 			
			Shader mShader = new SweepGradient(width / 2, height / 2, colorstart,null);//options[] 为空  表示 每种颜色均匀 分配
			paint.setShader(mShader);
			canvas.drawArc(rf, startSweep- SweepArcItem,2*SweepArcItem, true, paint);
		//	canvas.drawArc(rf, startSweep, SweepArcItem, true, paint);
			startSweep += SweepArcItem;
			 
			int yuanhuanWidth = width / 10;
			paint.setShader(null);
			paint.setColor(middleColor);
			canvas.drawCircle(width / 2, height / 2,
					height / 2 - yuanhuanWidth, paint);
			// 字体
			paint.setColor(Color.BLACK);
			int textSize = width / 8;
			paint.setTextSize(textSize);

			int progressWidth = (int) paint.measureText(("加载�?"));
			canvas.drawText("加载�?...", width / 2 - progressWidth / 2, height / 2,
					paint);
		}
		if (!isDone) {
			invalidate();
		} else {

		}

	}

	/**
	 * 表示 百分之几 取�?�范�?? 0-1 0.5表示半圆 1表示全圆
	 * 
	 * @param sweep
	 */

	public void startAnim() {
		invalidate();
	}
}
