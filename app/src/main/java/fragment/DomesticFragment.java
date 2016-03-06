package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cc.exerpro.ExeApplication;
import com.cc.exerpro.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.DomesticAdapter;
import bean.DomesticNews;
import protocal.APIProtocal;
import protocal.NewsKey;

/**
 * Created by lichengcai on 2016/2/19.
 */
public class DomesticFragment extends Fragment {

    private PullToRefreshListView mList_domestic_news;
    private DomesticAdapter mAdapter;
    private static final int MSG_ID_GET_NEWS_DATA = 1;
    private static final int MSG_ID_GET_MORE_NEWS_DATA = 2;
    private int mCurrentPage = 1;
    private final int PAGES = 20;

    private DomesticHandler mHandler = new DomesticHandler(this);


    public static class DomesticHandler extends Handler {
        private WeakReference<DomesticFragment> ref;
        private DomesticFragment frg;

        public DomesticHandler(DomesticFragment domesticFragment) {
            ref = new WeakReference<DomesticFragment>(domesticFragment);
            frg = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_ID_GET_NEWS_DATA:
                    if (frg.mList_domestic_news.isRefreshing()) {
                        frg.mList_domestic_news.onRefreshComplete();
                    }
                    ArrayList<DomesticNews> arrayList = (ArrayList<DomesticNews>) msg.obj;
                    if (arrayList.size() < frg.PAGES) {
                        frg.mList_domestic_news.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                    }
                    if (frg.mAdapter == null) {
                        frg.mAdapter = new DomesticAdapter(frg.getActivity(),arrayList);
                        frg.mList_domestic_news.getRefreshableView().setAdapter(frg.mAdapter);
                    }else {
                        frg.mAdapter.refreshData(arrayList);
                    }
                    break;
                case MSG_ID_GET_MORE_NEWS_DATA:
                    if (frg.mList_domestic_news.isRefreshing()) {
                        frg.mList_domestic_news.onRefreshComplete();
                    }
                    ArrayList<DomesticNews> array_more = (ArrayList<DomesticNews>) msg.obj;
                    if (array_more.size() < frg.PAGES) {
                        frg.mList_domestic_news.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                    }
                    frg.mAdapter.loadMore(array_more);
                    break;
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.from(getActivity()).inflate(R.layout.fragment_domestic,null);
        mList_domestic_news = (PullToRefreshListView) view.findViewById(R.id.list_pull_domestic_news);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
       getNewsData(false,false);
        mList_domestic_news.setMode(PullToRefreshBase.Mode.BOTH);
        mList_domestic_news.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getNewsData(true,false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                mCurrentPage++;
                getNewsData(false,true);
            }
        });
    }

    private void getNewsData(final boolean isRefresh, final boolean isLoadMore) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, APIProtocal.DOMESTIC_NEWS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("onResponse---", "json" + s);
                ArrayList<DomesticNews> arrayList = (ArrayList<DomesticNews>) DomesticNews.getArray(s);
                if (isLoadMore) {
                    mHandler.obtainMessage(MSG_ID_GET_MORE_NEWS_DATA,arrayList).sendToTarget();
                }else {

                    mHandler.obtainMessage(MSG_ID_GET_NEWS_DATA,arrayList).sendToTarget();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("onErrorResponse","--" + volleyError);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> paramMap = new HashMap<>();
                paramMap.put("key", NewsKey.DOMESTIC_KEY);
                if (isRefresh) {
                    mCurrentPage = 1;
                }
                paramMap.put("page",String.valueOf(mCurrentPage));
                paramMap.put("rows",String.valueOf(PAGES));
                return paramMap;
            }
        };

        ExeApplication.getQueue().add(stringRequest);

    }
}
