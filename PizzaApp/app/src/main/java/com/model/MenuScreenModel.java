package com.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.model.database.DatabaseHelper;
import com.model.helpers.RowItem;

import org.apache.commons.lang3.StringUtils;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class MenuScreenModel extends ViewModel {
    private DatabaseHelper databaseHelper;

    public MenuScreenModel(FragmentActivity activity) {
        databaseHelper = new DatabaseHelper(activity);
    }

    public List<RowItem> getListFromDatabase() {
        return databaseHelper.getAll(databaseHelper.getReadableDatabase());
    }

    public void addToList(String name, String description){
        databaseHelper.addToList(databaseHelper.getWritableDatabase(), name, description);
    }

    public void addToListWithImage(String name, String description, String imagePath){
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] bytesImage = byteArrayOutputStream.toByteArray();
        databaseHelper.addToListWithImage(databaseHelper.getWritableDatabase(), name, description, bytesImage);
    }

    public String getDescription(String name){
        String description = databaseHelper.getDescription(databaseHelper.getReadableDatabase(), name);
        return StringUtils.isNotEmpty(description)? description : "";
    }

    public Bitmap getImage(String name){
        byte [] bytesImage = databaseHelper.getImage(databaseHelper.getReadableDatabase(), name);
        if(bytesImage != null){
            return BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length);
        }
        return null;
    }
}