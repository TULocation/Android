package com.app.skynet.tulocation.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by init0 on 11.02.17.
 */

public class DBHelper extends SQLiteOpenHelper {
    //    private String DATABASE_NAME = "access_point_tulocation";

    /*RAKI*/
    public static final String TABLE_COMMENTS = "accesspoints";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BSSID = "bssid";
    public static final String COLUMN_XLOC = "xloc";
    public static final String COLUMN_YLOC = "yloc";
    public static final String COLUMN_DIST = "dist";

    private static final String DATABASE_NAME = "tulocation.db";
    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_BSSID + " text unique not null,"
            + COLUMN_XLOC + " real,"
            + COLUMN_YLOC + " real,"
            + COLUMN_DIST + " real"
            + ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }
}