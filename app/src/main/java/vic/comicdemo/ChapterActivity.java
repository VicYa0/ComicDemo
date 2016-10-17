package vic.comicdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.thinkland.sdk.android.Parameters;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vic.comicdemo.entity.ChapterResult;
import vic.comicdemo.model.ComicModel;
import vic.comicdemo.utils.UrlFactory;

public class ChapterActivity extends AppCompatActivity {
    @BindView(R.id.lv_chapter)
    ListView mLvChapter;

    private Intent intent;
    private List<ChapterResult.ChapterListBean> beanList;
    private Parameters params;
    private ComicModel model;
    private List chapterName;
    private String comicName;
    private ChapterResult.ChapterListBean bean;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String result = msg.obj.toString();
            intent = new Intent(ChapterActivity.this,ContentActivity.class);
            intent.putExtra("json",result);
            startActivity(intent);
        }
    };

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
        mLvChapter.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,chapterName));
        setListviewListener();
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
                LogUtils.d(comicName);
                LogUtils.d(chapterId);
                model.getComicListJson(params, UrlFactory.getComicContentUrl());
            }
        });
    }
}
