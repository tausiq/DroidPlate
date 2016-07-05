package com.genericslab.droidplate.helper.camera;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
import android.os.Handler;
import android.view.SurfaceView;

import com.genericslab.droidplate.app.CoreApplication;
import com.genericslab.droidplate.log.Tracer;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by shahab on 1/2/16.
 */
public class CameraManager {

    private boolean hasCamera;
    private int cameraType;
    private int cameraId;

    private Camera camera;
    private CoreApplication app;
    /**
     * @param app
     * @param cameraType {@link android.hardware.Camera.CameraInfo#CAMERA_FACING_BACK} or {@link android.hardware.Camera.CameraInfo#CAMERA_FACING_FRONT}
     */
    public CameraManager(CoreApplication app, int cameraType) {
        this.app = app;
        this.cameraType = cameraType;

        if (app.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            cameraId = getCameraId();

            if (cameraId != -1) {
                hasCamera = true;
            } else {
                hasCamera = false;
            }
        } else {
            hasCamera = false;
        }
    }

    public boolean hasCamera() {
        return hasCamera;
    }

    public CameraManager getCameraInstance() {
        camera = null;

        if (hasCamera) {
            try {
                camera = Camera.open(cameraId);
                app.setCamera(camera);
                prepareCamera();
            } catch (Exception e) {
                hasCamera = false;
            }
        }

        return this;
    }

    public void takePicture() {
        if (hasCamera) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Tracer.d("Taking pictures");
                    camera.takePicture(null, null, mPicture);
                }
            }, 2000);

        }
    }

    public void releaseCamera() {
        Tracer.i("Camera released");
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            app.setCamera(null);
            camera = null;
        }
    }

    private int getCameraId() {
        int camId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo ci = new Camera.CameraInfo();

        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, ci);
            if (ci.facing == cameraType) {
                camId = i;
            }
        }

        return camId;
    }

    private void prepareCamera() {
        SurfaceView view = new SurfaceView(app.getApplicationContext());

        try {
            camera.setPreviewDisplay(view.getHolder());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        camera.startPreview();

        Camera.Parameters params = camera.getParameters();
        params.setJpegQuality(100);

        camera.setParameters(params);
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            releaseCamera();

            if (data == null) return;

            File pictureFile = getOutputMediaFile();


            if (pictureFile == null) {
                Tracer.d("Error creating media file, check storage permissions");
                return;
            }

            try {
                Tracer.d("File created: " + pictureFile.getAbsolutePath());
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                EventBus.getDefault().post(new CameraEvent(cameraType));
            } catch (FileNotFoundException e) {
                Tracer.d("File not found: " + e.getMessage());
            } catch (IOException e) {
                Tracer.d("Error accessing file: " + e.getMessage());
            }
        }


    };

    private File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(app.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "images");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + (cameraType == Camera.CameraInfo.CAMERA_FACING_BACK ? "B_" : "F_") + timeStamp + ".jpg");

        return mediaFile;
    }
}
