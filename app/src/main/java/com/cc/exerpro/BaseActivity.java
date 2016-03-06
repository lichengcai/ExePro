package com.cc.exerpro;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by lichengcai on 2016/2/19.
 */
public class BaseActivity extends Activity {
    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }
}
