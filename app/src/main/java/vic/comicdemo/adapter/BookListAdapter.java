package vic.comicdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vic.comicdemo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/10/17.
 */

public class BookListAdapter extends BaseAdapter{
    private List data = new ArrayList();
    private Context context;
    private LayoutInflater inflater;

    public BookListAdapter(List data, Context context) {
        this.data = data;
        this.context = context;
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
        if (view==null){
            holder = new ViewHolder();
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_book_list,null);
            holder.ivCoverImg = (ImageView) view.findViewById(R.id.iv_coverImg);
            holder.tvName = (TextView) view.findViewById(R.id.tv_name);
            holder.tvType = (TextView) view.findViewById(R.id.tv_type);
            holder.tvDef = (TextView) view.findViewById(R.id.tv_def);
            holder.tvLastUpdate = (TextView) view.findViewById(R.id.tv_last_update);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        return null;
    }

    class ViewHolder{
        ImageView ivCoverImg;
        TextView tvName;
        TextView tvType;
        TextView tvDef;
        TextView tvLastUpdate;
    }
}
