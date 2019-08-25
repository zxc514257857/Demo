package com.zhr.test.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhr.test.Scale;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SCALE".
*/
public class ScaleDao extends AbstractDao<Scale, Long> {

    public static final String TABLENAME = "SCALE";

    /**
     * Properties of entity Scale.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UnitPrice = new Property(1, double.class, "unitPrice", false, "UNIT_PRICE");
        public final static Property WeightPcs = new Property(2, double.class, "weightPcs", false, "WEIGHT_PCS");
        public final static Property TotPrice = new Property(3, double.class, "totPrice", false, "TOT_PRICE");
        public final static Property TradeNo = new Property(4, String.class, "tradeNo", false, "TRADE_NO");
        public final static Property ScaleNo = new Property(5, String.class, "scaleNo", false, "SCALE_NO");
        public final static Property TradeTime = new Property(6, long.class, "tradeTime", false, "TRADE_TIME");
        public final static Property StallNum = new Property(7, String.class, "stallNum", false, "STALL_NUM");
        public final static Property PayType = new Property(8, String.class, "payType", false, "PAY_TYPE");
        public final static Property TradeUnit = new Property(9, String.class, "tradeUnit", false, "TRADE_UNIT");
    }


    public ScaleDao(DaoConfig config) {
        super(config);
    }
    
    public ScaleDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SCALE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"UNIT_PRICE\" REAL NOT NULL ," + // 1: unitPrice
                "\"WEIGHT_PCS\" REAL NOT NULL ," + // 2: weightPcs
                "\"TOT_PRICE\" REAL NOT NULL ," + // 3: totPrice
                "\"TRADE_NO\" TEXT," + // 4: tradeNo
                "\"SCALE_NO\" TEXT," + // 5: scaleNo
                "\"TRADE_TIME\" INTEGER NOT NULL ," + // 6: tradeTime
                "\"STALL_NUM\" TEXT," + // 7: stallNum
                "\"PAY_TYPE\" TEXT," + // 8: payType
                "\"TRADE_UNIT\" TEXT);"); // 9: tradeUnit
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SCALE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Scale entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindDouble(2, entity.getUnitPrice());
        stmt.bindDouble(3, entity.getWeightPcs());
        stmt.bindDouble(4, entity.getTotPrice());
 
        String tradeNo = entity.getTradeNo();
        if (tradeNo != null) {
            stmt.bindString(5, tradeNo);
        }
 
        String scaleNo = entity.getScaleNo();
        if (scaleNo != null) {
            stmt.bindString(6, scaleNo);
        }
        stmt.bindLong(7, entity.getTradeTime());
 
        String stallNum = entity.getStallNum();
        if (stallNum != null) {
            stmt.bindString(8, stallNum);
        }
 
        String payType = entity.getPayType();
        if (payType != null) {
            stmt.bindString(9, payType);
        }
 
        String tradeUnit = entity.getTradeUnit();
        if (tradeUnit != null) {
            stmt.bindString(10, tradeUnit);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Scale entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindDouble(2, entity.getUnitPrice());
        stmt.bindDouble(3, entity.getWeightPcs());
        stmt.bindDouble(4, entity.getTotPrice());
 
        String tradeNo = entity.getTradeNo();
        if (tradeNo != null) {
            stmt.bindString(5, tradeNo);
        }
 
        String scaleNo = entity.getScaleNo();
        if (scaleNo != null) {
            stmt.bindString(6, scaleNo);
        }
        stmt.bindLong(7, entity.getTradeTime());
 
        String stallNum = entity.getStallNum();
        if (stallNum != null) {
            stmt.bindString(8, stallNum);
        }
 
        String payType = entity.getPayType();
        if (payType != null) {
            stmt.bindString(9, payType);
        }
 
        String tradeUnit = entity.getTradeUnit();
        if (tradeUnit != null) {
            stmt.bindString(10, tradeUnit);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Scale readEntity(Cursor cursor, int offset) {
        Scale entity = new Scale( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getDouble(offset + 1), // unitPrice
            cursor.getDouble(offset + 2), // weightPcs
            cursor.getDouble(offset + 3), // totPrice
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // tradeNo
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // scaleNo
            cursor.getLong(offset + 6), // tradeTime
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // stallNum
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // payType
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // tradeUnit
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Scale entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUnitPrice(cursor.getDouble(offset + 1));
        entity.setWeightPcs(cursor.getDouble(offset + 2));
        entity.setTotPrice(cursor.getDouble(offset + 3));
        entity.setTradeNo(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setScaleNo(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTradeTime(cursor.getLong(offset + 6));
        entity.setStallNum(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPayType(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setTradeUnit(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Scale entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Scale entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Scale entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}