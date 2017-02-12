package com.app.skynet.tulocation.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.app.skynet.tulocation.list.APList;
import com.app.skynet.tulocation.list.AccessPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by init0 on 11.02.17.
 */

public class APtoDB {

    private SQLiteDatabase database;
    private DBHelper db;
    private String[] allColumns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_BSSID, DBHelper.COLUMN_SSID, DBHelper.COLUMN_XLOC, DBHelper.COLUMN_YLOC, DBHelper.COLUMN_DIST, DBHelper.COLUMN_ENABLE};


    public APtoDB(Context context) {
        db = new DBHelper(context);
    }

    public void open() {
        database = db.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public AccessPoint createAccessPoint(String name, String mac, double posX, double posY, double distance) {
        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_DIST, distance);
        values.put(DBHelper.COLUMN_ENABLE, 1);
        try {
            values.put(DBHelper.COLUMN_XLOC, posX);
            values.put(DBHelper.COLUMN_YLOC, posY);
            values.put(DBHelper.COLUMN_BSSID, mac);
            values.put(DBHelper.COLUMN_SSID, name);

            long insertId = database.insertOrThrow(DBHelper.TABLE_COMMENTS, null, values);

            Cursor cursor = database.query(DBHelper.TABLE_COMMENTS,
                    allColumns, DBHelper.COLUMN_ID + " = " + insertId, null, null, null, null);

            cursor.moveToFirst();
            AccessPoint newComment = cursorToComment(cursor);
            cursor.close();
            return newComment;
        } catch (SQLiteException ex) {
          //  if (ex instanceof SQLiteConstraintException) {
                updateAPDist(mac, distance, 1);
//                int i = database.update(DBHelper.TABLE_COMMENTS, values, DBHelper.COLUMN_BSSID + "=\"" + mac + "\"", null);
                Log.e(APtoDB.class.getName(), "ERROR UNIQUE[]: "+ex.getMessage() +" "+ values.toString());
         //   }
//            else
//                throw ex;
            return null;
        }
    }

    public List<AccessPoint> getAllComments() {
        List<AccessPoint> comments = new ArrayList<AccessPoint>();

        Cursor cursor = database.query(DBHelper.TABLE_COMMENTS,
                allColumns, DBHelper.COLUMN_ENABLE + "=" + 1, null, null, null,DBHelper.COLUMN_DIST + " ASC", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            AccessPoint comment = cursorToComment(cursor);
            Log.i(APtoDB.class.getName(), "APEK! " + comment.getMac() +" " +comment.isEnable());
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    public void updateAPPos(AccessPoint point) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_XLOC, point.getPosX()); //These Fields should be your String values of actual column names
        cv.put(DBHelper.COLUMN_YLOC, point.getPosY());
//        cv.put(DBHelper.COLUMN_DIST, point.getDistance());
        database.update(DBHelper.TABLE_COMMENTS, cv, DBHelper.COLUMN_BSSID + "=\"" + point.getMac() + "\"", null);
    }

    public void updateAPDist(String mac, double dist, int flag) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_DIST, dist);
        cv.put(DBHelper.COLUMN_ENABLE, flag);
        database.update(DBHelper.TABLE_COMMENTS, cv, DBHelper.COLUMN_BSSID + "=\"" + mac + "\"", null);
    }
    public AccessPoint getAP(String mac) {
        Cursor cursor = database.query(DBHelper.TABLE_COMMENTS,
                allColumns, DBHelper.COLUMN_BSSID + "=\"" + mac + "\"", null, null, null, null);

        cursor.moveToFirst();
        AccessPoint newComment = cursorToComment(cursor);
        cursor.close();
        return  newComment;
    }
    public void setFalseAP() {
        String where = "UPDATE "+ DBHelper.TABLE_COMMENTS +" SET " + DBHelper.COLUMN_ENABLE + "=0";
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_ENABLE, 0);
        try {
//            Cursor c = database.rawQuery(where,null);
            int c = database.update(DBHelper.TABLE_COMMENTS, cv, DBHelper.COLUMN_ENABLE + " = 1", null);
//            String[] g = c.getColumnNames();
//            String h = "";
//            for(String tmp:g){
//                h+=" "+tmp;
//            }


            Log.i(APtoDB.class.getName(), "ROBIE UPDATE "+c);
//            c.close();
        } catch (SQLiteException ex) {
            Log.e(APtoDB.class.getName(), ex.getMessage());
        }
    }

    private AccessPoint cursorToComment(Cursor cursor) {
        AccessPoint comment = new AccessPoint();
        comment.setMac(cursor.getString(1));
        comment.setBSSID(cursor.getString(2));
        comment.setPosX(cursor.getDouble(3));
        comment.setPosY(cursor.getDouble(4));
        comment.setDistance(cursor.getDouble(5));
        comment.setEnable(cursor.getInt(6));
        return comment;
    }

}

