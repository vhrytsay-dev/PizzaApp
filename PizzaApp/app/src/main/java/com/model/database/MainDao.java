package com.model.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import android.graphics.Bitmap;
import android.inputmethodservice.Keyboard;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.helpers.RowItem;

import java.util.List;

@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
    void insert(RowItem rowItem);

    @Delete
    void delete(RowItem rowItem);

    @Query("UPDATE table_name SET title =:sTitle WHERE ID = :sID")
    void update(int sID, String sTitle);

    @Query("UPDATE table_name SET imageId =:sImage WHERE ID = :sID")
    void update(int sID, byte[] sImage);

    @Query("SELECT * FROM table_name")
    List<RowItem> getAll();

    @Query("SELECT title FROM table_name WHERE ID = :id")
    String getTitle(int id);

    @Query("SELECT description FROM table_name WHERE ID = :id")
    String getDescription(int id);

    @Query("SELECT imageId FROM table_name WHERE ID =:id")
    byte[] getImage(int id);

    @Query("UPDATE table_name SET favourite =:sLike WHERE ID = :sID")
    void update(int sID, boolean sLike);

    @Query("SELECT favourite FROM table_name WHERE ID =:sID")
    boolean getFavourite(int sID);
}
