package com.view;

import android.app.Activity;
import android.graphics.Bitmap;

import com.model.DescriptionScreenModel;

public class DescriptionScreenPresenter implements IPizzaAppMVP.IPizzaDescriptionScreenPresenter {

    private IPizzaAppMVP.IPizzaDescriptionScreen view;
    private DescriptionScreenModel model;

    public DescriptionScreenPresenter(IPizzaAppMVP.IPizzaDescriptionScreen view, Activity activity){
        this.view = view;
        model = new DescriptionScreenModel(activity);
    }

    public Bitmap getImage(String name){
        Bitmap result = model.getImage(name);
        return result;
    }
}
