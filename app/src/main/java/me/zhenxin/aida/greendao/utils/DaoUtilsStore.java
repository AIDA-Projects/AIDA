package me.zhenxin.aida.greendao.utils;

import me.zhenxin.aida.entity.HistoryProjectEntity;
import me.zhenxin.aida.greendao.gen.HistoryProjectEntityDao;

/**
 * 初始化、存放及获取DaoUtils
 */
public class DaoUtilsStore {
    private volatile static DaoUtilsStore instance = new DaoUtilsStore();
    // 历史项目实体DAO层 工具类
    private CommonDaoUtils<HistoryProjectEntity> mHistoryProjectEntityDaoUtils;

    public static DaoUtilsStore getInstance() {
        return instance;
    }

    private DaoUtilsStore() {
        DaoManager mManager = DaoManager.getInstance();
        HistoryProjectEntityDao _UserDao = mManager.getDaoSession().getHistoryProjectEntityDao();
        mHistoryProjectEntityDaoUtils = new CommonDaoUtils<>(HistoryProjectEntity.class, _UserDao);
    }

    public CommonDaoUtils<HistoryProjectEntity> getUserDaoUtils() {
        return mHistoryProjectEntityDaoUtils;
    }

}