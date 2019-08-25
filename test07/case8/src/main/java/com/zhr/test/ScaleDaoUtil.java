package com.zhr.test;

import android.content.Context;

import com.zhr.test.dao.DaoSession;
import com.zhr.test.dao.ScaleDao;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by zhr on 2019/3/3.
 * Located:zmkj
 * Des: 数据库的增删改查
 */
public class ScaleDaoUtil {

    private final DaoManager mDaoManager;
    private final boolean DEBUG_FLAG = true;
    private final DaoSession mDaoSession;
    private ScaleDao mScaleDao;

    public ScaleDaoUtil(Context context) {
        mDaoManager = DaoManager.getInstance();
        mDaoManager.initContext(context);
        mDaoManager.setDebug(DEBUG_FLAG);
        mDaoSession = mDaoManager.getDaoSession();
    }

    /**
     * 增加一条数据
     * @param scale
     */
    public void insertData(Scale scale){
        mDaoSession.insertOrReplace(scale);
    }

    /**
     * 增加数据集
     * @param scales
     */
    public void insertDataList(final List<Scale> scales){
        mDaoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < scales.size() ; i++){
                    mDaoSession.insertOrReplace(scales.get(i));
                }
            }
        });
    }

    /**
     * 删除一条数据
     * @param scale
     */
    public void deleteData(Scale scale){
        mDaoSession.delete(scale);
    }

    /**
     * 删除全部数据 scale.class
     */
    public void deleteAllData(Class<Scale> cls){
        mDaoSession.deleteAll(cls);
    }

    /**
     * 修改一条数据
     */
    public void updateData(Scale scale){
        mDaoSession.update(scale);
    }

    /**
     * 根据主键查询一条数据
     * @param key
     */
    public Scale queryData(long key , Class<Scale> cls){
        return mDaoSession.load(cls , key);
    }

    /**
     * 查询数据集
     * @return
     */
    public List<Scale> queryDataList(Class<Scale> cls , WhereCondition cond1, WhereCondition cond2){
        QueryBuilder<Scale> builder = mDaoSession.queryBuilder(cls);
        return builder.where(cond1).where(cond2).list();
    }

    /**
     * 查询所有数据
     * @param cls
     * @return
     */
    public List<Scale> queryAll(Class<Scale> cls){
        return mDaoSession.loadAll(cls);
    }
}
