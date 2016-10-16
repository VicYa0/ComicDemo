package vic.comicdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.thinkland.sdk.android.Parameters;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vic.comicdemo.model.ComicModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.btn_comic_of_boy)
    Button btnComicOfBoy;
    @BindView(R.id.btn_comic_of_girl)
    Button btnComicOfGirl;
    @BindView(R.id.btn_comic_of_teens)
    Button btnComicOfTeens;
    @BindView(R.id.btn_comic_of_dm)
    Button btnComicOfDm;

    private ComicModel model;
    private Parameters params;
    private Intent intent;
    private static final int JSON_RESULT =1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String result ;
            switch (msg.what){
                case JSON_RESULT:
                    result = msg.obj.toString();
                    Log.i("result",result);
                    intent = new Intent(MainActivity.this,BookListActivity.class);
                    intent.putExtra("jsonResult",result);
                    startActivity(intent);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        model = new ComicModel(MainActivity.this,handler);
    }

    @OnClick({R.id.btn_comic_of_boy, R.id.btn_comic_of_girl, R.id.btn_comic_of_teens, R.id.btn_comic_of_dm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_comic_of_boy:
                params = new Parameters();
                params.add("type", btnComicOfBoy.getText().toString());
                model.getComicListJson(params);
                break;
            case R.id.btn_comic_of_girl:
                params = new Parameters();
                params.add("type",btnComicOfGirl.getText().toString());
                model.getComicListJson(params);
                break;
            case R.id.btn_comic_of_teens:
                params = new Parameters();
                params.add("type",btnComicOfTeens.getText().toString());
                model.getComicListJson(params);
                break;
            case R.id.btn_comic_of_dm:
                params = new Parameters();
                params.add("type",btnComicOfDm.getText().toString());
                model.getComicListJson(params);
                break;
        }
    }


}
