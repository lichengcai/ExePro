package com.cc.exerpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.ListDrawerAdapter;

public class MainActivity extends BaseActivity {

    private ListView mList_drawer;
    private ArrayList<String> mArray_string = new ArrayList<String>();
    private ListDrawerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mList_drawer = (ListView) findViewById(R.id.list_drawer);
        mAdapter = new ListDrawerAdapter(mContext,mArray_string);
        initList();
        mList_drawer.setAdapter(mAdapter);
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");
        mList_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(mContext,NewsActivity.class));
                }
                if (position == 1) {
                    startActivity(new Intent(mContext, TestActivity.class));
                }


            }
        });
    }


    private void initList() {
        for (int i=0; i<5; i++) {
            mArray_string.add("ITEM---" + i);
        }
    }
}
