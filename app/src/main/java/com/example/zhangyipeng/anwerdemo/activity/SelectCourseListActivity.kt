package com.example.zhangyipeng.anwerdemo.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView

import com.example.zhangyipeng.anwerdemo.R
import com.example.zhangyipeng.anwerdemo.adapter.GridViewRadioAdapter
import com.example.zhangyipeng.anwerdemo.base.BaseActivity

class SelectCourseListActivity : BaseActivity() {

    companion object {
        var callBack: SelectDictionaryCallBack? = null
    }

    fun selectPos(activity: Activity, title: String,  count: Int, callBack: SelectDictionaryCallBack) {
        SelectCourseListActivity.callBack = callBack
        val intent = Intent(activity, SelectCourseListActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("count", count)
        activity.startActivity(intent)
    }

    interface SelectDictionaryCallBack {
        fun getData(pos: Int)
    }


    private var adapter: GridViewRadioAdapter? = null
    private var gridView: GridView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)

        setLeftBtn(true)
        setTextTitle(""+intent.getStringExtra("title"))

        val count = intent.getIntExtra("count", 0)

        adapter = GridViewRadioAdapter(this)

        adapter!!.setDataNum(count)

        gridView = findViewById(R.id.gridView) as GridView?

        gridView!!.adapter = adapter

        gridView!!.onItemClickListener = AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
            callBack!!.getData(position)
            adapter!!.setSelection(position)  //传值更新
            adapter!!.notifyDataSetChanged()  //每一次点击通知adapter重新渲染

            finish()
        }
    }
}
