package com.cicleprogrossbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class CicleProgressBarVerical extends View {

	public CicleProgressBarVerical(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	Path p = new Path();
	float progress, progressNow = 0;
	float startAngle = 90, sweepAngle = 0;
	boolean isAnim = false;
	double a;

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		RectF rf = new RectF();
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		int r = width / 2;
		rf.top = 0;
		rf.left = 0;
		rf.right = width;
		rf.bottom = height;
		p.arcTo(rf, 0, 180);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.GRAY);
		canvas.drawCircle(width / 2, height / 2, width / 2, paint);
		paint.setColor(Color.GREEN);

		float jiaodu = 180 / 100;

		if (isAnim)
			startAngle -= jiaodu;
		if (startAngle > 0) {

		} else {
			startAngle += 360;
		}
		sweepAngle = sweepAngle + jiaodu * 2;
		if (isAnim) {
			progressNow = (float) (sweepAngle / 3.6f);
		}

		progressNow = (float) (Math.round(progressNow * 100) / 100);
		float right_x = (float) (width / 2 + Math.sin((startAngle + 90) / 180
				* Math.PI)
				* r);
		float right_y = (float) (width / 2 - Math.cos((startAngle + 90) / 180f
				* Math.PI)
				* r);

		float left_x = (float) (width / 2 + Math
				.sin((startAngle + sweepAngle + 90) / 180f * Math.PI) * r);
		float left_y = (float) (width / 2 - Math
				.cos((startAngle + sweepAngle + 90) / 180f * Math.PI) * r);

		/*
		 * Shader shader=new LinearGradient(width/2, height, x, y, new int[]
		 * {Color.BLUE,Color.WHITE},null,Shader.TileMode.REPEAT);
		 * //paint.setShader(shader);
		 */
		canvas.drawArc(rf, startAngle, sweepAngle, false, paint);
		paint.setColor(Color.BLACK);
 
		float path_width = right_x - left_x;
		 
		paint.setColor(Color.BLUE);
		paint.setTextSize(30);
		canvas.drawText(progressNow + "%", width / 2 - width / 5, height / 2
				+ width / 4, paint);
		if (progress != progressNow) {
			if (isAnim)
				invalidate();
		} else {
			isAnim = false;
		}

	}

	public void startAnim(float progress) {
		setProgress(progress);
		isAnim = true;
		invalidate();
	}

	public float getProgress() {
		return progressNow;
	}

	public void setProgress(float progress) {
		startAngle = 90;
		sweepAngle = 0;
		this.progress = (float) (Math.round(progress * 100) / 100);
		;
	}

}
