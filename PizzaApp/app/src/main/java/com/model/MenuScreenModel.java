package com.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.model.database.DatabaseHelper;
import com.model.helpers.RowItem;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MenuScreenModel extends ViewModel {
    private DatabaseHelper databaseHelper;

    public MenuScreenModel(FragmentActivity activity) {
        databaseHelper = new DatabaseHelper(activity);
    }

    public List<RowItem> getListFromDatabase() {
        return databaseHelper.getAll();
    }

    public boolean addToList(String name, String description){
        databaseHelper.addToList(databaseHelper.getWritableDatabase(), name, description);
        return true;
    }

    public boolean addToListWithImage(String name, String description, String imagePath){
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] bytesImage = byteArrayOutputStream.toByteArray();
        databaseHelper.addToListWithImage(databaseHelper.getWritableDatabase(), name, description, bytesImage);
        return true;
    }

    public String getDescription(String name){
        return databaseHelper.getDescription(databaseHelper.getReadableDatabase(), name);
    }

    public Bitmap getImage(String name){
        return databaseHelper.getImage(databaseHelper.getReadableDatabase(), name);
    }
}