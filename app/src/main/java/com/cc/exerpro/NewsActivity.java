package com.cc.exerpro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import fragment.DomesticFragment;
import fragment.InternationalFragment;

/**
 * Created by lichengcai on 2016/2/19.
 */
    public class NewsActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mRadioGroup;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTrans;
    private String lastFragmentTab="";
    public static final String FG_DOMESTIC_NEWS ="fg_domestic_news";
    public static final String FG_INTERNATIONAL_NEWS = "fg_international_news";
    public static final String FG_INTEREST_NEWS = "fg_interest_news";
    public static final int TAB_DOMESTIC_NEWS = 0;
    public static final int TAB_INTERNATIONAL_NEWS =1;
    public static final int TAB_INTEREST_NEWS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        init();

    }

    private void init() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_news_bottom);
        mRadioGroup.setOnCheckedChangeListener(this);
        ((RadioButton)mRadioGroup.getChildAt(0)).setChecked(true);

        if (mFragmentManager == null) {

            mFragmentManager = getSupportFragmentManager();
        }
//        mTrans = mFragmentManager.beginTransaction();
        ((RadioButton)mRadioGroup.getChildAt(TAB_DOMESTIC_NEWS)).setChecked(true);

    }

    public void hideLastFragment(Fragment lastFragment) {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        if (mTrans == null) {
            mTrans = mFragmentManager.beginTransaction();
        }
        if (lastFragment != null) {

            mTrans.hide(lastFragment);
            mTrans.commitAllowingStateLoss();
        }
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        final Fragment lastFragment =  mFragmentManager.findFragmentByTag(lastFragmentTab);
        switch (checkedId) {
            case R.id.radio_domestic:
                Log.d("TAG","domestic---");
                hideLastFragment(lastFragment);
                lastFragmentTab = FG_DOMESTIC_NEWS;
                DomesticFragment domesticFragment = (DomesticFragment) mFragmentManager.findFragmentByTag(FG_DOMESTIC_NEWS);
                if (domesticFragment == null) {
                    domesticFragment = new DomesticFragment();
                    mTrans.add(R.id.frame_news,domesticFragment,FG_DOMESTIC_NEWS);
                }else {
                    mTrans.show(domesticFragment);
                }

//                mTrans.commitAllowingStateLoss();
                break;
            case R.id.radio_international:
                Log.d("TAG","international---");
                hideLastFragment(lastFragment);
                lastFragmentTab = FG_INTERNATIONAL_NEWS;
                InternationalFragment internationalFragment = (InternationalFragment) mFragmentManager.findFragmentByTag(FG_INTERNATIONAL_NEWS);
                if (internationalFragment == null) {
                    internationalFragment = new InternationalFragment();
                    mTrans.add(R.id.frame_news,internationalFragment,FG_INTERNATIONAL_NEWS);
                }else {
                    mTrans.show(internationalFragment);
                }
                break;
        }
    }
}
