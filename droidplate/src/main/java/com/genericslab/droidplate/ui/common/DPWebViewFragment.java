package com.genericslab.droidplate.ui.common;


import android.support.v4.app.Fragment;
import android.webkit.WebView;

import com.genericslab.droidplate.CoreFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(resName = "dp_fragment_webview")
public class DPWebViewFragment extends CoreFragment {

    @ViewById
    WebView webView;

    @FragmentArg
    String url;


    public DPWebViewFragment() {
        // Required empty public constructor
    }

    @AfterViews
    void afterViews() {
        if (url == null || url.isEmpty()) {
            onError("Can not load empty url. Please go back and try again later.");
        } else {
            webView.loadUrl(url);
        }
    }

    void onError(String description) {
        webView.stopLoading();
        webView.loadData(description, "text/html", "utf-8");
    }

}
