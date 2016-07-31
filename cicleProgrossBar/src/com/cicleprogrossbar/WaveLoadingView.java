package  com.cicleprogrossbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class WaveLoadingView extends View {

    private Paint wavePaint;
    private Paint textPaint;
    private int textColor = 0xffFFFFFF;
    private int waveColor = 0xff0099CC;
    private int textSize = 50;
    private Path path;

    // 瀹革箑褰搁崑蹇曅�? 锠�
    private int fai = 0;
    // 娑撳﹣绗呴崑蹇曅�?
    private float k = -50;
    // 鐟欐帡锟界喎瀹�
    private float w = 0.5f;
    // 閹割垰绠�?
    private int a = 20;

    private int height;
    private int width;
    private float targetHeight;
    private float textHeight;
    private int progress = 0;
    // 0% 閺冭绱濈粚铏规閻ㄥ嫰鐝惔锟�?
    private float baseBlank;

    private OnFinishedListener listener;

    private int ms = 4;

    private boolean isRun = true;

    public WaveLoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public WaveLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public WaveLoadingView(Context context) {
        super(context);
        init();
    }

    private void init() {
        wavePaint = new Paint();
        wavePaint.setAntiAlias(true);
        wavePaint.setColor(waveColor);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

        path = new Path();

        new MyThread().start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setPath(canvas);
        wavePaint.setColor(Color.WHITE);

        canvas.drawCircle(width/2, width/2*3, width/2-100, wavePaint);

        wavePaint.setColor(waveColor);

        canvas.drawPath(path, wavePaint);
        wavePaint.setXfermode(new PorterDuffXfermode(Mode.SRC_ATOP));

        String str = progress + "%";
        float strWidth = textPaint.measureText(str);
        canvas.drawText(str, width / 2 - (strWidth / 2), textHeight, textPaint);
    }

    private void setPath(Canvas canvas){
        int x = 0;
        int y = 0;
        path.reset();
        for (int i = 0; i < width; i++) {
            x = i;
            y = (int) (a * Math.sin((i * w + fai) * Math.PI / 180) + k);
            if (i == 0) {
             	path.moveTo(x, y);
            }
            path.quadTo(x, y, x + 1, y);
        }
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.close();
    }

    /**
     *
     * @param p 0~1
     */
    public void updateProgress(float p) {
        if(p >=0 && p <= 1){
            targetHeight = (float) (baseBlank * (1 - p));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initLayoutParams();
    }

    private void initLayoutParams(){
        height = this.getHeight();
        width = this.getWidth();
        baseBlank = (float) (height * 0.9);
        targetHeight = baseBlank;
        k = baseBlank;
        textHeight = baseBlank;
    }

    public WaveLoadingView setTextColor(int color) {
        this.textColor = color;
        textPaint.setColor(textColor);
        return this;
    }

    public WaveLoadingView setTextSize(int size){
        this.textSize = size;
        textPaint.setTextSize(textSize);
        return this;
    }

    public WaveLoadingView setWaveColor(int color) {
        this.waveColor = color;
        wavePaint.setColor(waveColor);
        return this;
    }

    /**
     *
     * @param amplitude
     *            濞夈垺姘�?幐顖氱畽閿涳�? 姒涙顓绘稉锟� 20
     */
    public WaveLoadingView setAmplitude(int a) {
        this.a = a;
        return this;
    }

    /**
     *
     * @param w
     *            姒涙顓绘稉锟�0.5
     */
    public WaveLoadingView setPalstance(float w) {
        this.w = w;
        return this;
    }

    /**
     *
     * @param ms
     *            姒涙顓绘稉锟�4濮ｎ偆顫�?
     */
    public WaveLoadingView setRefreshTime(int ms) {
        this.ms = ms;
        return this;
    }

    public void setOnFinishedListener(OnFinishedListener l) {
        this.listener = l;
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            while (isRun) {
                fai++;
                if (k > targetHeight) {
                    k -= 0.5;
                    progress = (int) ((baseBlank - k) / baseBlank * 100);
                    if (textHeight > (height / 2)) {
                        textHeight -= 0.5;
                    }
                }
                if (progress >= 100 && listener != null) {
                    listener.onFinished();
                    isRun = false;
                }
                if (fai == 360) {
                    fai = 0;
                }
                mHandler.sendEmptyMessage(1);
                try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                invalidate();
            }
        }
    };

    public interface OnFinishedListener {
        public void onFinished();
    }

}