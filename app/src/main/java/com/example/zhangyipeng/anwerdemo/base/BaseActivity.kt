package com.example.zhangyipeng.anwerdemo.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.zhangyipeng.anwerdemo.R

open class BaseActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 修改标题

     * @param title 标题名
     */
    protected fun setTextTitle(title: String) {
        (findViewById(R.id.tv_toolsbar_title) as TextView).text = title
    }

    /**
     * 修改左按钮

     * @param flag 是否显示
     */
    protected fun setLeftBtn(flag: Boolean) = if (flag) {
        (findViewById(R.id.tv_toolsbar_left))!!.visibility = View.VISIBLE
        (findViewById(R.id.tv_toolsbar_left))!!.setOnClickListener {
            hideSoftInput()
            finish()
        }
    } else {
        (findViewById(R.id.tv_toolsbar_left))!!.visibility = View.GONE
    }

    /**
     * 修改右按钮(文字按钮)

     * @param flag     是否显示
     * *
     * @param string   按钮名
     * *
     * @param listener 点击事件
     */
    protected fun setRightBtn(flag: Boolean, string: String, listener: View.OnClickListener) {
        if (flag) {
            (findViewById(R.id.tv_toolsbar_right) as TextView).text = string
            (findViewById(R.id.tv_toolsbar_right) as TextView).visibility = View.VISIBLE
            (findViewById(R.id.tv_toolsbar_right) as TextView).setOnClickListener(listener)
        } else {
            (findViewById(R.id.tv_toolsbar_right) as TextView).visibility = View.GONE
        }
    }


    /**
     * 设置右上角图片按钮

     * @param isShow
     * *
     * @param resId
     * *
     * @param onClickListener
     */
    protected fun setRightButton(isShow: Boolean, resId: Int, onClickListener: View.OnClickListener) {
        val iv_right = findViewById(R.id.iv_right) as ImageView
        if (iv_right != null) {
            if (isShow) {
                iv_right!!.visibility = View.VISIBLE
                iv_right!!.setImageResource(resId)
                iv_right!!.setOnClickListener(onClickListener)
            } else {
                iv_right!!.visibility = View.GONE
            }
        }
    }

    /**
     * 修改右按钮(纯图按钮)

     * @param flag     是否显示
     * *
     * @param resId    按钮图
     * *
     * @param listener 点击事件
     */
    protected fun setRightBtn(flag: Boolean, resId: Int, listener: View.OnClickListener) {
        if (flag) {
            (findViewById(R.id.tv_toolsbar_right) as TextView).text = ""
            val layoutParams = (findViewById(R.id.tv_toolsbar_right) as TextView).layoutParams as LinearLayout.LayoutParams
            layoutParams.height = layoutParams.height - dip2px(28f)
            layoutParams.width = layoutParams.height
            layoutParams.gravity = Gravity.CENTER_VERTICAL
            layoutParams.setMargins(0, 0, dip2px(12f), 0)
            (findViewById(R.id.tv_toolsbar_right) as TextView).layoutParams = layoutParams
            (findViewById(R.id.tv_toolsbar_right) as TextView).setBackgroundResource(resId)
            (findViewById(R.id.tv_toolsbar_right) as TextView).visibility = View.VISIBLE
            (findViewById(R.id.tv_toolsbar_right) as TextView).setOnClickListener(listener)
        } else {
            (findViewById(R.id.tv_toolsbar_right) as TextView).visibility = View.GONE
        }
    }




    /**
     * 收起键盘
     */
    protected fun hideSoftInput() {
        try {
            val mInputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mInputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 返回当前程序版本名
     */
    fun getAppVersionName(): String {
        var versionName: String? = ""
        try {
            // ---get the package info---
            val pm = packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            versionName = pi.versionName
            if (versionName == null || versionName.isEmpty()) {
                return ""
            }
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return versionName!!
    }

    /**
     * 返回当前程序版本
     */
    fun getAppVersionCode(): String {
        var versionId = 0
        try {
            // ---get the package info---
            val pm = packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            versionId = pi.versionCode
            if (versionId <= 0) {
                return ""
            }
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return "$versionId"
    }


}
