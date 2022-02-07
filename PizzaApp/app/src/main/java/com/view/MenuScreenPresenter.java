package com.view;

import androidx.fragment.app.FragmentActivity;
import com.model.MenuScreenModel;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class MenuScreenPresenter implements IPizzaAppMVP.IMenuPresenter {

    private IPizzaAppMVP.IMenuFragment appView;
    private MenuScreenModel menuModel;

    public MenuScreenPresenter(IPizzaAppMVP.IMenuFragment appView, FragmentActivity activity){
        this.appView = appView;
        menuModel = new MenuScreenModel(activity);
    }

    public void setDataToListview() {
        List<String> list = menuModel.getListFromDatabase();
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
}
