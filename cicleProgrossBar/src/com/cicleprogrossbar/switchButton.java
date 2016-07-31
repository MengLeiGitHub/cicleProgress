package com.cicleprogrossbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class switchButton extends View {
	private int selectColor = Color.parseColor("#00BB00");// 选中的颜色
	private int unSelected = 0xffcccccc;// 未选中颜色
	private int btnShadeColor = 0xffdddddd;// 按钮阴影颜色
	private int btnColor = Color.WHITE;// 按钮颜色

	private boolean isShade = false;// 设置是否有阴影
	private float btn_height;// 表示按钮的高度
	private switchOnclickInterface onclickInteferface;

	@SuppressLint("NewApi")
	public switchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setLayerType(LAYER_TYPE_SOFTWARE, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(width, height);
	}

	Path path = new Path();
	RectF rf = new RectF();
	int top, bottom, left, right, centerX, centerY;

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		top = left = 0;
		centerX = w / 2;
		centerY = h / 2;
		bottom = (int) (h * 0.8f);
		right = w;
		rf.top = top;
		rf.bottom = h;

		rf.left = left;
		rf.right = h;

		path.arcTo(rf, 90, 180);
		rf.right = right;
		rf.left = rf.right - h;
		path.arcTo(rf, 270, 180);
		path.close();
	}

	int animCount = 10;// 缩放次数
	int animNormal = 10;
	boolean isAnim = false;//
	float nomarl = 1f;
	float scale = 1f;

	int ANIM_ZOOM_OUT = 1, ANIM_ZOOM_IN = 2;// 缩小
	int ANIM_ZOOM = ANIM_ZOOM_IN;

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);

		canvas.save();

		paint.setColor(unSelected);
		canvas.drawPath(path, paint);
		canvas.save();
		canvas.restore();
		canvas.scale(scale * 0.94f, scale * 0.94f, 2 * centerX - bottom / 2,
				centerY);
		paint.setColor(selectColor);
		paint.setStrokeWidth(2);
		canvas.drawPath(path, paint);
		canvas.restore();

		if (isShade) {
			paint.setColor(btnShadeColor);
			canvas.drawCircle(centerY + (right - rf.bottom) * (1 - scale),
					centerY, centerY, paint);
			btn_height = centerY * 0.89f;
		} else {
			btn_height = centerY * 0.95f;
		}
		paint.setColor(btnColor);
		canvas.drawCircle(centerY + (right - rf.bottom) * (1 - scale), centerY,
				btn_height, paint);
		canvas.save();

		if (isAnim) {
			if (animCount > 0) {
				paint.setColor(unSelected);
				canvas.drawPath(path, paint);
				canvas.save();
				paint.setColor(selectColor);
				canvas.scale(scale, scale, 2 * centerX - bottom / 2, centerY);
				canvas.drawPath(path, paint);
				canvas.save();
				canvas.restore();
				canvas.restore();
				if (isShade) {
					paint.setColor(btnShadeColor);
					paint.setStyle(Style.FILL);
					canvas.drawCircle(centerY + centerX * (1 - scale), centerY,
							centerY, paint);
					btn_height = centerY * 0.89f;
				} else {
					btn_height = centerY * 0.95f;
				}

				paint.setColor(btnColor);
				canvas.drawCircle(centerY + (right - rf.bottom) * (1 - scale),
						centerY, btn_height, paint);
				canvas.save();
				if (ANIM_ZOOM == ANIM_ZOOM_OUT) {
					scale *= 0.3f;
				} else {
					scale /= 0.3f;
				}
				animCount--;
				invalidate();
			} else {
				animCount = animNormal;
				if (ANIM_ZOOM == ANIM_ZOOM_IN)
					scale = nomarl;
				isAnim = false;

			}
		}

	}

	PointF downf = new PointF();

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// return super.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downf.x = event.getX();
			downf.y = event.getY();
			return true;
		case MotionEvent.ACTION_UP:
			if (downf.x == event.getX() && downf.y == event.getY()) {
				if (onclickInteferface != null)
					onclickInteferface.onsingleClick(this);
			}

			isAnim = true;
			ANIM_ZOOM = ANIM_ZOOM == ANIM_ZOOM_OUT ? ANIM_ZOOM_IN
					: ANIM_ZOOM_OUT;
			invalidate();
			break;
		}

		return super.onTouchEvent(event);
	}

	public boolean isOpen() {
		return ANIM_ZOOM == ANIM_ZOOM_IN;
	}

	public boolean isClosed() {
		return ANIM_ZOOM == ANIM_ZOOM_OUT;
	}

	public boolean setOpen() {

		if (ANIM_ZOOM == ANIM_ZOOM_IN) {
			return false;
		} else {
			ANIM_ZOOM = ANIM_ZOOM_IN;
			isAnim = true;
			invalidate();
		}
		return true;
	}

	public boolean setClose() {

		if (ANIM_ZOOM == ANIM_ZOOM_OUT) {
			return false;
		} else {
			ANIM_ZOOM = ANIM_ZOOM_OUT;
			isAnim = true;
			invalidate();
		}
		return true;
	}

	/**
	 * 获取开关状态
	 */
	public String getState() {
		if (ANIM_ZOOM == ANIM_ZOOM_IN)
			return "开";
		else if (ANIM_ZOOM == ANIM_ZOOM_OUT)
			return "关";
		return null;
	}

	/**
	 * 设置按钮外阴影
	 * 
	 * @param flag
	 *            true有 false 无
	 */
	public void setShade(boolean flag) {
		isShade = flag;
		invalidate();
	}

	public interface switchOnclickInterface {
		public void onsingleClick(View view);

		public void onSuccess();

		public void onFailes();
	}

	public void setOnclickInteferface(switchOnclickInterface onclickInteferface) {
		this.onclickInteferface = onclickInteferface;
	}

}
