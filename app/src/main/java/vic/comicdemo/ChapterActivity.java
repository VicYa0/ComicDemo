package vic.comicdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vic.comicdemo.entity.ChapterResult;
import vic.comicdemo.model.ComicModel;
import vic.comicdemo.utils.UrlFactory;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ChapterActivity extends AppCompatActivity implements AbsListView.OnScrollListener{
    @BindView(R.id.lv_chapter)
    ListView mLvChapter;

    private Intent intent;
    private List<ChapterResult.ChapterListBean> beanList;
    private List<ChapterResult.ChapterListBean> tempList;
    private Parameters params;
    private ComicModel model;
    private List chapterName;
    private String comicName;
    private ChapterResult.ChapterListBean bean;
    private static final int JSON_RESULT = 1;
    private static final int UPDATE_LIST = 2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case JSON_RESULT:
                    String result = msg.obj.toString();
                    intent = new Intent(ChapterActivity.this,ContentActivity.class);
                    intent.putExtra("json",result);
                    startActivity(intent);
                    break;
                case UPDATE_LIST:
                    String s = msg.obj.toString();
                    tempList = new ArrayList<>();
                    tempList = model.getChapterList(s);
                    if (tempList.isEmpty()){
                        Toast.makeText(ChapterActivity.this,"到底了 >_<......",Toast.LENGTH_SHORT).show();
                    }else {
                        beanList.addAll(tempList);
                        for (int i = 0;i < tempList.size();i++){
                            chapterName.add(tempList.get(i).getName());
                            mAdapter.notifyDataSetChanged();
                            requesting = false;
                        }
                    }

                    break;
            }
        }
    };

    private boolean isBottom = false;
    private boolean requesting = false;
    private int skip = 0;

    private CustomAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        intent = getIntent();
        model = new ComicModel(this);
        String json = intent.getStringExtra("json");
        comicName = intent.getStringExtra("comicName");
        beanList = new ArrayList<>();
        chapterName = new ArrayList();
        beanList = model.getChapterList(json);
        for (int i = 0;i < beanList.size();i++){
            chapterName.add(beanList.get(i).getName());
        }
        mAdapter = new CustomAdapter(this,android.R.layout.simple_list_item_1,chapterName);
        mLvChapter.setAdapter(mAdapter);
        setListviewListener();
    }

    private class CustomAdapter extends ArrayAdapter{

        public CustomAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }
    }

    private void setListviewListener(){
        mLvChapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bean = beanList.get(position);
                int chapterId = bean.getId();
                model = new ComicModel(ChapterActivity.this,handler);
                params = new Parameters();
                params.add("comicName",comicName);
                params.add("id",chapterId);
                model.getComicListJson(params, UrlFactory.getComicContentUrl());
            }
        });
        mLvChapter.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case OnScrollListener.SCROLL_STATE_IDLE:
                if(isBottom && !requesting){
                    //加载下一页数据
                    requesting = true;
                    params = new Parameters();
                    params.add("comicName",comicName);
                    skip = skip + 20;
                    params.add("skip",skip);
                    JuheData.executeWithAPI(this, 163, UrlFactory.getComicChapterUrl(), JuheData.GET, params, new DataCallBack() {
                        @Override
                        public void onSuccess(int i, String s) {
                            JSONObject json = null;
                            try {
                                json = new JSONObject(s);
                                String result = (json.getJSONObject("result")).toString();
                                Message msg = Message.obtain(handler);
                                msg.what = UPDATE_LIST;
                                msg.obj = result;
                                msg.sendToTarget();
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
                break;
            case OnScrollListener.SCROLL_STATE_FLING:
                //Log.i("info", "SCROLL_STATE_FLING");
                break;
            case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                //Log.i("info", "SCROLL_STATE_TOUCH_SCROLL");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem + visibleItemCount == totalItemCount){
            isBottom = true;
        }else{
            isBottom = false;
        }
    }
}
