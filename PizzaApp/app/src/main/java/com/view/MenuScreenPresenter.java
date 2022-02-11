package com.view;

import android.graphics.Bitmap;

import androidx.fragment.app.FragmentActivity;
import com.model.MenuScreenModel;
import com.model.helpers.RowItem;
import com.pizzaapp.R;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class MenuScreenPresenter implements IPizzaAppMVP.IMenuScreenPresenter {

    private IPizzaAppMVP.IMenuScreen appView;
    private MenuScreenModel menuModel;

    public MenuScreenPresenter(IPizzaAppMVP.IMenuScreen appView, FragmentActivity activity){
        this.appView = appView;
        menuModel = new MenuScreenModel(activity);
    }

    public void setDataToListview() {
        List<RowItem> list = menuModel.getListFromDatabase();
        appView.setDataToListview(list);
    }

    public String addPizza(String name, String description) {
        if (StringUtils.isNotBlank(name)){
            menuModel.addToList(name, description);
            setDataToListview();
            return "Pizza " + name + " added to List";
        }else{
            return "Please enter name";
        }
    }

    public String addPizzaWithImage(String name, String description, String stringFilePath) {
        if (StringUtils.isNotBlank(name)){
            menuModel.addToListWithImage(name, description, stringFilePath);
            setDataToListview();
            return "Pizza " + name + " added to List";
        }else{
            return "Please enter name";
        }
    }

    public String getDescription(String name){
        if(StringUtils.isNotBlank(name)){
            String result = menuModel.getDescription(name);
            return StringUtils.isNotBlank(result)? result : "";
        }
        return "";
    }

    public Bitmap getImage(String name){
        Bitmap result = menuModel.getImage(name);
        return result;
    }
}
