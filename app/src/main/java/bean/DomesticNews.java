package bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/2/19.
 */
public class DomesticNews {
    private String ctime;
    private String title;
    private String picUrl;
    private String description;
    private String url;


    @Override
    public String toString() {
        return "DomesticNews{" +
                "ctime='" + ctime + '\'' +
                ", title='" + title + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Object getArray(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Gson gson = new Gson();
            ArrayList<DomesticNews> array = gson.fromJson(jsonObject.getString("result"),new TypeToken<ArrayList<DomesticNews>>(){}.getType());
            return array;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
