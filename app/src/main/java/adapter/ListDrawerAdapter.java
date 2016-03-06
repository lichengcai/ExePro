package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cc.exerpro.R;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/2/19.
 */
public class ListDrawerAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mArray;

    public ListDrawerAdapter(Context mContext,ArrayList<String> mArray) {
        this.mArray = mArray;
        this.mContext = mContext;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_drawer,parent,false);
        }

        TextView text_author_name = ViewHolder.get(convertView,R.id.text_author_name);
        text_author_name.setText(mArray.get(position));
        return convertView;
    }
}
