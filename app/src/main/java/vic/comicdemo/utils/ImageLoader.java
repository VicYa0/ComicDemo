package vic.comicdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import vic.comicdemo.R;

/**
 * Created by Vic Yao on 2016/10/17.
 */

public class ImageLoader {
    private LruCache<String,Bitmap> mCache;
    public static final int HANDLER_IMAGE_LOADED = 100;
    private Thread workThread;
    private List<ImageLoadTask> tasks = new ArrayList<ImageLoadTask>();
    private Context mContext;
    private ListView mListView;
    private boolean isLoop = true;
    private Bitmap mBitmap;
    //声明Handler
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case HANDLER_IMAGE_LOADED:
                    //给imageView设置Bitmap
                    ImageLoadTask task = (ImageLoadTask) msg.obj;
                    ImageView ivAlbum = (ImageView) mListView.findViewWithTag(task.path);
                    if(ivAlbum!=null){ //找到了对应的imageView控件
                        Bitmap b = getBitmapFromCache(task.path);
                        if(b!=null){  //图片下载成功了
                            ivAlbum.setImageBitmap(b);
                        }else{ //图片下载失败了
                            ivAlbum.setImageResource(R.mipmap.ic_launcher);
                        }
                    }
                    break;
            }
        }
    };

    public ImageLoader(Context context,ListView listView) {
        int maxMemorySize = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemorySize / 8;
        mCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return super.sizeOf(key, value);
            }
        };
        workThread = new Thread(){
            /**
             * 不断轮循任务集合 从集合中获取每个任务
             * 然后执行，下载图片。
             */
            public void run() {
                while(isLoop){
                    if(!tasks.isEmpty()){ //里面有任务
                        ImageLoadTask task = tasks.remove(0);
                        LogUtils.d(task);
                        String path = task.path;
                        try {
                            mBitmap = BitmapUtils.loadBitmap(HttpUtils.get(path),120,300);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //task.bitmap = mBitmap;
                        //主线程：把bitmap设置到ImageView中
                        Message msg = Message.obtain();
                        msg.what = HANDLER_IMAGE_LOADED;
                        msg.obj = task;
                        LogUtils.d(msg.obj);
                        handler.sendMessage(msg);

                    }else{ //里面没有任务   线程等待
                        try {
                            synchronized (workThread) {
                                workThread.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        workThread.start();
    }

    public void addBitmapToCache(String url, Bitmap bitmap){
        if (getBitmapFromCache(url)==null){
            mCache.put(url,bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String url){
        return mCache.get(url);
    }

    //封装图片加载任务
    class ImageLoadTask{
        String path;			//图片地址
        //Bitmap bitmap;	//根据地址下载到的图片
    }

    public void display(ImageView ivAlbum, String path){
        if(getBitmapFromCache(path)!=null){ //以前存过
            mBitmap = getBitmapFromCache(path);
            if(mBitmap!=null){ //bitmap还没有被销毁
                //Log.i("info", "从内存缓存中找到的图片..");
                ivAlbum.setImageBitmap(mBitmap);
                return;
            }
        }
/*        //去文件缓存中寻找是否有图片
        String filename = path.substring(path.lastIndexOf("/"));
        File file = new File(context.getCacheDir(), "images"+filename);
        Bitmap b=BitmapUtils.loadBitmap(file);
        if(b != null){
            //Log.i("info", "从文件缓存中找到的图片..");
            ivAlbum.setImageBitmap(b);
            //把从文件中读取的bitmap 存入内存缓存
            cache.put(path, new SoftReference<Bitmap>(b));
            return;
        }*/
        //创建ImageLoadTask对象 添加到任务集合中
        ImageLoadTask task = new ImageLoadTask();
        task.path = path;
        LogUtils.d(path);
        tasks.add(task);
        //唤醒工作线程  起来干活
        synchronized (workThread) {
            workThread.notify();
        }
    }

}
