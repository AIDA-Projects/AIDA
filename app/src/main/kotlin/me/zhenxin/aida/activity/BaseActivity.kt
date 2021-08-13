package me.zhenxin.aida.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * 基本 Activity
 *
 * @author 真心
 * @email qgzhenxin@qq.com
 * @since 2021/8/13 19:50
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun setOnClick(views: MutableList<View>, listener: View.OnClickListener) {
        views.forEach {
            it.setOnClickListener(listener)
        }
    }

}