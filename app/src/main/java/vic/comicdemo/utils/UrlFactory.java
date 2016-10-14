package vic.comicdemo.utils;

/**
 * Created by Vic Yao on 2016/10/14.
 * 用于管理url地址
 */

public class UrlFactory {
    public static String getComicListUrl(){
        String url = "http://japi.juhe.cn/comic/book";
        return url;
    }

    public static String getComicChapterUrl(){
        String url = "http://japi.juhe.cn/comic/chapter";
        return url;
    }
    public static String getComicContentUrl(){
        String url = "http://japi.juhe.cn/comic/chapterContent";
        return url;
    }
}
