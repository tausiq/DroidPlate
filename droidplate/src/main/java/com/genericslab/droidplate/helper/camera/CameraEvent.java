package com.genericslab.droidplate.helper.camera;

/**
 * Created by shahab on 1/2/16.
 */
public class CameraEvent {

    private int cameraType;

    public CameraEvent(int cameraType) {
        this.cameraType = cameraType;
    }

    public int getCameraType() {
        return cameraType;
    }
}
