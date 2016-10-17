package vic.comicdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

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

    public BookContentAdapter(List<ComicContent.ImageListBean> data, Context context) {
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
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
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.ivContent);
        return convertView;
    }

    class ViewHolder{
        ImageView ivContent;
    }
}
