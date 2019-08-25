package com.zhr.test;

import android.content.Context;

import com.zhr.test.dao.DaoMaster;
import com.zhr.test.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

public class DaoManager {

    // 多线程访问
    public volatile static DaoManager mDaoManager;
    // 数据库名称
    private static final String DB_NAME = "scale.db";
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private Context mContext;
    private DaoMaster.DevOpenHelper mHelper;

    /**
     * 使用单例模式创建DaoManager
     * @return
     */
    public static DaoManager getInstance(){
        DaoManager instance = null;
        if(mDaoManager == null){
            synchronized (DaoManager.class){
                if(instance == null){
                    instance = new DaoManager();
                    mDaoManager = instance;
                }
            }
        }
        return mDaoManager;
    }

    /**
     * 初始化Context
     * @param context
     */
    public void initContext(Context context){
        this.mContext = context;
    }

    /**
     * 判断DaoMaster是否存在 不存在则创建
     * @return
     */
    public DaoMaster getDaoMaster(){
        if(mDaoMaster == null){
            // 创建数据库帮助类 给数据库命名
            mHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
            mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        }
        return mDaoMaster;
    }

    /**
     * 判断DaoSession是否存在 不存在再判断DaoMaster是否存在 不纯在则创建 由DaoMaster获取DaoSession
     * @return
     */
    public DaoSession getDaoSession(){
        if(mDaoSession == null){
            if(mDaoMaster == null){
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    /**
     * 设置数据库的调试模式是否打开
     * @param debugFlag
     */
    public void setDebug(boolean debugFlag){
        QueryBuilder.LOG_SQL = debugFlag;
        QueryBuilder.LOG_VALUES = debugFlag;
    }

    public void closeDataBase(){
        closeHelper();
        closeDaoSession();
    }

    /**
     * helper关掉之后 DaoMaster就关掉了
     */
    public void closeHelper(){
        if(mHelper != null){
            mHelper.close();
            mHelper = null;
        }
    }

    public void closeDaoSession(){
        if(mDaoSession != null){
            mDaoSession.clear();
            mDaoSession = null;
        }
    }
}
