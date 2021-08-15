package me.zhenxin.aida.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用程序工具类
 */
public class AppUtils {
    /**
     * 获取APP信息列表
     *
     * @return APP信息列表
     */
    public static List<PackageInfo> getAppInfoList(final PackageManager packageManager) {
        final List<PackageInfo> apps = packageManager.getInstalledPackages(0);
        List<PackageInfo> resultList = new ArrayList<>();
        for (PackageInfo packageInfo : apps) {
            int flags = packageInfo.applicationInfo.flags;
            // 是否包含系统应用
            if (!((flags & ApplicationInfo.FLAG_SYSTEM) == 0) && !GlobalConfig.MenuListFilterCheck.systemAppChecked) {
                continue;
            }
            resultList.add(packageInfo);
        }
        return resultList;
    }
}
