package com.genericslab.droidplate.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by shahab on 2/12/16.
 */
public class NetworkUtils {

    public static int TYPE_WIFI = ConnectivityManager.TYPE_WIFI;
    public static int TYPE_MOBILE = ConnectivityManager.TYPE_MOBILE;
    public static int TYPE_OTHERS = 100;
    public static int TYPE_NOT_CONNECTED = -1;

    public static int getNetworkConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = connMgr.getActiveNetworkInfo();

        if (nInfo != null && nInfo.isConnected()) {
            if (nInfo.getType() == ConnectivityManager.TYPE_WIFI) return TYPE_WIFI;
            else if (nInfo.getType() == ConnectivityManager.TYPE_MOBILE) return TYPE_MOBILE;

            // at least its connected
            return TYPE_OTHERS;
        }

        return TYPE_NOT_CONNECTED;
    }

    public static boolean isConnected(Context context) {
        return getNetworkConnection(context) != TYPE_NOT_CONNECTED;
    }
}
