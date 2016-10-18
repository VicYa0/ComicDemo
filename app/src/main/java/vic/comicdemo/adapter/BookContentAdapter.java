package vic.comicdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.prefill.PreFillType;

import java.util.ArrayList;
import java.util.List;

import vic.comicdemo.R;
import vic.comicdemo.entity.ComicContent;

/**
 * Created by Vic Yao on 2016/10/17.
 */

public class BookContentAdapter extends BaseAdapter {
    private List<ComicContent.ImageListBean> data = new ArrayList();
    private Context context;
    private LayoutInflater inflater;
    private ComicContent.ImageListBean mImageList;
    private ListView mListView;
    private GlideBuilder mBuilder;

    public BookContentAdapter(List<ComicContent.ImageListBean> data, Context context) {
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        MemorySizeCalculator memorySizeCalculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = memorySizeCalculator.getMemoryCacheSize();
        int defalutBitmapPoolSize = memorySizeCalculator.getBitmapPoolSize();
        mBuilder = new GlideBuilder(context);
        mBuilder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize*4));
        mBuilder.setBitmapPool(new LruBitmapPool(defalutBitmapPoolSize*4));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        mImageList = data.get(position);
        if (convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_book_content,null);
            holder.ivContent = (ImageView) convertView.findViewById(R.id.iv_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        String path = mImageList.getImageUrl();
        Glide.with(context)
                .load(path)
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.ivContent);

        return convertView;
    }

    class ViewHolder{
        ImageView ivContent;
    }
}
