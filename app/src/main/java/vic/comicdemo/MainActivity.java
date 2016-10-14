package vic.comicdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

import butterknife.BindView;
import butterknife.ButterKnife;
import vic.comicdemo.utils.UrlFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.test)
    TextView mTest;
    private final String NULL_PARAM ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Parameters params = new Parameters();
//        params.add("name",null);
//        params.add("type",null);
//        params.add("skip",0);
//        params.add("finish",null);
        JuheData.executeWithAPI(this, 163, UrlFactory.getComicListUrl(), JuheData.GET, params, new DataCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                mTest.setText(s);
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {

            }
        });
        //mTest.setText();
    }
}
