package com.view;

import androidx.fragment.app.FragmentActivity;
import com.model.MenuScreenModel;
import com.model.helpers.RowItem;
import com.pizzaapp.R;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class MenuScreenScreenPresenter implements IPizzaAppMVP.IMenuScreenPresenter {

    private IPizzaAppMVP.IMenuScreen appView;
    private MenuScreenModel menuModel;
    private final int initialImageId = R.drawable.ic_baseline_local_pizza_24;

    public MenuScreenScreenPresenter(IPizzaAppMVP.IMenuScreen appView, FragmentActivity activity){
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

    public String getDescription(String name){
        if(StringUtils.isNotBlank(name)){
            String result = menuModel.getDescription(name);
            return StringUtils.isNotBlank(result)? result : "";
        }
        return "";
    }

    public int getImage(String name){
        if(StringUtils.isNotBlank(name)){
            int result = menuModel.getImage(name);
            return result != 0? result : initialImageId;
        }
        return initialImageId;
    }
}
