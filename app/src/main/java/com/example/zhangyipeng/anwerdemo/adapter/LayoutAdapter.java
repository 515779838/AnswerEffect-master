package com.example.zhangyipeng.anwerdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangyipeng.anwerdemo.R;
import com.example.zhangyipeng.anwerdemo.bean.AnswerBean;

import java.util.List;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private List<AnswerBean> datas;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView tv1;
        public final TextView tv2;
        public final TextView tv3;
        public final TextView tv4;
        public final TextView tv_explain;
        public final LinearLayout ll_anwer_all_show;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
            tv4 = (TextView) view.findViewById(R.id.tv4);
            tv_explain = (TextView) view.findViewById(R.id.tv_explain);
            ll_anwer_all_show = (LinearLayout) view.findViewById(R.id.ll_anwer_all_show);
        }
    }

    public LayoutAdapter(Context context, RecyclerView recyclerView) {

        mContext = context;
        mRecyclerView = recyclerView;
    }

    public void setDataList(List<AnswerBean> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        AnswerBean subDataBean = datas.get(position);
        holder.title.setText((position+1)+"、 "+subDataBean.getQuestion());

        holder.tv1.setText(subDataBean.getAnswer1());
        holder.tv2.setText(subDataBean.getAnswer2());
        holder.tv3.setText(subDataBean.getAnswer3());
        holder.tv4.setText(subDataBean.getAnswer4());
        holder.tv_explain.setText("解析");

        holder.ll_anwer_all_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPosition;
                if (position == datas.size() - 1){
                    newPosition = position;
                }else {
                    newPosition = position +1;
                }
                lisener.onSelectClick(newPosition );
            }
        });

        final View itemView = holder.itemView;

    }
    onSelectClick lisener;
    public interface onSelectClick{
        void onSelectClick(int position);
    }
    public void setOnSelectClickLisener(onSelectClick lisener){
        this.lisener = lisener;
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }
}