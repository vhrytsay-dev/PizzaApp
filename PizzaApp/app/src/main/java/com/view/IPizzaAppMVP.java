package com.view;

import android.graphics.Bitmap;

import com.model.helpers.RowItem;

import java.util.List;

public interface IPizzaAppMVP {

    interface IMenuScreen {
        void setDataToListview(List<RowItem> categoriesToList);
    }

    interface IMenuScreenPresenter {
        void setDataToListview();
        String addPizza(String name, String description);
        String addPizzaWithImage(String name, String description, String stringFilePath);
        String getDescription(String name);
        Bitmap getImage(String name);
    }

    interface IPizzaDescriptionScreen {
    }

    interface IPizzaDescriptionScreenPresenter {
    }

}
