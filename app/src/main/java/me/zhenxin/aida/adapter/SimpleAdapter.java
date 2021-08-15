package me.zhenxin.aida.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.zhenxin.aida.R;
import me.zhenxin.aida.entity.SimpleItemEntity;

public class SimpleAdapter extends BaseAdapter implements Filterable, View.OnClickListener {
    /**
     * 当前显示的列表数据
     */
    private List<SimpleItemEntity> list;
    /**
     * 原始列表数据
     */
    private List<SimpleItemEntity> originList;
    /**
     * 上下文对象
     */
    private Context ctx;
    /**
     * 过滤器
     */
    private SimpleAdapter.SimpleItemFilter mFilter;

    /**
     * 子项点击事件
     */
    private SimpleAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View v);
    }

    /**
     * 构造
     *
     * @param list 列表数据
     * @param ctx  上下文
     */
    public SimpleAdapter(List<SimpleItemEntity> list, Context ctx, SimpleAdapter.OnItemClickListener listener) {
        this.list = list;
        this.originList = list;
        this.ctx = ctx;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        SimpleAdapter.ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.simple_item, null);
            holder = new SimpleAdapter.ViewHolder();
            holder.simpleItemImg = view.findViewById(R.id.simple_item_img);
            holder.simpleItemTitle = view.findViewById(R.id.simple_item_title);
            holder.simpleItemSummary = view.findViewById(R.id.simple_item_summary);
            view.setTag(holder);
        } else {
            holder = (SimpleAdapter.ViewHolder) view.getTag();
        }
        SimpleItemEntity entity = list.get(position);
        holder.simpleItemImg.setImageDrawable((Drawable) entity.getSimpleItemImg());
        holder.simpleItemTitle.setText(entity.getSimpleItemTitle());
        holder.simpleItemSummary.setText(entity.getSimpleItemSummary());
        return view;
    }

    @Override
    public void onClick(View v) {
        mListener.onItemClick(v);
    }

    @Override
    public Filter getFilter() {
        if (null == mFilter) {
            mFilter = new SimpleAdapter.SimpleItemFilter();
        }
        return mFilter;
    }

    class SimpleItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            List<SimpleItemEntity> newValues = new ArrayList();
            String filterString = constraint.toString().trim()
                    .toLowerCase();

            // 如果搜索框内容为空，就恢复原始数据
            if (TextUtils.isEmpty(filterString)) {
                newValues = originList;
            } else {
                // 过滤出新数据
                for (SimpleItemEntity entity : originList) {
                    if (entity.getSimpleItemTitle().toLowerCase().contains(filterString)) {
                        newValues.add(entity);
                    }
                }
            }

            results.values = newValues;
            results.count = newValues.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list = (List<SimpleItemEntity>) results.values;

            if (results.count > 0) {
                SimpleAdapter.this.notifyDataSetChanged();  // 通知数据发生了改变
            } else {
                SimpleAdapter.this.notifyDataSetInvalidated(); // 通知数据失效
            }
        }
    }

    static class ViewHolder {
        ImageView simpleItemImg;
        TextView simpleItemTitle;
        TextView simpleItemSummary;
    }
}
