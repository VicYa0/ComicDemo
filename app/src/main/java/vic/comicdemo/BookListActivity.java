package vic.comicdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vic.comicdemo.entity.ComicListResult;
import vic.comicdemo.model.ComicModel;

public class BookListActivity extends AppCompatActivity {

    @BindView(R.id.lv_book_list)
    ListView lvBookList;

    private Intent intent;
    private List<ComicListResult.BookListBean> dataList;
    private ComicModel model;
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
    }

    @OnClick(R.id.lv_book_list)
    public void onClick() {
    }
}
