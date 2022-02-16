package com.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.ViewModel;

import com.model.database.RoomDB;

public class DescriptionScreenModel extends ViewModel {
    private RoomDB databaseHelper;

    public DescriptionScreenModel(Activity activity) {
        databaseHelper = RoomDB.getInstance(activity);
    }

    public Bitmap getImage(int id){
        byte[] result = databaseHelper.mainDao().getImage(id);
        if(result != null){
            return BitmapFactory.decodeByteArray(result, 0, result.length);
        }
        return null;
    }
}
