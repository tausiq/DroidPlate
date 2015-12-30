package com.genericslab.droidplate.helper.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;

import com.genericslab.droidplate.R;

/**
 * Created by shahab on 12/30/15.
 */
public class FontHelper {

    enum Fonts {
        NOTO_SANS_BOLD(R.string.fonts_noto_sans_bold),
        NOTO_SANS_ITALIC(R.string.fonts_noto_sans_italic),
        NOTO_SANS_REGULAR(R.string.fonts_noto_sans_regular);

        int resFont;

        Fonts(int resFont) {
            this.resFont = resFont;
        }

        public int getFont() {
            return resFont;
        }
    }

    /**
     * Sets a font on a textview
     * @param textview
     * @param fonts
     * @param context
     */
    public static void setCustomFont(TextView textview, Fonts fonts, Context context) {
        setCustomFont(textview, fonts, context, Typeface.NORMAL);
    }

    public static void setCustomFont(TextView textview, Fonts fonts, Context context, int style) {
        if(fonts == null) {
            return;
        }

        Typeface tf = getTypeface(context, fonts);
        if(tf != null) {
            textview.setTypeface(tf, style);
        }
    }

    private static LruCache<Integer, Typeface> sTypefaceCache = new LruCache<>(12);

    public static Typeface getTypeface(Context context, Fonts fonts) {
        Typeface tf = sTypefaceCache.get(fonts.getFont());
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getApplicationContext().getAssets(), context.getString(fonts.getFont()));
            }
            catch (Exception e) {
                return null;
            }
            sTypefaceCache.put(fonts.getFont(), tf);
        }
        return tf;
    }

    public static SpannableString getFontString(Context context, CharSequence title, Fonts fonts) {
        SpannableString ret = new SpannableString(title);
        ret.setSpan(new TypefaceSpan(getTypeface(context, fonts)), 0, ret.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ret;
    }

    public static SpannableString getFontString(Context context, CharSequence title) {
        return getFontString(context, title, Fonts.NOTO_SANS_REGULAR);
    }
}
