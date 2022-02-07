package com.view;

import android.app.Activity;

import com.model.DescriptionScreenModel;

public class PizzaDescriptionScreenPresenter implements IPizzaAppMVP.IPizzaDescriptionScreenPresenter {

    private IPizzaAppMVP.IPizzaDescriptionScreen view;
    private DescriptionScreenModel model;

    public PizzaDescriptionScreenPresenter(IPizzaAppMVP.IPizzaDescriptionScreen view, Activity activity){
        this.view = view;
        model = new DescriptionScreenModel(activity);
    }
}
