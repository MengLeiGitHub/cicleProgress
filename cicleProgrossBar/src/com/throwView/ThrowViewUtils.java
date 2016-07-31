package com.throwView;

 
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class ThrowViewUtils {
	PointF start, end,f1,f2;
	View v;
	ThrowViewAddInterface  throwViewAddInterface;
	int width,height; 
	
	 
	public ThrowViewUtils(int width, int height, View v,ThrowViewAddInterface  throwViewAddInterface) {
		this.width = width;
 		this.height = height;
		this.v = v;
 		this.throwViewAddInterface=throwViewAddInterface;
 		initPoints();
	}
	
	@SuppressLint("NewApi")
	private void initPoints() {
		// TODO Auto-generated method stub
		start=new PointF();
		start.x=0;
		start.y=height;
		end=new PointF();
 		end.x=width;
		end.y=0;
		f1=new PointF();
 		f1.x=width/4;
		f1.y=0;
		f2=new PointF();
 		f2.x=width/4*3;
		f2.y=0;
 		v.setX(start.x);
		v.setY(start.y);
	}
 
	
	
	@SuppressLint("NewApi")
	public void start() {
		AnimatorSet anim = getAnimatorset();
		anim.start();
		anim.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				super.onAnimationEnd(animation);
 				throwViewAddInterface.addView(v);
 			}

		});

	}

	@SuppressLint("NewApi")
	private AnimatorSet getAnimatorset() {
		// TODO Auto-generated method stub
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 0.1f, 1f);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 0.1f, 1f);
		ThrowTypeEvaluator type = new ThrowTypeEvaluator(f1, f2);
		Log.e("tag", "end="+end.x+"  "+end.y);
		ValueAnimator valueAnimator = ValueAnimator.ofObject(type, start, end);
		valueAnimator.setTarget(v);
 		valueAnimator.setDuration(2000);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// TODO Auto-generated method stub
				PointF p=(PointF) arg0.getAnimatedValue();
				v.setX(p.x);
				v.setY(p.y);
			}
		});
		AnimatorSet anim1 = new AnimatorSet();
 		anim1.playTogether(scaleX, scaleY, valueAnimator);
		anim1.setInterpolator(new DecelerateInterpolator());

		return anim1;
	}

}
