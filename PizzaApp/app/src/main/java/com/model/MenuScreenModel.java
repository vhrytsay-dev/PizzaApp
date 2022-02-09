package com.model;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.model.database.DatabaseHelper;
import com.model.helpers.RowItem;

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

    public String getDescription(String name){
        return databaseHelper.getDescription(databaseHelper.getReadableDatabase(), name);
    }

    public int getImage(String name){
        return databaseHelper.getImage(databaseHelper.getReadableDatabase(), name);
    }
}