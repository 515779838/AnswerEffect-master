package com.example.zhangyipeng.anwerdemo.activity;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.zhangyipeng.anwerdemo.R;
import com.example.zhangyipeng.anwerdemo.adapter.LayoutAdapter;
import com.example.zhangyipeng.anwerdemo.base.BaseActivity;
import com.example.zhangyipeng.anwerdemo.bean.AnswerBean;
import com.example.zhangyipeng.anwerdemo.tools.DBManager;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.io.File;
import java.util.ArrayList;

import static com.example.zhangyipeng.anwerdemo.tools.DBManager.DB_NAME;

public class AnswerReadActivity extends BaseActivity {

    private ProgressDialog progressDialog;

    private RecyclerViewPager mRecyclerView;
    private LayoutAdapter layoutAdapter;

    private ArrayList<AnswerBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anwers_read);

        setTextTitle("" + getString(R.string.app_name));

        setRightButton(true, R.mipmap.cardlist, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SelectCourseListActivity().selectPos(AnswerReadActivity.this, "" + getString(R.string.app_name), datas.size(), new SelectCourseListActivity.SelectDictionaryCallBack() {
                    @Override
                    public void getData(int pos) {
                         mRecyclerView.scrollToPosition(pos);

                    }
                });
            }
        });

        initViewPager();

        new MyTask().execute();

    }

    private void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(AnswerReadActivity.this);
            progressDialog.setMessage("加载中...");
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void dismissDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(AnswerReadActivity.this);
        }
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog();
        }

        @Override
        protected String doInBackground(String... strings) {
            String dbPath = Environment.getExternalStorageDirectory() + "/databases/" + DB_NAME;
            boolean flag = new File(dbPath).delete();
            DBManager dbManager = new DBManager(AnswerReadActivity.this);
            SQLiteDatabase sqLiteDatabase = dbManager.DBManager();

            datas = dbManager.query(sqLiteDatabase);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dismissDialog();
            if (layoutAdapter != null) {

                layoutAdapter.setDataList(datas);
                layoutAdapter.setOnSelectClickLisener(new LayoutAdapter.onSelectClick() {
                    @Override
                    public void onSelectClick(int position) {
                        mRecyclerView.smoothScrollToPosition(position);
                        Log.e("zj","position = "+position);
                    }
                });
            }
        }
    }



    protected void initViewPager() {
        mRecyclerView = (RecyclerViewPager) findViewById(R.id.viewpager);
        LinearLayoutManager layout  = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setSinglePageFling(true);
        mRecyclerView.setFlingFactor(0.1f);
        mRecyclerView.setTriggerOffset(0.1f);
        layoutAdapter = new LayoutAdapter(this, mRecyclerView);
        mRecyclerView.setAdapter(layoutAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
//                updateState(scrollState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {

            }
        });

        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
//                mRecyclerView.scrollToPosition(newPosition);

                Log.d("test", "oldPosition:" + oldPosition + " newPosition:" + newPosition);
//                recyclerView.scrollToPosition(newPosition);
//
//                topicAdapter.notifyCurPosition(newPosition);
//                topicAdapter.notifyPrePosition(oldPosition);
//
//                Log.i("DDD",newPosition+"");

            }
        });



        mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {


            }
        });
    }
}
