package kr.anima.xd.s.calendardiarytest.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alfo6-10 on 8/23/2017.
 */

public class DBOpener extends SQLiteOpenHelper {

    private String tableName;

    public DBOpener(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        tableName=name;
    }

    public DBOpener(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        tableName=name;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tableName+"("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"name TEXT NOT NULL, "
                +"img INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tableName);
        onCreate(db);
    }

}
