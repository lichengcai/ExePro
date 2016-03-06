package util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.cc.exerpro.R;

/**
 * Created by lichengcai on 2016/3/3.
 */
public class MyImageLoader {

    public static void getImage(RequestQueue requestQueue,ImageView imageView,String url) {
        ImageLoader imageLoader = new ImageLoader(requestQueue,new MyImageCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        imageLoader.get(url,listener);
    }

    static class MyImageCache implements ImageLoader.ImageCache {
        private LruCache<String,Bitmap> mCache;
        public MyImageCache(){
            int maxSize = 1024*1024*10;
            mCache = new LruCache<String,Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String s) {
            return mCache.get(s);
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            mCache.put(s,bitmap);
        }
    }


}
