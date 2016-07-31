package pxbitmap;

import java.util.Random;

import com.example.cicleprogrossbar.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
 
public class RecordingView extends View{
 
 
    Paint mPaint;
 
    Bitmap mBitmap;
 
    int mBitmapWidth = 0;
    int mBitmapHeight = 0;
 
    int mArrayColor[] = null;
    int mArrayColorLengh = 0;
    long startTime = 0;
    int mBackVolume = 0;
 
    public RecordingView(Context context) {
        super(context);
        init(context);
 
    }
 
    public RecordingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
 
    void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
 
        //在这里创建了一张bitmap
        mBitmap = BitmapFactory.decodeResource(context.getResources(),
            R.drawable.huatong);
        //将这张bitmap设置为背景图片
        setBackgroundDrawable(new BitmapDrawable(mBitmap));
 
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
 
        mArrayColorLengh = mBitmapWidth * mBitmapHeight;
        mArrayColor = new int[mArrayColorLengh];
        int count = 0;
        for (int i = 0; i < mBitmapHeight; i++) {
        for (int j = 0; j < mBitmapWidth; j++) {
            //获得Bitmap 图片中每一个点的color颜色值
            int color = mBitmap.getPixel(j, i);
            //将颜色值存在一个数组中 方便后面修改
            mArrayColor[count] = color;
            //如果你想做的更细致的话 可以把颜色值的R G B 拿到做响应的处理 笔者在这里就不做更多解释
            int r = Color.red(color);
            int g = Color.green(color);
            int b = Color.blue(color);
             
            count++;
        }
        }
        startTime = System.currentTimeMillis();
    }
 
    /**
     * 返回一个随机数
     * 
     * @param botton
     * @param top
     * @return
     */
    int UtilRandom(int botton, int top) {
        return ((Math.abs(new Random().nextInt()) % (top + 1 - botton)) + botton);
    }
 
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //每隔100毫秒设置一下填充的颜色区域
        if (System.currentTimeMillis() - startTime >= 100) {
        startTime = System.currentTimeMillis();
        setVolume(UtilRandom(0, 100));
        }
         
        //用于刷新屏幕
        invalidate();
    }
 
    public void setVolume(int volume) {
        int startY = 0;
        int endY = 0;
        boolean isAdd = false;
        //判断当前应该填充新区域 还是还原旧的区域 
        if (mBackVolume > volume) {
        isAdd = false;
        startY = getValue(mBackVolume);
        endY = getValue(volume);
        } else {
        isAdd = true;
        startY = getValue(volume);
        endY = getValue(mBackVolume);
        }
        //没必要每次都循环图片中的所有点，因为这样会比较耗时。
        int count = startY * mBitmapWidth;
        //从图片须要填充或者还原 颜色的起始点 开始 到 终点 
        for (int i = startY; i < endY; i++) {
        for (int j = 0; j < mBitmapWidth; j++) {
            if (isAdd) {
            //将需要填充的颜色值如果不是
            //在这说明一下 如果color 是全透明 或者全黑 返回值为 0
            //getPixel()不带透明通道 getPixel32()才带透明部分 所以全透明是0x00000000 
            //而不透明黑色是0xFF000000 如果不计算透明部分就都是0了
            int color = mBitmap.getPixel(j, i);
            if (color != 0) {
                mBitmap.setPixel(j, i, Color.BLACK);
            }
            } else {
            //如果是还原颜色 把现在点的颜色 赋值为之前保存颜色的数组
            mBitmap.setPixel(j, i, mArrayColor[count]);
            }
            count++;
        }
        }
        mBackVolume = volume;
    }
     
    //通过百分比 根据图片宽高算出实际填充 高度
    public int getValue(int volume) {
        return mBitmapHeight - (mBitmapHeight * volume / 100);
    }
 
}