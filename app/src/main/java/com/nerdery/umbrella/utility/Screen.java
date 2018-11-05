package com.nerdery.umbrella.utility;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Screen {
    public static int getWidth(Context context) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);

        return Math.round(displayMetrics.widthPixels);
    }
}
