package com.airbubble;

import android.animation.TypeEvaluator;
import android.annotation.SuppressLint;
import android.graphics.PointF;

@SuppressLint("NewApi")
public class LoveTypeEvaluator implements TypeEvaluator<PointF> {
	PointF f1,f2;

	public LoveTypeEvaluator(PointF point2, PointF point3) {
		// TODO Auto-generated constructor stub
		f1=point2;
		f2=point3;
	}

	@Override
	public PointF evaluate(float t, PointF start, PointF end) {
		// TODO Auto-generated method stub
        PointF pointF = new PointF();
        float m=1-t;
        pointF.x=m*m*m*start.x+3*f1.x*m*m*t+3*f2.x*t*t*m+t*t*t*end.x;
        pointF.y=m*m*m*start.y+3*f1.y*m*m*t+3*f2.y*t*t*m+t*t*t*end.y;
 		
		return pointF;
	}

}
