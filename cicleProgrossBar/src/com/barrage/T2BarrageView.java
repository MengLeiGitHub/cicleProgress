package com.barrage;

 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
 
/**
 * 弹幕
 * @author Administrator
 *
 */
public class T2BarrageView extends LinearLayout implements DataChangeInterface{
  	
 	private BarrageAdapter  adapter;	
 	private boolean  END=false,START=false,SUSPEND=false;
 	private HashMap<String, Animator>  list=new HashMap<String, Animator>();
  	 
	public BarrageAdapter getAdapter() {
		return adapter;
	}
 	public void setAdapter(BarrageAdapter adapter) {
		this.adapter = adapter;
		adapter.register(this);
		addView();
	}

	public T2BarrageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initUI(context);
	}
	Context context;
	public T2BarrageView(Context context) {
		super(context);
		initUI(context);
	}
	
	@SuppressLint("NewApi")
	public T2BarrageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initUI(context);
	}

	private LinearLayout container;
	private void initUI(Context context) {
		this.context = context;
   		container = this;
   		setOrientation(LinearLayout.VERTICAL);
		 
	 
		
	}
	
	/**
	 * 当前需要加载的view
	 */
	private int curPos = 1;
	
	/**
	 * 添加弹幕
	 */
	private void addView() {
		if (curPos >= adapter.getCount()) return;
		if(SUSPEND)return;
		
		View v=null;
		if(container.getChildCount()>=curPos-1)
		   v=container.getChildAt(curPos-1);
		View view=adapter.getView(curPos, v, container);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.setMargins(5, 5, 5, 0);
 		container.addView(view, lp);
 		
		if (curPos < 4) {
			alphaAnim(view);
		} else {
			Log.e("tag", "Curpos="+curPos+" adapter.getCount()=  "+adapter.getCount());
			addAnim(curPos == adapter.getCount()-1 );
		}
	}
	
	/**
	 * 向上平移的同时删除第一个view动画
	 * @param isEnd
	 */
	@SuppressLint("NewApi")
	private void addAnim(final boolean isEnd ) {
		final  String animName=Math.random()+"";
		
		ObjectAnimator anim = ObjectAnimator.ofFloat(container.getChildAt(0), "alpha", 1f, 0f);
		
		ObjectAnimator trans = ObjectAnimator.ofFloat(container, 
				"translationY", 
				0, 
				-container.getChildAt(0).getMeasuredHeight() - 5);
		
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.play(anim).with(trans);
		animatorSet.setDuration(2000);
		animatorSet.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				list.put(animName, animation);
 			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				list.remove(animName);

				Log.e("isEnd", isEnd+"");
				if (isEnd) {
					alphaDismissAnim();
				} else {
					if(container.getChildCount()>0)
					   container.removeViewAt(0);
					if (curPos < adapter.getCount()) {
						END=false;
						curPos++;
						addView();
						
					}
				}
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
		animatorSet.start();
	}
	
	 
	
	/**
	 * 移除子布局并添加消失动画
	 */
	@SuppressLint("NewApi")
	private void alphaDismissAnim() {
		ObjectAnimator anim = ObjectAnimator.ofFloat(this, "alpha", 1.0f, 1f);
		final  String animName=Math.random()+"";

		anim.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				list.put(animName, animation);

			}
			@Override
			public void onAnimationRepeat(Animator animation) {
				
			}
			@Override
			public void onAnimationEnd(Animator animation) {
				//container.removeAllViews();
				list.remove(animName);

				if(container.getChildCount()>0)
					   container.removeViewAt(0);
				END=true;
			}
			@Override
			public void onAnimationCancel(Animator animation) {
				
			}
		});
		anim.setDuration(1000);
		anim.start();
	}
	
	/**
	 * 添加弹幕数据动画
	 * @param v
	 */
	
	@SuppressLint("NewApi")
	private void alphaAnim(View v) {
		ObjectAnimator anim = ObjectAnimator.ofFloat(v, "alpha", 1.0f, 1f);
		final  String animName=Math.random()+"";
 		anim.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				list.put(animName, animation);

			}
			@Override
			public void onAnimationRepeat(Animator animation) {
			}
			@Override
			public void onAnimationEnd(Animator animation) {
				list.remove(animName);

				if (curPos < adapter.getCount()) {
					curPos++;
					addView();
				}
			}
			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
		anim.setDuration(1000);
		anim.start();
	}
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		Log.e("e", curPos+"==="+adapter.getCount()+"  DATAALLSHOWS="+END);
 		if (curPos<adapter.getCount()&&END) {
			this.post(new Runnable() {
 				@Override
				public void run() {
					// TODO Auto-generated method stub
 					curPos++;
 					addView();
				}
			});
			
		}
	}
	@Override
	public void notifyDataSetInvalidated() {
		// TODO Auto-generated method stub
		container.removeAllViews();
		container.clearAnimation();
		try{
			clear();
		}catch(Exception e){
			clear();
		}
		 
		curPos=1;
		addView();
 	}
	@SuppressLint("NewApi")
	private void clear(){
		 for(Entry<String, Animator> entry: list.entrySet()){  
			 entry.getValue().end();   
			 entry.getValue().cancel();
   		  }  
		 list.clear();
	}
	@SuppressLint("NewApi")
	@Override
	public void AnimPause() {
		// TODO Auto-generated method stub
		for(Entry<String, Animator> entry: list.entrySet()){  
 			 entry.getValue().pause();
  		  }  
	}
	@SuppressLint("NewApi")
	@Override
	public void AnimStart() {
		// TODO Auto-generated method stub
		for(Entry<String, Animator> entry: list.entrySet()){  
			 entry.getValue().resume();
			 
 		  }  
	}
	
	
}
