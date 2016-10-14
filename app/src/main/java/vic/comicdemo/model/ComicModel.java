package vic.comicdemo.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
    private List<ComicListResult> BeanList;
    private List<ComicListResult.BookListBean> mBookList;
    private ComicListResult mComicListResult;
    private static final int API_ID = 163;
    public void getComicList(Context context,Parameters parameters){
        Parameters params = parameters;
        JuheData.executeWithAPI(context, API_ID, UrlFactory.getComicListUrl(), JuheData.GET, params, new DataCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    JSONObject json = new JSONObject(s);
                    if (json.getInt("error_code")==200){
                        String result = (json.getJSONObject("result")).toString();
//                        Log.i("result11",result);
                        mComicListResult = new Gson().fromJson(result,ComicListResult.class);
                        BeanList = new ArrayList<ComicListResult>();
                        mBookList = new ArrayList<ComicListResult.BookListBean>();
                        BeanList.add(mComicListResult);
                        mBookList = BeanList.get(0).getBookList();
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
}
