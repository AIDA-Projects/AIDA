package me.zhenxin.aida.adapter;

import android.content.Context;
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
import me.zhenxin.aida.entity.HistoryProjectEntity;

/**
 * 历史项目列表适配器
 */
public class HistoryProjectAdapter extends BaseAdapter implements Filterable, View.OnClickListener {

    /**
     * 当前显示的列表数据
     */
    private List<HistoryProjectEntity> list;
    /**
     * 原始列表数据
     */
    private List<HistoryProjectEntity> originList;
    /**
     * 上下文对象
     */
    private Context ctx;
    /**
     * 过滤器
     */
    private HistoryProjectFilter mFilter;

    /**
     * 子项点击事件
     */
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View v);
    }

    /**
     * 构造
     *
     * @param list 列表数据
     * @param ctx  上下文
     */
    public HistoryProjectAdapter(List<HistoryProjectEntity> list, Context ctx, OnItemClickListener listener) {
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
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.history_project_items, null);
            holder = new ViewHolder();
            holder.icon = view.findViewById(R.id.history_project_icon);
            holder.name = view.findViewById(R.id.history_project_name);
            holder.pkg = view.findViewById(R.id.history_project_pkg);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        HistoryProjectEntity entity = list.get(position);
        holder.icon.setImageDrawable(entity.getIcon());
        holder.name.setText(entity.getName());
        holder.pkg.setText(entity.getPkg());
        return view;
    }

    @Override
    public void onClick(View v) {
        mListener.onItemClick(v);
    }

    @Override
    public Filter getFilter() {
        if (null == mFilter) {
            mFilter = new HistoryProjectFilter();
        }
        return mFilter;
    }

    class HistoryProjectFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            List<HistoryProjectEntity> newValues = new ArrayList();
            String filterString = constraint.toString().trim()
                    .toLowerCase();

            // 如果搜索框内容为空，就恢复原始数据
            if (TextUtils.isEmpty(filterString)) {
                newValues = originList;
            } else {
                // 过滤出新数据
                for (HistoryProjectEntity entity : originList) {
                    if (entity.getName().toLowerCase().contains(filterString)) {
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
            list = (List<HistoryProjectEntity>) results.values;

            if (results.count > 0) {
                HistoryProjectAdapter.this.notifyDataSetChanged();  // 通知数据发生了改变
            } else {
                HistoryProjectAdapter.this.notifyDataSetInvalidated(); // 通知数据失效
            }
        }
    }

    static class ViewHolder {
        ImageView icon;
        TextView name;
        TextView pkg;
    }

}
