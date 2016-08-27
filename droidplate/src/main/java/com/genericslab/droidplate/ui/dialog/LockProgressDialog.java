package com.genericslab.droidplate.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.genericslab.droidplate.R;
import com.genericslab.droidplate.config.Config;


/**
 * Created by shahab on 12/9/15.
 */
public class LockProgressDialog extends AppCompatDialogFragment {

    AlertDialog dialog;
    TextView txtProgressMessage;
    Button btnNegative;
    Activity activity;
    DialogListener listener;

    public interface DialogListener {
        void onCancelDialog();
    }

    public LockProgressDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

        if (activity instanceof DialogListener)
            listener = (DialogListener) activity;

        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    @Override
    public void setTargetFragment(Fragment fragment, int requestCode) {
        super.setTargetFragment(fragment, requestCode);

        if (fragment instanceof DialogListener)
            listener = (DialogListener) fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_lock_progress, null);
        txtProgressMessage = (TextView) inflate.findViewById(R.id.txtProgress);

        return dialog = new AlertDialog.Builder(getActivity())
                .setView(inflate)
                .setCancelable(false)
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (listener != null) listener.onCancelDialog();
                                dismiss();
                            }
                        }
                )
                .create();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (btnNegative == null) {
            btnNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            btnNegative.setVisibility(View.GONE);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtProgressMessage.setText(R.string.msg_waitMore);
                btnNegative.setVisibility(View.VISIBLE);
            }
        }, Config.TASK_TIMEOUT_MILLIS);
    }
}
