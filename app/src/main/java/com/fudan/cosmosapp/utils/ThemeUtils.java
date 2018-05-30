package com.fudan.cosmosapp.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

import com.fudan.cosmosapp.R;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class ThemeUtils {


    private static int sTheme = 1;

    public final static int THEME_DEFAULT = 1;
    public final static int THEME_BLACK = 2;
    public final static int THEME_BLUE = 3;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity
     * of the same type.
     */
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
//        overridePendingTransition(R.anim.start_anim, R.anim.out_anim);
        Intent intent = new Intent(activity, activity.getClass());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            ActivityOptions options = ActivityOptions
                    .makeCustomAnimation(activity, R.anim.fade_in, R.anim.fade_out);
            activity.startActivity(intent, options.toBundle());
        }else {
            activity.startActivity(intent);
        }


    }

    /**
     * Set the theme of the activity, according to the configuration.
     */
    public static void onActivityCreateSetTheme(Context activity) {
        switch (sTheme) {
            default:
            case 1:
                activity.setTheme(R.style.MyTheme);
                break;
            case 2:
                activity.setTheme(R.style.BlackTheme);
                break;
            case 3:
                activity.setTheme(R.style.BlueTheme);
                break;
        }
    }

    public static void setsTheme(int sTheme) {
        ThemeUtils.sTheme = sTheme;
    }
}
