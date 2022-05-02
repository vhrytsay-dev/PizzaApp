package com.view;

import android.content.Context;
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
    private Context context;

    public MenuScreenPresenter(IPizzaAppMVP.IMenuScreen appView, FragmentActivity activity, Context context){
        this.appView = appView;
        menuModel = new MenuScreenModel(activity);
        this.context = context;
    }

    public void setDataToListview() {
        List<RowItem> list = menuModel.getListFromDatabase();
        appView.setDataToListview(list);
    }

    public String addPizza(String name, String description) {
        if (StringUtils.isNotBlank(name)){
            menuModel.addToList(name, description);
            setDataToListview();
            return String.format(context.getResources().getString(R.string.pizzaAddedText), name);
        }else{
            return context.getResources().getString(R.string.enterName);
        }
    }

    public String addPizzaWithImage(String name, String description, String stringFilePath) {
        if (StringUtils.isNotBlank(name)){
            menuModel.addToListWithImage(name, description, stringFilePath);
            setDataToListview();
            return String.format(context.getResources().getString(R.string.pizzaAddedText), name);
        }else{
            return context.getResources().getString(R.string.enterName);
        }
    }

    public String getDescription(int id){
        return menuModel.getDescription(id);
    }

    public Bitmap getImage(int id){
        Bitmap result = menuModel.getImage(id);
        return result;
    }
}
