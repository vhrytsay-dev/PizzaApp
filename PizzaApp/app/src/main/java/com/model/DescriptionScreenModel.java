package com.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.ViewModel;

import com.model.database.DatabaseHelper;

import java.io.ByteArrayOutputStream;

public class DescriptionScreenModel extends ViewModel {
    private DatabaseHelper databaseHelper;

    public DescriptionScreenModel(Activity activity) {
        databaseHelper = new DatabaseHelper(activity);
    }

    public Bitmap getImage(String name){
        byte [] bytesImage = databaseHelper.getImage(databaseHelper.getReadableDatabase(), name);
        if(bytesImage != null){
            return BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length);
        }
        return null;
    }
}
