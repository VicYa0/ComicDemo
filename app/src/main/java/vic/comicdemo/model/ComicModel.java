package vic.comicdemo.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vic.comicdemo.entity.ChapterResult;
import vic.comicdemo.entity.ComicContent;
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
    private ChapterResult cr;
    private ComicContent cc;
    private List jsonResult;
    private List beanResultList;


    public ComicModel(Context context) {
        this.context = context;
    }
    public ComicModel(Context context,Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void getComicListJson(Parameters parameters,String url){
        Parameters params = parameters;
        JuheData.executeWithAPI(context, API_ID, url, JuheData.GET, params, new DataCallBack() {
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
        jsonResult = new ArrayList<ComicListResult>();
        beanResultList = new ArrayList<ComicListResult.BookListBean>();
        jsonResult.add(clr);
        beanResultList = clr.getBookList();
        return beanResultList;
    }

    public List getChapterList(String s){
        gson = new Gson();
        cr =gson.fromJson(s,ChapterResult.class);
        jsonResult = new ArrayList();
        beanResultList = new ArrayList();
        jsonResult.add(cr);
        beanResultList = cr.getChapterList();
        return beanResultList;
    }

    public List getContentList(String s){
        gson = new Gson();
        cc =gson.fromJson(s,ComicContent.class);
        LogUtils.d(cc);
        jsonResult = new ArrayList();
        beanResultList = new ArrayList();
        jsonResult.add(cc);
        beanResultList = cc.getImageList();
        return beanResultList;
    }

    private void  sendJsonResult(String s){
        Message msg = Message.obtain(handler);
        msg.what = JSON_RESULT;
        msg.obj = s;
        msg.sendToTarget();
    }
}