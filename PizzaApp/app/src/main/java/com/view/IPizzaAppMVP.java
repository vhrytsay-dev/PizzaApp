package com.view;

import java.util.List;

public interface IPizzaAppMVP {

    interface IMenuFragment{
        void setDataToListview(List<String> categoriesToList);
    }

    interface IMenuPresenter {
        void setDataToListview();
        String addPizza(String name, String description);
        String getDescription(String name);
    }

}
