package vic.comicdemo.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/16.
 */

public class GsonUtils {
    private List list = new ArrayList();
    private Gson gson = new Gson();
    public List getDataFromGson(String s,Class c){
        list.add(gson.fromJson(s,c));
        return list;
    }
}
