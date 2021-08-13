package me.zhenxin.aida.activity

import com.xuexiang.xui.widget.activity.BaseSplashActivity
import com.xuexiang.xutil.app.ActivityUtils
import me.zhenxin.aida.R

/**
 * 欢迎页 Activity
 *
 * @author ZhenXin
 * @email qgzhenxin@qq.com
 * @since 2021/8/9 17:18
 */
class SplashActivity : BaseSplashActivity() {
    override fun onCreateActivity() {
        initSplashView(R.drawable.xui_config_bg_splash)
        startSplash(false)
    }

    public override fun onSplashFinished() {
        ActivityUtils.startActivity(MainActivity::class.java)
        finish()
    }

    override fun getSplashDurationMillis(): Long {
        return 500
    }
}