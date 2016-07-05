package com.genericslab.droidplate.helper.camera;

import android.hardware.Camera;
import android.os.Handler;

import com.genericslab.droidplate.app.CoreApplication;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by shahab on 1/2/16.
 */
public class CameraHelper {

    private Handler mTaskHandler;
    private int interval;
    private CoreApplication app;

    Runnable task = new Runnable() {
        @Override
        public void run() {
             new CameraManager(app, Camera.CameraInfo.CAMERA_FACING_FRONT).getCameraInstance().takePicture();
        }
    };

    public CameraHelper(CoreApplication app, int interval) {
        this.app = app;
        mTaskHandler = new Handler();
        this.interval = interval;
    }

    public void startTask() {
        EventBus.getDefault().register(this);
        task.run();
    }

    public void stopTask() {
        EventBus.getDefault().unregister(this);
        mTaskHandler.removeCallbacks(task);
    }

    public void onEvent(CameraEvent cameraEvent) {
        if (cameraEvent.getCameraType() == Camera.CameraInfo.CAMERA_FACING_BACK) {
            mTaskHandler.postDelayed(task, interval * 1000);
        } else if (cameraEvent.getCameraType() == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            new CameraManager(app, Camera.CameraInfo.CAMERA_FACING_BACK).getCameraInstance().takePicture();
        }
    }
}
