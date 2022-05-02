package com.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.model.database.RoomDB;
import com.model.database.RowItemAdapter;
import com.model.helpers.RowItem;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MenuScreenModel extends ViewModel {
    private RoomDB database;
    private RowItemAdapter adapter;

    public MenuScreenModel(FragmentActivity activity) {
        database = RoomDB.getInstance(activity);
    }

    public List<RowItem> getListFromDatabase() {
        return database.mainDao().getAll();
    }

    public void addToList(String name, String description){
        database.mainDao().insert(new RowItem(name, description, null));
    }

    public void addToListWithImage(String name, String description, String imagePath){
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] bytesImage = byteArrayOutputStream.toByteArray();
        database.mainDao().insert(new RowItem(name, description, bytesImage));
    }

    public String getDescription(int id){
        database.mainDao().getDescription(id);
        return null;
    }

    public Bitmap getImage(int id){
        database.mainDao().getImage(id);
        return null;
    }
}