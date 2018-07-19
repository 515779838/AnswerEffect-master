package com.example.zhangyipeng.anwerdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhangyipeng.anwerdemo.R;

public class GridViewRadioAdapter extends BaseAdapter {
    private Context mContext;
    private int lastPosition = -1;            //记录上一次选中的图片位置，默认不选中

    public GridViewRadioAdapter(Context mContext) {
        this.mContext = mContext;
    }

    private int num;

    public void setDataNum(int num) {
        this.num = num;
        notifyDataSetChanged();
    }

    public void setSelection(int position) {   //在activity中GridView的onItemClickListener中调用此方法，来设置选中位置
        lastPosition = position;
    }

    @Override
    public int getCount() {
        return num;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_topic, null);
            viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_id.setText((position + 1) + "");
//
        return convertView;
    }

    class ViewHolder{
        private TextView tv_id;
    }
}
