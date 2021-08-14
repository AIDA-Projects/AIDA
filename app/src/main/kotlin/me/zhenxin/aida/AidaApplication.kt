package me.zhenxin.aida

import android.app.Application
import com.xuexiang.xrouter.launcher.XRouter


/**
 * Aida Application
 *
 * @author 真心
 * @email qgzhenxin@qq.com
 * @since 2021/8/14 22:01
 */
class AidaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initXRouter()
    }

    private fun initXRouter() {
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            XRouter.openLog() // 打印日志
            XRouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        XRouter.init(this)
    }

    private fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}