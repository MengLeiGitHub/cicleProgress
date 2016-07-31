package com.camera;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
 
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	    private SurfaceHolder mHolder;
	    private Camera mCamera;
	    Size mPreviewSize;
	    List<Size> mSupportedPreviewSizes;
	    int   cameraWidth,cameraHeight;

	    public CameraPreview(Context context, AttributeSet attrs, int defStyle)
	    {
	        super(context, attrs, defStyle);
        	init();

	    }

	    public CameraPreview(Context context, AttributeSet attrs)
	    {
	        super(context, attrs);
        	init();

	    }

	    public CameraPreview(Context context)
	    {
	        super(context);
        	init();

	    }

	    /**
	     * ��ʼ������
	     * 
	     */
	    private void init()
	    {
	        Log.d("tag", "CameraPreview initialize");

	        // Install a SurfaceHolder.Callback so we get notified when the
	        // underlying surface is created and destroyed.
	        mHolder = getHolder();
	        mHolder.addCallback(this);
	        // deprecated setting, but required on Android versions prior to 3.0
	        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	       
	    }

	    public void setCamera(Camera camera)
	    {

	        mCamera = camera;
	        if (mCamera != null)
	        {
	            mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
	            
	            requestLayout();
	            showcamera();
	        }
	    }

	    @Override
	    public void surfaceCreated(SurfaceHolder holder)
	    {
	        Log.d("tag", "surfaceCreated");
	        // The Surface has been created, now tell the camera where to draw the
	        // preview.
	        try
	        {
	            if (null != mCamera)
	            {
	                mCamera.setPreviewDisplay(holder);
	            }
	        }
	        catch (IOException e1)
	        {
	            e1.printStackTrace();

	            Log.d("tag",
	                    "Error setting camera preview display: " + e1.getMessage());
	        }
	        try
	        {
	            if (null != mCamera)
	            {
	                mCamera.startPreview();
		            Log.d("tag", "surfaceCreated successfully! ");

	            }

	        }
	        catch (Exception e)
	        {
	            Log.d("tag",
	                    "Error setting camera preview: " + e.getMessage());
	        }
	    }

	    @Override
	    public void surfaceChanged(SurfaceHolder holder, int format, int width,
	            int height)
	    {

	        Log.d("tag", "surface changed");
	        // If your preview can change or rotate, take care of those events here.
	        // Make sure to stop the preview before resizing or reformatting it.

	        if (null == mHolder.getSurface())
	        {
	            // preview surface does not exist
	            return;
	        }

	        // stop preview before making changes
	        try
	        {
	            if (null != mCamera)
	            {
	                mCamera.stopPreview();
	            }
	        }
	        catch (Exception e)
	        {
	            // ignore: tried to stop a non-existent preview
	        }

	        // set preview size and make any resize, rotate or
	        // reformatting changes here

	        showcamera();
	    }

	    private void  showcamera(){
	    	 if (null != mCamera)
		        {
	    		    if(mPreviewSize==null)initcamersize();
		            Camera.Parameters parameters = mCamera.getParameters();
		            parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);

		            requestLayout();

		            mCamera.setParameters(parameters);
		            mCamera.setDisplayOrientation(90);
		            Log.d("tag", "camera set parameters successfully!: "
		                    + parameters);
		        }
		        // ��������������óߴ�

		        // start preview with new settings
		        try
		        {
		            if (null != mCamera)
		            {
		                mCamera.setPreviewDisplay(mHolder);
		                mCamera.startPreview();
		            }

		        }
		        catch (Exception e)
		        {
		            Log.d("tag",
		                    "Error starting camera preview: " + e.getMessage());
		        }
	    }
	    
	    
	    
	    @Override
	    public void surfaceDestroyed(SurfaceHolder holder)
	    {
	        Log.d("tag", "surfaceDestroyed");

	        if (null != mCamera)
	        {
	            mCamera.stopPreview();
	        }

	    }

	    @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	    {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	        // We purposely disregard child measurements because act as a
	        // wrapper to a SurfaceView that centers the camera preview instead
	        // of stretching it.
	        final int width = resolveSize(getSuggestedMinimumWidth(),
	                widthMeasureSpec);
	        final int height = resolveSize(getSuggestedMinimumHeight(),
	                heightMeasureSpec);
	        
	        cameraWidth=getMeasuredWidth();
	        cameraHeight=getMeasuredHeight();
	        
	        setMeasuredDimension(width, height);

	        
	       
	        
	        
	    }

	    
	    void initcamersize(){
	    	if (mSupportedPreviewSizes != null)
	        {
	        	 mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, cameraWidth,
	        			 cameraHeight);
	        }
	    }
	    
	    
	    private Size getOptimalPreviewSize(List<Size> sizes, int w, int h)
	    {
	        final double ASPECT_TOLERANCE = 0.1;
	        double targetRatio = (double) w / h;
	        if (sizes == null)
	            return null;

	        Size optimalSize = null;
	        double minDiff = Double.MAX_VALUE;

	        int targetHeight = h;

	        // Try to find an size match aspect ratio and size
	        for (Size size : sizes)
	        {
	            double ratio = (double) size.width / size.height;
	            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
	                continue;
	            if (Math.abs(size.height - targetHeight) < minDiff)
	            {
	                optimalSize = size;
	                minDiff = Math.abs(size.height - targetHeight);
	            }
	        }

	        // Cannot find the one match the aspect ratio, ignore the requirement
	        if (optimalSize == null)
	        {
	            minDiff = Double.MAX_VALUE;
	            for (Size size : sizes)
	            {
	                if (Math.abs(size.height - targetHeight) < minDiff)
	                {
	                    optimalSize = size;
	                    minDiff = Math.abs(size.height - targetHeight);
	                }
	            }
	        }
	        return optimalSize;
	    }
}
