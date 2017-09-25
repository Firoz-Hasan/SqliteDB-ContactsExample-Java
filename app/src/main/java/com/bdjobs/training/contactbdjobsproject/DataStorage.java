package com.bdjobs.training.contactbdjobsproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

/**
 * Created by FIROZ HASAN on 8/1/2017.
 */
//--------------

public class DataStorage {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    SimpleCursorAdapter simpleCursorAdapter;

    public DataStorage(Context context){
        dbHelper=new DBHelper(context);

    }

    private void dbOpen()
    {
        database=dbHelper.getWritableDatabase();

    }

    private void dbClose()
    {
        dbHelper.close();
    }



    public boolean insertContact (ContactClass contactModel)
    {
        this.dbOpen();

        ContentValues cvProfileValue=new ContentValues();
        cvProfileValue.put(DBHelper.CONTACT_COL_NAME,contactModel.getName());
        cvProfileValue.put(DBHelper.CONTACT_COL_MOBILE_NUMBER, contactModel.getPhoneNumber());
        cvProfileValue.put(DBHelper.CONTACT_COL_MOBILE_TYPE,contactModel.getPhonetype());
        cvProfileValue.put(DBHelper.CONTACT_COL_EMAIL_ADDRESS,contactModel.getEmail());
        cvProfileValue.put(DBHelper.CONTACT_COL_EMAIL_TYPE,contactModel.getEmailtype());

        long inserted=database.insert(DBHelper.TABLE_NAME_CONTACT, null, cvProfileValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }

    public ArrayList<ContactClass> getAllContacts()
    {

        ArrayList<ContactClass> contactModels=new ArrayList<>();
        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_CONTACT;


        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int contactID=cursor.getInt(cursor.getColumnIndex(DBHelper.CONTACT_COL_ID));
                String contactName=cursor.getString(cursor.getColumnIndex(DBHelper.CONTACT_COL_NAME));
                String contactMblNumber =cursor.getString(cursor.getColumnIndex(DBHelper.CONTACT_COL_MOBILE_NUMBER));
                String contactMblType=cursor.getString(cursor.getColumnIndex(DBHelper.CONTACT_COL_MOBILE_TYPE));
                String contactEmailAddress=cursor.getString(cursor.getColumnIndex(DBHelper.CONTACT_COL_EMAIL_ADDRESS));
                String contactEmailAddressType=cursor.getString(cursor.getColumnIndex(DBHelper.CONTACT_COL_EMAIL_TYPE));
                //String profile_flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                ContactClass contactModel=new ContactClass(contactName,contactMblNumber,contactEmailAddress,contactMblType,contactEmailAddressType, contactID);
                contactModels.add(contactModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return contactModels;
    }

    public void deleteRow(int value)
    {
        this.dbOpen();
        database.execSQL("DELETE FROM " + DBHelper.TABLE_NAME_CONTACT + " WHERE "+DBHelper.CONTACT_COL_ID+"='"+value+"'");
        this.dbOpen();
    }

    public boolean updateContact (int contactID,ContactClass contactModel)
    {
        this.dbOpen();

        ContentValues contactValue=new ContentValues();
        contactValue.put(DBHelper.CONTACT_COL_NAME, contactModel.getName());
        contactValue.put(DBHelper.CONTACT_COL_EMAIL_ADDRESS, contactModel.getEmail());
        contactValue.put(DBHelper.CONTACT_COL_MOBILE_NUMBER, contactModel.getPhoneNumber());
        contactValue.put(DBHelper.CONTACT_COL_EMAIL_TYPE, contactModel.getEmailtype());
        contactValue.put(DBHelper.CONTACT_COL_MOBILE_TYPE, contactModel.getPhonetype());
        //cvDoctorValue.put(DBHelper.COL_FLAG, doctorModel.getFlag());

        // long inserted=database.insert(DBHelper.TABLE_NAME_DOCTOR,null,cvDoctorValue);

        long updated = database.update(DBHelper.TABLE_NAME_CONTACT, contactValue, DBHelper.CONTACT_COL_ID + "= " + contactID, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

}
