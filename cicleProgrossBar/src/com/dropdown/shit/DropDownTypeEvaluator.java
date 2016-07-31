package com.dropdown.shit;

import android.animation.TypeEvaluator;
import android.annotation.SuppressLint;
import android.graphics.PointF;

@SuppressLint("NewApi")
public class DropDownTypeEvaluator implements TypeEvaluator<PointF> {
	 

	@Override
	public PointF evaluate(float t, PointF start, PointF end) {
		// TODO Auto-generated method stub
        PointF pointF = new PointF();
        pointF.x=start.x;
        //1/2gt^2
        int g=10;
        pointF.y=end.y*g*t*t+start.y;
 		
		return pointF;
	}

}
