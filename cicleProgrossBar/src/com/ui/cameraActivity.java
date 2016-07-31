package com.ui;

import com.camera.CameraPreview;
import com.example.cicleprogrossbar.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class cameraActivity extends Activity{
	
	CameraPreview cam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
	 
		
			cam=(CameraPreview) this.findViewById(R.id.camera);
			this.findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					cam.setCamera(getCameraInstance(getDefaultCameraId()));
				}
			});
			
				
	}
	
	
	int mNumberOfCameras;
    int mCameraCurrentlyLocked;

    // The first rear facing camera
    int mDefaultCameraId;

	
	
	
	 /**
     * 得到默认相机的ID
     * 
     * @return
     */
    @SuppressLint("NewApi")
	private int getDefaultCameraId()
    {
        Log.d("tag", "getDefaultCameraId");
        int defaultId = -1;

        // Find the total number of cameras available
        mNumberOfCameras = Camera.getNumberOfCameras();

        // Find the ID of the default camera
        CameraInfo cameraInfo = new CameraInfo();
        for (int i = 0; i < mNumberOfCameras; i++)
        {
            Camera.getCameraInfo(i, cameraInfo);
            Log.d("tag", "camera info: " + cameraInfo.orientation);
            if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK)
            {
                defaultId = i;
            }
        }
        if (-1 == defaultId)
        {
            if (mNumberOfCameras > 0)
            {
                // 如果没有后向摄像头
                defaultId = 0;
            }
            else
            {
                // 没有摄像头
                Toast.makeText(getApplicationContext(), "无相机",
                        Toast.LENGTH_LONG).show();
            }
        }
        return defaultId;
    }
    /** A safe way to get an instance of the Camera object. */
    @SuppressLint("NewApi")
	public static Camera getCameraInstance(int cameraId)
    {
        Log.d("tag", "getCameraInstance");
        Camera c = null;
        try
        {
            c = Camera.open(cameraId); // attempt to get a Camera instance
        }
        catch (Exception e)
        {
            // Camera is not available (in use or does not exist)
            e.printStackTrace();
            Log.e("tag", "Camera is not available");
        }
        return c; // returns null if camera is unavailable
    }
	
}
