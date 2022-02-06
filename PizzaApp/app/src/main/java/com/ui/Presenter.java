package com.ui;

import androidx.fragment.app.FragmentActivity;
import com.model.MenuViewModel;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Presenter {

    private IMenuFragmentView appView;
    private MenuViewModel menuModel;

    public Presenter(IMenuFragmentView appView, FragmentActivity activity){
        this.appView = appView;
        menuModel = new MenuViewModel(activity);
    }

    public void setDataToListview() {
        List<String> list = menuModel.getListFromDatabase();
        appView.setDataToListview(list);
    }

    public String addPizza(String name, String description) {
        if (StringUtils.isNotEmpty(name)){
            menuModel.addToList(name, description);
            appView.update();
            return "Pizza " + name + " added to List";
        }else{
            return "Please enter name";
        }
    }

    public String getDescription(String name){
        return menuModel.getDescription(name);
    }
}
