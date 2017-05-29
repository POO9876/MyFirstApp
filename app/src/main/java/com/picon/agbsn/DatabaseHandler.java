package com.picon.agbsn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "notifications";
    // Contacts table name
    private static final String TABLE_CONTACTS = "message";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TIME = "time";
    private final ArrayList<Message> contact_list = new ArrayList<Message>();

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY autoincrement," + KEY_TITLE + " TEXT,"
                + KEY_MESSAGE + " TEXT," + KEY_TIME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void Add_Message(Message message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, message.get_title()); // title
        values.put(KEY_MESSAGE, message.get_message()); // Message
        values.put(KEY_TIME, message.get_time()); // Time
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Contacts
    public ArrayList<Message> Get_Contacts() {
        try {
            contact_list.clear();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " order by "+KEY_ID+ " desc";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Message message = new Message();
                    message.set_id(Integer.parseInt(cursor.getString(0)));
                    message.set_title(cursor.getString(1));
                    message.set_message(cursor.getString(2));
                    message.set_time(cursor.getString(3));
                    // Adding contact to list
                    contact_list.add(message);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
            return contact_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_contact", "" + e);
        }

        return contact_list;
    }

    // Deleting single contact
    public void Delete_Contact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

}