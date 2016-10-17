package vic.comicdemo.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vic Yao on 2016/10/17.
 */

public class HttpUtils {
    public static InputStream get(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStream is = conn.getInputStream();
        return is;
    }
}
