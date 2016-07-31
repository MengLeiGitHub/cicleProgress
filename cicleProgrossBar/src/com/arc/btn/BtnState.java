package com.arc.btn;

public class BtnState {
	private float  startSweep;
	private float  endSweep;
	private boolean  isClick;
	private int    color;
	private int CenterX;
	private  int  CenterY;
	private float alpha;
	
	
	public float getAlpha() {
		return alpha;
	}
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	public int getCenterX() {
		return CenterX;
	}
	public void setCenterX(int centerX) {
		CenterX = centerX;
	}
	public int getCenterY() {
		return CenterY;
	}
	public void setCenterY(int centerY) {
		CenterY = centerY;
	}
	public float getStartSweep() {
		return startSweep;
	}
	public void setStartSweep(float startSweep) {
		this.startSweep = startSweep;
	}
	public float getEndSweep() {
		return endSweep;
	}
	public void setEndSweep(float endSweep) {
		this.endSweep = endSweep;
	}
	public boolean isClick() {
		return isClick;
	}
	public void setClick(boolean isClick) {
		this.isClick = isClick;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
}
