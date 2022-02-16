package com.model.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_name")
public class RowItem {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "imageId")
    private byte [] imageId;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;

    public RowItem(String title, String description, byte[] imageId) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
    }
    public byte[] getImageId() {
        return imageId;
            //return BitmapFactory.decodeByteArray(imageId, 0, imageId.length);
    }

    public void setImageId(byte[] imageId) {
        this.imageId = imageId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
