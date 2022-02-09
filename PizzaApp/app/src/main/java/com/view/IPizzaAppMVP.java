package com.view;

import java.util.List;

public interface IPizzaAppMVP {

    interface IMenuScreen {
        void setDataToListview(List<String> categoriesToList);
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
