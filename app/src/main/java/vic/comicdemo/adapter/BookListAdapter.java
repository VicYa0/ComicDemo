package vic.comicdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import vic.comicdemo.R;
import vic.comicdemo.entity.ComicListResult;
import vic.comicdemo.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/10/17.
 */

public class BookListAdapter extends BaseAdapter{
    private List<ComicListResult.BookListBean> data = new ArrayList();
    private Context context;
    private LayoutInflater inflater;
    private ComicListResult.BookListBean mBookList;
    private ImageLoader mLoader;
    private ListView mListView;

    public BookListAdapter(List data, Context context,ListView listView) {
        this.data = data;
        this.context = context;
        this.mListView = listView;
        mLoader = new ImageLoader(context ,listView);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        mBookList = data.get(i);

        if (view==null){
            holder = new ViewHolder();
            inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_book_list,null);
            holder.ivCoverImg = (ImageView) view.findViewById(R.id.iv_coverImg);
            holder.tvName = (TextView) view.findViewById(R.id.tv_name);
            holder.tvType = (TextView) view.findViewById(R.id.tv_type);
            holder.tvDes = (TextView) view.findViewById(R.id.tv_des);
            holder.tvLastUpdate = (TextView) view.findViewById(R.id.tv_last_update);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        String path = mBookList.getCoverImg();
        holder.tvName.setText(mBookList.getName());
        holder.tvType.setText(mBookList.getType()+"  "+mBookList.getArea());
        holder.tvDes.setText(mBookList.getDes());
        holder.tvLastUpdate.setText(mBookList.getLastUpdate()+"");
        Glide.with(context)
                .load(path)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.ivCoverImg);
        //mLoader.display(holder.ivCoverImg,path);
        return view;
    }

    class ViewHolder{
        ImageView ivCoverImg;
        TextView tvName;
        TextView tvType;
        TextView tvDes;
        TextView tvLastUpdate;
    }

}
