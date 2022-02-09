package com.model.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.model.helpers.RowItem;
import com.pizzaapp.R;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PizzaAppDB.db";
    public static final String TABLE_NAME = "pizzas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE = "image";
    private int initialImageId = R.drawable.ic_baseline_local_pizza_24;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_IMAGE + " INTEGER)");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @SuppressLint("Range")
    public ArrayList<RowItem> getAll() {
        ArrayList<RowItem> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "SELECT * FROM " + TABLE_NAME, null );
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            int imgID = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE));
            if(imgID == 0){
                imgID = initialImageId;
            }
            array_list.add(new RowItem(imgID, cursor.getString(cursor.getColumnIndex(COLUMN_NAME))));
            cursor.moveToNext();
        }
        return array_list;
    }

    //TODO Add image to Database
    public void addToList(SQLiteDatabase db, String name, String description) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, String.valueOf(name));
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, String.valueOf(description));
        db.insert(TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public String getDescription(SQLiteDatabase db, String name) {
        String description;
        Cursor cursor =  db.rawQuery( "SELECT " + COLUMN_DESCRIPTION + " FROM " + TABLE_NAME + " WHERE name =\"" + name + "\"", null );
        cursor.moveToFirst();
        description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        return StringUtils.isNotEmpty(description)? description : "";
    }

    @SuppressLint("Range")
    public int getImage(SQLiteDatabase db, String name) {
        Cursor cursor =  db.rawQuery( "SELECT " + COLUMN_IMAGE + " FROM " + TABLE_NAME + " WHERE name =\"" + name + "\"", null );
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE));
    }
}
