package com.ui;

import com.model.PizzaAppModel;

public class Presenter {

    AppView appView;
    private PizzaAppModel pizzaAppModel = new PizzaAppModel();

    public Presenter(AppView appView){
        this.appView = appView;
    }

    public String getName(){
        return pizzaAppModel.getName();
    }

    public String getDescription(){
        return pizzaAppModel.getDescription();
    }

}
