package com.genericslab.droidplate.helper;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;

import com.genericslab.droidplate.log.Tracer;

import java.io.File;
import java.io.IOException;

/**
 * Created by shahab on 1/1/16.
 */
public class AudioRecordHelper {

    private File recordFile = null;
    MediaRecorder mRecorder;

    public AudioRecordHelper(Context context) {
        recordFile = new File (context.getExternalFilesDir(Environment.DIRECTORY_MUSIC), "records");
        if (!recordFile.mkdirs()) {
            Tracer.e("Audio record file directory not created");
        }
    }

    public void startTask() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(recordFile.getAbsolutePath() + File.separator + "audio.3gp");
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Tracer.e(e, "prepare() failed");
            return;
        }

        mRecorder.start();
        Tracer.i("Recording started: " + recordFile.getAbsolutePath());
    }

    public void stopTask() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }
}
