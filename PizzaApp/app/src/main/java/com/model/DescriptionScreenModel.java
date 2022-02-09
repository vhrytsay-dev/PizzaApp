package com.model;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.model.database.DatabaseHelper;

public class DescriptionScreenModel extends ViewModel {
    private DatabaseHelper databaseHelper;

    public DescriptionScreenModel(Activity activity) {
        databaseHelper = new DatabaseHelper(activity);
    }
}
