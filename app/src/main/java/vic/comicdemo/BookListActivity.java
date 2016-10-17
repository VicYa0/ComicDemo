package vic.comicdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.thinkland.sdk.android.Parameters;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vic.comicdemo.adapter.BookListAdapter;
import vic.comicdemo.entity.ComicListResult;
import vic.comicdemo.model.ComicModel;
import vic.comicdemo.utils.UrlFactory;

public class BookListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @BindView(R.id.lv_book_list)
    ListView lvBookList;

    private List<ComicListResult.BookListBean> dataList;
    private ComicModel model;
    private BookListAdapter mAdapter;
    private ComicListResult.BookListBean bean;
    private Intent intent;
    private String comicName;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            intent = new Intent(BookListActivity.this,ChapterActivity.class);
            String result = msg.obj.toString();
            LogUtils.d(result);
            intent.putExtra("json", result);
            intent.putExtra("comicName",comicName);
            startActivity(intent);
        }
    };
    private Parameters params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        intent = getIntent();
        model = new ComicModel(this);
        dataList = new ArrayList<ComicListResult.BookListBean>();
        String json = intent.getStringExtra("jsonResult");
        dataList = model.getComicList(json);
        mAdapter = new BookListAdapter(dataList,BookListActivity.this,lvBookList);
        lvBookList.setAdapter(mAdapter);
        lvBookList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        bean = dataList.get(position);
        String name = bean.getName();
        comicName = name;
        model = new ComicModel(BookListActivity.this,handler);
        params = new Parameters();
        params.add("comicName", name);
        model.getComicListJson(params, UrlFactory.getComicChapterUrl());
    }
}
