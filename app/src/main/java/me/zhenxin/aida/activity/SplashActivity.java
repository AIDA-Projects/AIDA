package me.zhenxin.aida.activity;

import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.activity.BaseSplashActivity;
import com.xuexiang.xutil.app.ActivityUtils;

import me.zhenxin.aida.R;

/**
 * 欢迎页 Activity
 *
 * @author ZhenXin
 * @email qgzhenxin@qq.com
 * @since 2021/8/9 17:18
 */
public class SplashActivity extends BaseSplashActivity {
    @Override
    protected void onCreateActivity() {
        initSplashView(R.drawable.xui_config_bg_splash);
        startSplash(false);
    }

    @Override
    public void onSplashFinished() {
        ActivityUtils.startActivity(MainActivity.class);
    }

    @Override
    protected long getSplashDurationMillis() {
        return 500;
    }
}