package me.zhenxin.aida

import android.os.Bundle
import me.zhenxin.aida.activity.BaseActivity

/**
 * Splash Activity
 *
 * @author ZhenXin
 * @since 2021/8/9 17:18
 * @email qgzhenxin@qq.com
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}