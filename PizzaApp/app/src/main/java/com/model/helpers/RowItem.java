package com.model.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RowItem {
    private byte [] imageId;
    private String title;

    public RowItem(byte[] imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }
    public Bitmap getImageId() {
        if(imageId == null){
            return null;
        }else {
            return BitmapFactory.decodeByteArray(imageId, 0, imageId.length);
        }
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
}
