package vic.comicdemo.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vic.comicdemo.entity.ComicListResult;
import vic.comicdemo.utils.UrlFactory;

/**
 * Created by Vic Yao on 2016/10/14.
 */

public class ComicModel {
    private static final int API_ID = 163;
    private Handler handler;
    private Context context;
    private static final int JSON_RESULT = 1;
    private Gson gson;
    private ComicListResult clr;
    private List<ComicListResult> listResults;
    private List<ComicListResult.BookListBean> bookListBeanList;

    public ComicModel(Context context) {
        this.context = context;
    }
    public ComicModel(Context context,Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void getComicListJson(Parameters parameters){
        Parameters params = parameters;
        JuheData.executeWithAPI(context, API_ID, UrlFactory.getComicListUrl(), JuheData.GET, params, new DataCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    JSONObject json = new JSONObject(s);
                    if (json.getInt("error_code")==200){
                        String result = (json.getJSONObject("result")).toString();
                        sendJsonResult(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {

            }
        });
    }

    public List getComicList(String s){
        gson = new Gson();
        clr =gson.fromJson(s,ComicListResult.class);
        listResults = new ArrayList<ComicListResult>();
        bookListBeanList = new ArrayList<ComicListResult.BookListBean>();
        listResults.add(clr);
        bookListBeanList = listResults.get(0).getBookList();
        return bookListBeanList;
    }

    private void  sendJsonResult(String s){
        Message msg = Message.obtain(handler);
        msg.what = JSON_RESULT;
        msg.obj = s;
        msg.sendToTarget();
    }
}