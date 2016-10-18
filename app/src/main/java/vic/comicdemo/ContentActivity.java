package vic.comicdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vic.comicdemo.adapter.BookContentAdapter;
import vic.comicdemo.adapter.BookListAdapter;
import vic.comicdemo.entity.ComicContent;
import vic.comicdemo.model.ComicModel;

public class ContentActivity extends AppCompatActivity {

    @BindView(R.id.lv_content)
    ListView mLvContent;
    private Intent intent;
    private ComicModel model;
    private List<ComicContent.ImageListBean> dataList;
    private BookContentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        intent = getIntent();
        model = new ComicModel(this);
        dataList = new ArrayList<ComicContent.ImageListBean>();
        String json = intent.getStringExtra("json");
        dataList = model.getContentList(json);
        mAdapter = new BookContentAdapter(dataList,this);
        mLvContent.setAdapter(mAdapter);
    }
}
