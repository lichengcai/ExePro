package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.exerpro.ExeApplication;
import com.cc.exerpro.R;

import java.util.ArrayList;

import bean.DomesticNews;
import util.MyImageLoader;

/**
 * Created by lichengcai on 2016/2/19.
 */
public class DomesticAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<DomesticNews> mArray;

    public DomesticAdapter(Context mContext,ArrayList<DomesticNews> mArray) {
        this.mContext = mContext;
        this.mArray = mArray;
    }
    @Override
    public int getCount() {
        return mArray.size();
    }

    @Override
    public Object getItem(int position) {
        return mArray.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_domestic_news,parent,false);
        }
        TextView text_title = ViewHolder.get(convertView,R.id.text_domestic_title);
        TextView text_time = ViewHolder.get(convertView,R.id.text_domestic_time);
        final ImageView img_domestic = ViewHolder.get(convertView, R.id.img_domestic);

        text_title.setText(mArray.get(position).getTitle());
        text_time.setText(mArray.get(position).getCtime());

        Log.d("TAG", "img_loader---" + mArray.get(position).getPicUrl());
        MyImageLoader.getImage(ExeApplication.getQueue(),img_domestic,mArray.get(position).getPicUrl());
        return convertView;
    }
    public void refreshData(ArrayList<DomesticNews> arrayList) {
        mArray.clear();
        mArray.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void loadMore(ArrayList<DomesticNews> arrayList) {
        mArray.addAll(arrayList);
        notifyDataSetChanged();
    }
}
