package com.cc.exerpro;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by lichengcai on 2016/2/19.
 */
public class ExeApplication extends Application {

    public static RequestQueue mQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        mQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getQueue() {
        return mQueue;
    }

}
