package com.model;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MenuViewModel extends ViewModel {
    private DatabaseHelper databaseHelper;

    public MenuViewModel(FragmentActivity activity) {
        databaseHelper = new DatabaseHelper(activity);
    }

    public List<String> getListFromDatabase() {
        return databaseHelper.getAll();
    }

    public boolean addToList(String name, String description){
        databaseHelper.addToList(databaseHelper.getWritableDatabase(), name, description);
        return true;
    }

    public String getDescription(String name){
        return databaseHelper.getDescription(databaseHelper.getReadableDatabase(), name);
    }
}