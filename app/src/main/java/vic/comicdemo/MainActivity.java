package vic.comicdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.thinkland.sdk.android.Parameters;

import butterknife.BindView;
import butterknife.ButterKnife;
import vic.comicdemo.model.ComicModel;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.test)
    TextView mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ComicModel model = new ComicModel();
        Parameters params = new Parameters();
        model.getComicList(this,params);
    }
}
