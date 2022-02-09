package com.view;

import com.model.helpers.RowItem;

import java.util.List;

public interface IPizzaAppMVP {

    interface IMenuScreen {
        void setDataToListview(List<RowItem> categoriesToList);
    }

    interface IMenuScreenPresenter {
        void setDataToListview();
        String addPizza(String name, String description);
        String getDescription(String name);
    }

    interface IPizzaDescriptionScreen {
    }

    interface IPizzaDescriptionScreenPresenter {
    }

}
