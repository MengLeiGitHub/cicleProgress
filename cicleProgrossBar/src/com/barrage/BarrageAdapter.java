package com.barrage;

import android.view.View;
import android.view.ViewGroup;

public abstract class BarrageAdapter implements DataChangeInterface{
	protected DataChangeInterface dataChange;
 	public abstract int getCount();
 	public abstract int getCurrentNum();
 //	public abstract View getCurrentView();
   	public abstract Object getItem(int arg0);
  	public abstract long getItemId(int arg0);
  	public abstract View getView(int arg0, View arg1, ViewGroup arg2);
 	public abstract void register(DataChangeInterface change);
 
 	
}
