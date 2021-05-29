package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;

import java.util.ArrayList;

public class ListViewAdapter implements ListAdapter {
    private ArrayList<String> listData;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<String> listData) {
        this.listData = listData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        if (listData != null && !listData.isEmpty()) {
            return listData.size();
        }
        return 0;
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
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.row_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.layout = (LinearLayout) rowView.findViewById(R.id.layout);
            viewHolder.textItem = (TextView) rowView.findViewById(R.id.text_item);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.textItem.setText(listData.get(position));
//        if (position % 2 == 0) {
//            holder.layout.setBackgroundColor(Color.CYAN);
//        } else {
//            holder.layout.setBackgroundColor(Color.GREEN);
//        }

        return rowView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return listData.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private class ViewHolder {
        public LinearLayout layout;
        public TextView textItem;
    }
}
