package com.bdjobs.training.contactbdjobsproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bdjobs.training.contactbdjobsproject.MainActivity;

/**
 * Created by FIROZ HASAN on 8/1/2017.
 */
//--------------------hello
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="contactTest.db";
    public static final int DATABASE_VERSION = 4;

    public static final String TABLE_NAME_CONTACT="contact";

    public static final String CONTACT_COL_ID="_id";
    public static final String CONTACT_COL_MOBILE_NUMBER="MobileNumber";
    public static final String CONTACT_COL_EMAIL_ADDRESS="EmailAddress";
    public static final String CONTACT_COL_MOBILE_TYPE="mType";
    public static final String CONTACT_COL_EMAIL_TYPE="eType";
    public static final String CONTACT_COL_NAME="Name";

    static final String CREATE_TABLE_CONTACT = "CREATE TABLE "+TABLE_NAME_CONTACT +" ( " +
            CONTACT_COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            CONTACT_COL_MOBILE_NUMBER+" TEXT ,"+
            CONTACT_COL_EMAIL_ADDRESS+" TEXT ,"+
            CONTACT_COL_MOBILE_TYPE+" TEXT ,"+
            CONTACT_COL_EMAIL_TYPE+" TEXT ,"+
            CONTACT_COL_NAME+" TEXT )";

    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
        Log.d(MainActivity.TAG,CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME_CONTACT);
    }

}
